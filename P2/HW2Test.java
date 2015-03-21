/**
 * The HW2Test class provides unit testing for HW2.java.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.3
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HW2Test {
    
    // Adds a rule for exception checking
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    // Tests everykToTheNthChar method in class HW2
    @Test
    public void everykToTheNthChar() {        
        String input = "123456789";
        int n = 1;
        assertEquals("When n < 2: the output String should match the input String",
                     "123456789", HW2.everykToTheNthChar(input, n));
        n = 2;
        assertEquals("When n = 2: the output String should contain the 1st, 2nd, 4th, and 8th chars",
                     "1248", HW2.everykToTheNthChar(input, n));
        n = 3;
        assertEquals("When n = 3: the output String should contain the 1st, 3rd, and 9th chars",
                     "139", HW2.everykToTheNthChar(input, n));
        input = "";
        assertEquals("When the input String is empty: the output String should be empty",
                     "", HW2.everykToTheNthChar(input, n));
    }
    
    // Tests containsSubSequence method in class HW2
    @Test
    public void containsSubSequence() {        
        String s1 = "abc";
        String s2 = "a";
        assertTrue("The sequence includes the first character: true",
                   HW2.containsSubSequence(s1, s2));
        s2 = "b";
        assertTrue("The sequence includes only middle characters: true",
                   HW2.containsSubSequence(s1, s2));
        s2 = "c";
        assertTrue("The sequence includes the last character: true",
                   HW2.containsSubSequence(s1, s2));
        s2 = "abc";
        assertTrue("The sequence is identical to the input String: true",
                   HW2.containsSubSequence(s1, s2));
        s2 = "def";
        assertFalse("The input String does not contain the sequence: false",
                   HW2.containsSubSequence(s1, s2));
        s2 = "acb";
        assertFalse("The sequence is in the wrong order: false",
                   HW2.containsSubSequence(s1, s2));
        s2 = "abc~";
        assertFalse("The sequence is longer than the input String: false",
                   HW2.containsSubSequence(s1, s2));
        s1 = "";
        s2 = "abc";
        assertFalse("The input String is empty: false",
                   HW2.containsSubSequence(s1, s2));
        s1 = "abc";
        s2 = "";
        assertFalse("The sequence is empty: false",
                   HW2.containsSubSequence(s1, s2));
    }
    
    // Tests subtract method in class HW2
    @Test
    public void subtract() {        
        String s1 = "abc";
        String s2 = "ab";
        assertEquals("Subtraction at the beginning of the String",
                     "c", HW2.subtract(s1, s2));
        s2 = "bc";
        assertEquals("Subtraction at the end of the String",
                     "a", HW2.subtract(s1, s2));
        s2 = "ac";
        assertEquals("Subtraction at the endpoints of the String",
                     "b", HW2.subtract(s1, s2));        
        s2 = "def";
        assertEquals("Subtraction of non-existant chars",
                     "abc", HW2.subtract(s1, s2));
        s2 = "abc";
        assertEquals("Subtraction of the entire input String",
                     "", HW2.subtract(s1, s2));
        s2 = "abc~";
        assertEquals("Subtraction is longer than the input String",
                     "", HW2.subtract(s1, s2));
        s1 = "";
        s2 = "abc";
        assertEquals("Subtraction from an empty String",
                     "", HW2.subtract(s1, s2));
        s1 = "abc";
        s2 = "";
        assertEquals("Subtraction of an empty String",
                     "abc", HW2.subtract(s1, s2));
    }
    
    // Tests doubleValue method in class HW2
    @Test
    public void doubleValue() {
        String input = "123";
        assertEquals("Conversion of an integer",
                     123.0, HW2.doubleValue(input), 0.0001);
        input = "-123";
        assertEquals("Conversion of a negative integer",
                     -123.0, HW2.doubleValue(input), 0.0001);
        input = "12.3";
        assertEquals("Conversion of a non-integer",
                     12.3, HW2.doubleValue(input), 0.0001);
        input = "-12.3";
        assertEquals("Conversion of a negative non-integer",
                     -12.3, HW2.doubleValue(input), 0.0001);
        input = ".123";
        assertEquals("Conversion given a leading decimal point",
                     0.123, HW2.doubleValue(input), 0.0001);
        input = "123.";
        assertEquals("Conversion given a trailing decimal point",
                     123.0, HW2.doubleValue(input), 0.0001);
        input = "0123";
        assertEquals("Conversion given a leading zero",
                     123.0, HW2.doubleValue(input), 0.0001);
        input = "123.00";
        assertEquals("Conversion given a trailing zero",
                     123.0, HW2.doubleValue(input), 0.0001);
        
        // Conversion of an empty String
        thrown.expect(NumberFormatException.class);
        input = "";
        HW2.doubleValue(input);
        
        // Conversion of an improperly formatted String
        thrown.expect(NumberFormatException.class);
        input = "1.2.3";
        HW2.doubleValue(input);
        
        // Conversion of an String with an illegal char
        thrown.expect(NumberFormatException.class);
        input = "1.0f";
        HW2.doubleValue(input);
    }
    
    // Tests everyNthCharOfWord method in class HW2
    @Test
    public void everyNthCharOfWord() {
        String input = "a ab abc abcd";
        int place = 1;
        assertEquals("First letter of each word",
                     "aaaa", HW2.everyNthCharOfWord(input, place));
        place = 2;
        assertEquals("Second letter of each word",
                     " bbb", HW2.everyNthCharOfWord(input, place));
        place = 3;
        assertEquals("Third letter of each word",
                     "  cc", HW2.everyNthCharOfWord(input, place));
        place = 4;
        assertEquals("Fourth letter of each word",
                     "   d", HW2.everyNthCharOfWord(input, place));
        place = 5;
        assertEquals("All word lengths are less than n",
                     "   ", HW2.everyNthCharOfWord(input, place));
        place = 0;
        assertEquals("The place is not a postive number",
                     "", HW2.everyNthCharOfWord(input, place));
        input = "";
        place = 1;
        assertEquals("The input String is empty",
                     "", HW2.everyNthCharOfWord(input, place));
    }
    
    // Tests flipWords method in class HW2
    @Test
    public void flipWords() {
        String input = "abc";
        assertEquals("Conversion of a single word",
                     "cba", HW2.flipWords(input));
        input = "abc abc";
        assertEquals("Conversion of a two words separated by a whitespace char",
                     "cba cba", HW2.flipWords(input));
        input = "abc-abc";
        assertEquals("Conversion of a two words separated by a non-whitespace char",
                     "cba-cba", HW2.flipWords(input));
        input = "#swag";
        assertEquals("Conversion of a word with a leading non-whitespace char",
                     "#gaws", HW2.flipWords(input));
        input = "bang!";
        assertEquals("Conversion of a word with a trailing non-whitespace char",
                     "gnab!", HW2.flipWords(input));
        input = "";
        assertEquals("Conversion of an empty String",
                     "", HW2.flipWords(input));
    }
    
    /***********************************************************************************************************
     *                                 Oh man! Extra credit beyond this Point!                                 *
     ***********************************************************************************************************/
    
    // Tests transponse method in class HW2
    @Test
    public void transpose() {
        // The first test is on an exact copy of the example given on the instructions page,
        // given because this method's output is more complex
        String input = "Hello everyone!!!   This is $100,000 worth of fun!";
        assertEquals("Conversion of the instructions example",
                     "He!Ti$wof!\nev!hs1ofu\nle!i 0r n\nlr s 0t \noy   ,h \n o   0  \n n   0  \n e   0  ",
                     HW2.transpose(input));
        input = "a";
        assertEquals("Conversion of a single char",
                     "a", HW2.transpose(input));
        input = "";
        assertEquals("Conversion of an empty String",
                     "", HW2.transpose(input));
    }
    
    // Tests on helper methods used in the extra credit have been omitted
}