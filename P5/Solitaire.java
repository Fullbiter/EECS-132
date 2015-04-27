/**
 * [description placeholder]
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.25
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class Solitaire {
    
    /** The stock **/
    private Deck stock;
    
    /** The tableau **/
    public Tableau tableau = new Tableau();
    
    /** The active Piles **/
    private ArrayList<Pile> piles;
    
    /** The foundation **/
    private ArrayList<Foundation> foundation;
    
    /**
     * Constructs a regular Solitaire game
     */
    public Solitaire() {
        this(new Deck(), 7);
    }
    
    /**
     * Constructs a custom Solitaire game
     * @param  deck             the Deck
     * @param  activePileCount  the number of active piles
     */
    public Solitaire(Deck deck, int activePileCount) {
        this.piles = new ArrayList<Pile>(activePileCount);
        this.foundation = new ArrayList<Foundation>(deck.getNumberSuits());
        this.stock = deck;
        // ensure the stock is shuffled
        stock.shuffle();
        // flip each Card face down
        stock.hideCards();
        // add empty Foundations to foundation
        for (int i = 0; i < deck.getNumberSuits(); i++) {
            Foundation newPile = new Foundation(stock.getUniqueSuits()[i]);
            foundation.add(newPile);
        }
        // add empty Piles to piles
        for (int i = 0; i < activePileCount; i++) {
            Pile newPile = new Pile();
            piles.add(newPile);
        }
        initializeGame();
    }
    
    /**
     * Deals Cards into each Pile and flip the top Cards face up
     */
    public void initializeGame() {
        // iterate over each Pile
        for (int i = 0; i < piles.size(); i++) {
            // add an iterative amount of cards to each Pile (e.g. 1, 2, ..., number of piles)
            for (int j = 0; j <= i; j++)
                piles.get(piles.size() - i - 1).add(stock.drawCard());
            piles.get(piles.size() - i - 1).getLast().setIsFaceUp(true);
        }
    }
    
    /**
     * Moves three Cards (or fewer if there are fewer in the stock) to the tableau
     */
    public void moveStockToTableau() {
        // don't attempt anything if the stock is empty
        if (stock.size() > 0) {
            for (Card card : tableau)
                card.setIsFaceUp(false);
            for (int i = 0; i < 3 && stock.size() > 0; i++) {
                tableau.add(stock.drawCard());
                tableau.getLast().setIsFaceUp(true);
            }
        }
    }
    
    /**
     * Moves all tableau Cards to the stock
     */
    public void moveTableauToStock() {
        // iterate over each Card in the tableau
        for (Card card : tableau) {
//            card.setIsFaceUp(false);
            stock.insertCard(card);
        }
        tableau.clear();
    }
    
    /**
     * Moves the top card of a given Pile to the foundation
     */
    public void movePileToFoundation() {
        // iterate over each Card in the tableau
//        for (Card card : tableau) {
//            card.setIsFaceUp(false);
//            stock.add(card);
//        }
//        tableau.clear();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        // represent the foundation piles
        for (Foundation pile : foundation) {
            sb.append(pile.getSuit().toString() + ": ");
            for (Card card : pile)
                sb.append(card.toString() + "  ");
            sb.append("\n");
        }
        sb.append("\n");
        // represent the active piles
        int pileLabel = piles.size();
        for (Pile pile : piles) {
            sb.append(pileLabel-- + ": ");
            for (Card card : pile)
                sb.append(card.toString() + "  ");
            sb.append("\n");
        }
        // represent the stock
        sb.append("\nStock: ");
        for (Card card : stock)
            sb.append(card.toString() + "  ");
        // represent the tableau
        sb.append("\nTableau: ");
        for (Card card : tableau)
            sb.append(card.toString() + "  ");
        sb.append("\n");
        return sb.toString();
    }
}
