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
    private Tableau tableau = new Tableau();
    
    /** The active Piles **/
    public ArrayList<Pile> piles;
    
    /** The foundation **/
    public ArrayList<FoundationPile> foundation;
    
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
        this.foundation = new ArrayList<FoundationPile>(deck.getNumberSuits());
        this.stock = deck;
        // ensure the stock is shuffled
        stock.shuffle();
        // flip each Card face down
        stock.hideCards();
        // add empty Foundations to foundation
        for (int i = 0; i < deck.getNumberSuits(); i++) {
            FoundationPile newPile = new FoundationPile(stock.getUniqueSuits()[i]);
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
            card.setIsFaceUp(false);
            stock.insertCard(card);
        }
        tableau.clear();
    }
    
    /**
     * Moves the top card of a given Pile to the foundation
     */
    public void movePileToFoundation(Pile pile) {
        if (pile.size() == 0)
            throw new IllegalArgumentException("The input Pile is empty so no Cards can be moved.");
        boolean movedCard = false;
        for (int i = 0; i < foundation.size() && !movedCard; i++) {
            // the target Card matches the current FoundationPile
            if (pile.getLast().getSuit() == foundation.get(i).getSuit()) {
                // the FoundationPile is empty and the target Card matches the minimum face value OR
                // it's not empty and the face value of the target Card is one greater than the top FoundationPile Card
                if ((foundation.get(i).size() == 0 && pile.getLast().getFace() == stock.getMinFace())
                        || (foundation.get(i).size() > 0 && pile.getLast().getFace().ordinal()
                                == foundation.get(i).getLast().getFace().ordinal() + 1)) {
                    foundation.get(i).add(pile.getLast());
                    pile.removeLast();
                    pile.getLast().setIsFaceUp(true);
                    movedCard = true;
                }
            }
        }
        if (!movedCard)
            throw new IllegalArgumentException("Value of the target Card is not valid for its FoundationPile,\nor Suit"
                                                   + " of the target Card does not match that of any FoundationPile.");
    }
    
    /**
     * Moves the top card of a given Pile to the foundation
     */
    public void moveTableauToActive(Pile pile) {
        if (tableau.size() == 0)
            throw new IllegalArgumentException("The tableau is empty so no Cards can be moved.");
        boolean movedCard = false;
        // if the target Card color and the last card of pile color differ
        if (!tableau.getLast().getSuit().getColor().equals(pile.getLast().getSuit().getColor())) {
                pile.add(tableau.getLast());
                tableau.removeLast();
                movedCard = true;
        }
        if (!movedCard)
            throw new IllegalArgumentException("Color of the target Card matches its destination color.");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        // represent the foundation piles
        for (FoundationPile pile : foundation) {
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
