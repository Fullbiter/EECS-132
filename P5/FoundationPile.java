/**
 * FoundationPile objects represent the piles of the foundation in a game of Solitaire.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.26
 */

public class FoundationPile extends Pile {
    /** Suit of the FoundationPile **/
    Card.Suit suit;
    
    /**
     * Constructs a FoundationPile
     * @param  suit of the FoundationPile
     */
    public FoundationPile(Card.Suit suit) {
        this.suit = suit;
    }
    
    /**
     * Returns the Suit of this FoundationPile
     * @return  suits
     */
    public Card.Suit getSuit() {
        return suit;
    }
}
