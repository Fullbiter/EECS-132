/**
 * The UnitTesting class provides JUnit testing for Project 5.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.24
 */
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UnitTesting {
    
    /**
     * Tests the creation of every kind of Card (default number is 52)
     */
    @Test
    public void cardConstructor() {
        
        Card.Face[] allFaces = Card.Face.values();
        Card.Suit[] allSuits = Card.Suit.values();
        String[] testStrings = new String[allFaces.length * allSuits.length];
        
        // Cards face up
        String[] expectedStrings = {"AS", "AH", "AD", "AC", "2S", "2H", "2D", "2C", "3S", "3H", "3D", "3C", "4S", "4H",
                                    "4D", "4C", "5S", "5H", "5D", "5C", "6S", "6H", "6D", "6C", "7S", "7H", "7D", "7C",
                                    "8S", "8H", "8D", "8C", "9S", "9H", "9D", "9C", "10S", "10H", "10D", "10C", "JS",
                                    "JH", "JD", "JC", "QS", "QH", "QD", "QC", "KS", "KH", "KD", "KC"};
        int index = 0;
        for (Card.Face face : allFaces) {
            for (Card.Suit suit : allSuits)
                testStrings[index++] = new Card(face, suit).toString();
        }
        assertArrayEquals("One or more face up cards are displayed improperly", expectedStrings, testStrings);
        
        // Cards face down
        expectedStrings = new String[]{"XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX",
                                       "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX",
                                       "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX",
                                       "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX"};
        index = 0;
        for (Card.Face face : allFaces) {
            for (Card.Suit suit : allSuits)
                testStrings[index++] = new Card(face, suit, false).toString();
        }
        assertArrayEquals("One or more face down cards are displayed improperly", expectedStrings, testStrings);
    }
    
    /**
     * Tests the getter/setter methods of Card
     */
    @Test
    public void cardGetterSetter() {
        Card card1 = new Card("ace", "spades");
        Card card2 = new Card("ace", "spades");
        Card card3 = new Card("two", "hearts");
        // "ace" = "ace"
        assertTrue("Error in method getFace", card1.getFace().equals(card2.getFace()));
        // "ace" = "two"
        assertFalse("Error in method getFace", card1.getFace().equals(card3.getFace()));
        // "spades" = "spades"
        assertTrue("Error in method getSuit", card1.getSuit().equals(card2.getSuit()));
        // "spades" = "hearts"
        assertFalse("Error in method getSuit", card1.getSuit().equals(card3.getSuit()));
        card1.setIsFaceUp(true);
        assertTrue("Getter incorrectly states that the card is face down", card1.getIsFaceUp());
        card1.setIsFaceUp(false);
        assertFalse("Getter incorrectly states that the card is face up", card1.getIsFaceUp());
    }
    
    /**
     * Test the creation of different Decks
     */
    @Test
    public void deckConstructor() {
        // default
        Deck deck = new Deck();
        assertEquals("The default Deck is not displayed properly",
                     "AS  AH  AD  AC\n2S  2H  2D  2C\n3S  3H  3D  3C\n4S  4H  4D  4C\n" +
                     "5S  5H  5D  5C\n6S  6H  6D  6C\n7S  7H  7D  7C\n8S  8H  8D  8C\n" +
                     "9S  9H  9D  9C\n10S  10H  10D  10C\nJS  JH  JD  JC\nQS  QH  QD  QC\n" +
                     "KS  KH  KD  KC", deck.toString());
        // test first edge of Faces and Suits
        deck = new Deck(Card.Face.ACE, Card.Face.THREE, Card.Suit.SPADES, Card.Suit.HEARTS);
        assertEquals("A Deck of first edge Faces and Suits is not displayed properly",
                     "AS  AH\n2S  2H\n3S  3H", deck.toString());
        // test middle of Faces and Suits
        deck = new Deck(Card.Face.SIX, Card.Face.EIGHT, Card.Suit.HEARTS, Card.Suit.DIAMONDS);
        assertEquals("A Deck of middle Faces and Suits is not displayed properly",
                     "6H  6D\n7H  7D\n8H  8D", deck.toString());
        // test last edge of Faces and Suits
        deck = new Deck(Card.Face.JACK, Card.Face.KING, Card.Suit.DIAMONDS, Card.Suit.CLUBS);
        assertEquals("A Deck of last edge Faces and Suits is not displayed properly",
                     "JD  JC\nQD  QC\nKD  KC", deck.toString());
        // test one Face and one Suit
        deck = new Deck(Card.Face.ACE, Card.Face.ACE, Card.Suit.SPADES, Card.Suit.SPADES);
        assertEquals("A Deck of last edge Faces and Suits is not displayed properly",
                     "AS\nAS", deck.toString());
    }
    
    /**
     * Tests the getter methods of Deck
     */
    @Test
    public void deckGetter() {
        // default
        Deck deck = new Deck();
        assertEquals("Method getNumberSuits does not count correctly", 4, deck.getNumberSuits());
        assertEquals("Method getMinFace returns an incorrect Face", Card.Face.ACE, deck.getMinFace());
        assertEquals("Method getMaxFace returns an incorrect Face", Card.Face.KING, deck.getMaxFace());
        // duplicate Suits and one Face
        deck = new Deck(Card.Face.ACE, Card.Face.ACE, Card.Suit.SPADES, Card.Suit.SPADES);
        assertEquals("Method getNumberSuits counts duplicate Suits", 1, deck.getNumberSuits());
        assertEquals("Method getMinFace returns an incorrect Face", Card.Face.ACE, deck.getMinFace());
        assertEquals("Method getMaxFace returns an incorrect Face", Card.Face.ACE, deck.getMaxFace());
    }
}
