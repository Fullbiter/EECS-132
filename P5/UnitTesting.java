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
        
        // Number of unique face values
        final int FACES_COUNT = 13;
        // Number of unique suits
        final int SUITS_COUNT = 4;
        
        Card.Face[] allFaces = Card.Face.values();
        Card.Suit[] allSuits = Card.Suit.values();
        // Expected array to be compared against
        String[] expectedStrings = new String[FACES_COUNT * SUITS_COUNT];
        // Experimental array to compare
        String[] testStrings = new String[FACES_COUNT * SUITS_COUNT];
        // Placeholder for the expected Face
        String expectedFace = "";
        // Placeholder for the expected Suit
        String expectedSuit = "";
        // Placeholder for the experimental Face
        String testFace = "";
        // Placeholder for the experimental Suit
        String testSuit = "";
        // The current index
        int index = 0;
        
        // For each face ...
        for (int i = 1; i <= FACES_COUNT; i++) {
            switch (i) {
                case 1:  testFace = "ace";
                         expectedFace = "A";
                         break;
                case 2:  testFace = "two";
                         expectedFace = "2";
                         break;
                case 3:  testFace = "three";
                         expectedFace = "3";
                         break;
                case 4:  testFace = "four";
                         expectedFace = "4";
                         break;
                case 5:  testFace = "five";
                         expectedFace = "5";
                         break;
                case 6:  testFace = "six";
                         expectedFace = "6";
                         break;
                case 7:  testFace = "seven";
                         expectedFace = "7";
                         break;
                case 8:  testFace = "eight";
                         expectedFace = "8";
                         break;
                case 9:  testFace = "nine";
                         expectedFace = "9";
                         break;
                case 10: testFace = "ten";
                         expectedFace = "10";
                         break;
                case 11: testFace = "jack";
                         expectedFace = "J";
                         break;
                case 12: testFace = "queen";
                         expectedFace = "Q";
                         break;
                case 13: testFace = "king";
                         expectedFace = "K";
                         break;
            }
            // ... and each suit ...
            for (int j = 1; j <= SUITS_COUNT; j++) {
                switch (j) {
                    case 1: testSuit = "spades";
//                            expectedSuit = "\u2660";
                            expectedSuit = "S";
                            break;
                    case 2: testSuit = "hearts";
//                            expectedSuit = "\u2665";
                            expectedSuit = "H";
                            break;
                    case 3: testSuit = "diamonds";
//                            expectedSuit = "\u2666";
                            expectedSuit = "D";
                            break;
                    case 4: testSuit = "clubs";
//                            expectedSuit = "\u2663";
                            expectedSuit = "C";
                            break;
                }
                // ... create a card with the current combination
                testStrings[index] = new Card(testFace, testSuit).toString();
                expectedStrings[index++] = expectedFace + expectedSuit;
            }
        }
        assertArrayEquals("One or more cards are displayed improperly", expectedStrings, testStrings);
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
    }
    
    /**
     * Tests the getter methods of Deck
     */
//    @Test
//    public void deckGetter() {
//        Deck deck = new Deck("ace", "spades");
//        // "ace" = "ace"
//        assertTrue("Error in method getFace", card1.getFace().equals(card2.getFace()));
//        // "ace" = "two"
//        assertFalse("Error in method getFace", card1.getFace().equals(card3.getFace()));
//        // "spades" = "spades"
//        assertTrue("Error in method getSuit", card1.getSuit().equals(card2.getSuit()));
//        // "spades" = "hearts"
//        assertFalse("Error in method getSuit", card1.getSuit().equals(card3.getSuit()));
//        card1.setIsFaceUp(true);
//        assertTrue("Getter incorrectly states that the card is face down", card1.getIsFaceUp());
//        card1.setIsFaceUp(false);
//        assertFalse("Getter incorrectly states that the card is face up", card1.getIsFaceUp());
//    }
}
