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
    private Deck stock = new Deck();
    
    /** The tableau **/
    private LinkedList<Card> tableau = new LinkedList<Card>();
    
    /** The active piles **/
    private ArrayList<LinkedList<Card>> piles;
    
    /** The foundation **/
    private ArrayList<LinkedList<Card>> foundation;
    
    /**
     * Constructs a custom Solitaire game
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
        piles = new ArrayList<LinkedList<Card>>(activePileCount);
        foundation = new ArrayList<LinkedList<Card>>(deck.getNumberSuits());
        stock = deck;
        // ensure the deck is shuffled
        stock.shuffle();
        // flip each Card face down
        stock.hideCards();
        // deal Cards into each pile
        for (int i = activePileCount - 1; i >= 0; i--) {
            LinkedList<Card> newPile = new LinkedList<Card>();
            for (int j = 0; j <= i; j++)
                newPile.add(deck.drawCard());
            piles.add(newPile);
            // flip the top card face up
            piles.get(activePileCount - i - 1).getLast().setIsFaceUp(true);
        }
    }
    
    /**
     * Returns the number of unique Suits in this Deck
     * @return  number of unique suits
     */
//    public int getNumberSuits() {
//        return this.uniqueSuitCount;
//    }
}
