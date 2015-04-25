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
        final int FACES_COUNT = 13;
        final int SUITS_COUNT = 4;
        String[] expectedStrings = new String[FACES_COUNT * SUITS_COUNT];
        String[] testStrings = new String[FACES_COUNT * SUITS_COUNT];
        String expectedFace = "";
        String expectedSuit = "";
        String testFace = "";
        String testSuit = "";
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
                            expectedSuit = "\u2660";
                            break;
                    case 2: testSuit = "hearts";
                            expectedSuit = "\u2665";
                            break;
                    case 3: testSuit = "diamonds";
                            expectedSuit = "\u2666";
                            break;
                    case 4: testSuit = "clubs";
                            expectedSuit = "\u2663";
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
        Card card = new Card("ace", "spades");
        card.getFace();
        card.getSuit();
        card.setIsFaceUp(true);
        assertTrue("Getter incorrectly states that the card is face down", card.getIsFaceUp());
        card.setIsFaceUp(false);
        assertFalse("Getter incorrectly states that the card is face up", card.getIsFaceUp());
    }
}
