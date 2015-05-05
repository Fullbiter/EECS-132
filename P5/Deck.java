/**
 * Deck objects represent a deck of playing cards.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.26
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Deck extends ArrayList<Card> {
    
    /** The face values represented by the cards in this Deck **/
    private Card.Face[] faces;
    
    /** The suits represented by the cards in this Deck **/
    private Card.Suit[] suits;
    
    /** The unique suits represented by the cards in this Deck **/
    private Card.Suit[] uniqueSuits;
    
    /**
     * Constructs a typical Deck
     */
    public Deck() {
        this(Card.Face.ACE, Card.Face.KING, Card.Suit.SPADES, Card.Suit.HEARTS, Card.Suit.DIAMONDS, Card.Suit.CLUBS);
    }
    
    /**
     * Constructs a custom Deck
     * @param  minFace  the minimum face value
     * @param  maxFace  the maximum face value
     * @param  suits    the possible Suits for each Card
     */
    public Deck(Card.Face minFace, Card.Face maxFace, Card.Suit... suits) {
        // define the face values to be used
        this.faces = new Card.Face[maxFace.ordinal() - minFace.ordinal() + 1];
        Card.Face[] allFaces = Card.Face.values();
        for (int i = 0; i < this.faces.length; i++)
            this.faces[i] = allFaces[i + minFace.ordinal()];
        
        // define the suits to be used
        this.suits = suits;
        
        // copy suits data into a HashSet to remove duplicate suits, measure new size
        List<Card.Suit> auxList = Arrays.asList(suits);
        TreeSet<Card.Suit> auxSet = new TreeSet<Card.Suit>(auxList);
        uniqueSuits = new Card.Suit[auxSet.size()];
        int auxIndex = 0;
        // add all suits in auxSet to uniqueSuits
        for (Card.Suit suit : auxSet)
            uniqueSuits[auxIndex++] = suit;
        
        // add to the deck one of every face/suit combination
        for (Card.Face face : faces) {
            for (Card.Suit suit : suits)
                this.add(new Card(face, suit));
        }
    }
    
    /**
     * Returns the number of unique Suits in this Deck
     * @return  number of unique suits
     */
    public int getNumberSuits() {
        return uniqueSuits.length;
    }
    
    /**
     * Returns the Suits in this Deck
     * @return  suits
     */
    public Card.Suit[] getUniqueSuits() {
        return uniqueSuits;
    }
    
    /**
     * Returns the minimum face value used in this deck
     * @return  minimum face value
     */
    public Card.Face getMinFace() {
        return this.faces[0];
    }
    
    /**
     * Returns the maximum face value used in this deck
     * @return  maximum face value
     */
    public Card.Face getMaxFace() {
        return this.faces[faces.length - 1];
    }
    
    /**
     * Shuffles this Deck
     */
    public void shuffle() {
        Collections.shuffle(this);
    }
    
    /**
     * Returns the top Card on the deck
     * @return  Card
     */
    public Card drawCard() {
        Card drawnCard = this.get(this.size() - 1);
        this.remove(this.size() - 1);
        return drawnCard;
    }
    
    /**
     * Places the input Card at the bottom of the deck
     * @param  Card
     */
    public void insertCard(Card card) {
        this.add(0, card);        
    }
    
    /**
     * Returns a String representation of this Deck
     * @return  String version of Deck
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Interate over each card
        for (int i = 0; i < this.size() - 1; i++) {
            sb.append(this.get(i).toString());
            sb.append(((i + 1) % uniqueSuits.length == 0) ? "\n" : "  ");
        }
        sb.append(this.get(this.size() - 1).toString());
        return sb.toString();
    }
    
    /**
     * Flips Cards so that none are face up
     */
    public void hideCards() {
        // Iterate over each Card
        for (Card card : this) {
            card.setIsFaceUp(false);
        }
    }
    
    /**
     * Flips Cards so that all are face up
     */
    public void showCards() {
        // Iterate over each Card
        for (Card card : this) {
            card.setIsFaceUp(true);
        }
    }
}
