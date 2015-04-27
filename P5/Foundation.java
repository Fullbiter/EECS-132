/**
 * Foundation objects represent foundations in a game of Solitaire.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.26
 */

public class Foundation extends Pile {
    /** Suit of the Foundation **/
    Card.Suit suit;
    
    public Foundation(Card.Suit suit) {
        this.suit = suit;
    }
    
    public Card.Suit getSuit() {
        return suit;
    }
}