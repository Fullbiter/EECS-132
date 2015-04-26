/**
 * Deck objects represent a deck of playing cards.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.25
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Deck extends java.util.ArrayList<Card> {
    
    /** The face values represented by the cards in this Deck **/
    private Card.Face[] faces;
    
    /** The suits represented by the cards in this Deck **/
    private Card.Suit[] suits;
    
    /** The number of unique suits represented by the cards in this Deck **/
    private int uniqueSuitCount = 0;
    
    /** The Cards contained in this Deck **/
    private ArrayList<Card> cards = new ArrayList<Card>(52);
    
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
        this.faces = new Card.Face[maxFace.ordinal() - minFace.ordinal() + 1];
        Card.Face[] allFaces = Card.Face.values();
        for (int i = 0; i < this.faces.length; i++)
            this.faces[i] = allFaces[i + minFace.ordinal()];
        
        this.suits = suits;
        
        // copy suits data into a HashSet to remove duplicate suits, measure new size
        List<Card.Suit> auxList = Arrays.asList(suits);
        HashSet<Card.Suit> auxSet = new HashSet<Card.Suit>(auxList);
        uniqueSuitCount = auxSet.size();
        
        // add to the deck one of every face/suit combination
        for (Card.Face face : faces) {
            for (Card.Suit suit : suits)
                cards.add(new Card(face, suit));
        }
    }
    
    /**
     * Returns the number of unique Suits in this Deck
     * @return  number of unique suits
     */
    public int getNumberSuits() {
        return this.uniqueSuitCount;
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
        Collections.shuffle(cards, new Random(11777L));
    }
    
    /**
     * Returns the top Card on the deck
     * @return  Card
     */
    public Card drawCard() {
        Card drawnCard = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return drawnCard;
    }
    
    /**
     * Returns a String representation of this Deck
     * @return  String version of Deck
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Interate over each card
        for (int i = 0; i < cards.size() - 1; i++) {
            sb.append(cards.get(i).toString());
            sb.append(((i + 1) % uniqueSuitCount == 0) ? "\n" : "  ");
        }
        sb.append(cards.get(cards.size() - 1).toString());
        return sb.toString();
    }
    
    /**
     * Flips Cards so that none are face up
     */
    public void hideCards() {
        // Iterate over each Card
        for (Card card : cards) {
            card.setIsFaceUp(false);
        }
    }
    
    /**
     * Flips Cards so that all are face up
     */
    public void showCards() {
        // Iterate over each Card
        for (Card card : cards) {
            card.setIsFaceUp(true);
        }
    }
}
