/**
 * FoundationPile objects represent the piles of the foundation in a game of Solitaire.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.26
 */

public class FoundationPile extends Pile {
    /** Suit of the FoundationPile **/
    Card.Suit suit;
    
    public FoundationPile(Card.Suit suit) {
        this.suit = suit;
    }
    
    public Card.Suit getSuit() {
        return suit;
    }
}
