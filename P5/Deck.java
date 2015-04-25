/**
 * Deck objects represent a deck of playing cards.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.25
 */
public class Deck {
    
    /** The face values contained in this Deck **/
    private Face[] faces;
    
    /** The suits contained in this Deck **/
    private Suit[] suits;
    
    /**
     * Constructs a typical Deck
     */
    public Deck() {
        this(Face.ACE, Face.KING, Suit.SPADES, Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS);
    }
    
    /**
     * Constructs a custom Deck
     * @param  minFace  the minimum face value
     * @param  maxFace  the maximum face value
     * @param  suits    whether the card is face up
     */
    public Deck(Face minFace, Face maxFace, Suit... suits) {
        this.faces = new Face[maxFace.ordinal() - minFace.ordinal() + 1];
        for (int i = 0; i < this.faces.length; i++)
            this.faces[i] = minFace.values()[i];
        this.suits = suits;
    }
}
