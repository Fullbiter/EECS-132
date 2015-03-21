/**
 * The HW2 class provides static methods for operations on a String.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.3
 */
public class HW2 {
    
    /**
     * Returns a String of characters whose indices on the input String
     * match the sequential powers of the inputs integer.
     * @param  s  String from which chars will be pulled
     * @param  k  integer base whose powers will be iterated on
     * @return    String of characters at indices k<sup>n</sup>
     */
    public static String everykToTheNthChar(String s, int k) {
        if (k >= 2) {
            StringBuilder builder = new StringBuilder();
            k--;
            // TODO: Explain logic
            for (int i = 1; i <= s.length(); i += (k * i))
                builder.append(s.charAt(i - 1));
            return builder.toString();
        }
        else {
            return s;
        }
    }
    
    /**
     * Returns true if the String sequence is present in the input String
     * in order, though not necessarily consecutively.
     * @param  string    String to be searched
     * @param  sequence  target sequence
     * @return           truth value of the sequence's existance in the String
     */
    public static boolean containsSubSequence(String string, String sequence) {
        int j = 0; // index for sequence
        if (sequence.length() < 1)
            return false;
        // TODO: Explain logic
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == sequence.charAt(j)) {
                j++;
                if (j == sequence.length())
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a String with characters removed based on the input sequence
     * @param  string    initial String
     * @param  sequence  character blacklist
     * @return           resultant String
     */
    public static String subtract(String string, String sequence) {
        StringBuilder builder = new StringBuilder();
        int j = 0;
        // TODO: Explain logic
        for (int i = 0; i < string.length(); i++) {
            if (j < sequence.length() && string.charAt(i) == sequence.charAt(j))
                j++;
            else
                builder.append(string.charAt(i));
        }
        return builder.toString();
    }
    
    /**
     * Returns a String as a double if it is properly formatted as a number
     * @param   s   initial String
     * @return      value of the string
     * @exception   NumberFormatException  if the String is not formatted as a number
     */
    public static double doubleValue(String s) throws NumberFormatException {
        
        final int CHAR_OFFSET = 48;  // the number of char values before '1'
        int negOffset = 0;           // int representation of isNegative, used for indexing
        int radixPoint = s.length(); // index of '.'
        double value = 0.0;          // the value to be returned
        double placeValue = 1.0;     // current place value (e.g. ones, tens)
        boolean isNegative = false;  // whether or not the value is negative
        
        if (s.length() < 1)
            throw new NumberFormatException();
        
        // check whether the number is negative
        if (s.charAt(0) == '-')
            isNegative = true;
        negOffset = isNegative ? 1 : 0;
        
        // find the radix point, if one exists in the String
        for (int i = negOffset; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                if (radixPoint != s.length())
                    throw new NumberFormatException();
                radixPoint = i;
            }
            else if (s.charAt(i) < '0' || s.charAt(i) > '9')
                throw new NumberFormatException();
        }
        
        // sum the integer part of the value
        for (int i = 1; radixPoint - i >= negOffset; i++) {
            value += ((int)s.charAt(radixPoint - i) - CHAR_OFFSET) * placeValue;
            placeValue *= 10;
        }
        
        // sum the fractional part of the value
        placeValue = 0.1;
        for (int i = 1; radixPoint + i < s.length(); i++) {
            value += ((int)s.charAt(radixPoint + i) - CHAR_OFFSET) * placeValue;
            placeValue /= 10;
        }
        
        if (isNegative)
            value *= -1;
        return value;
    }
    
    /**
     * Returns a String of the <em>n</em>th letter of each
     * word in the input String
     * @param  s  String from which chars will be pulled
     * @param  n  target letter place
     * @return    String of characters in the <em>n</em>th place of every word
     */
    public static String everyNthCharOfWord(String s, int n) {
        StringBuilder builder = new StringBuilder();
        int j = 0; // the place of the current char within a word
        
        // iterate over input String
        for (int i = 0; i < s.length(); i++) {
            // current char is a letter
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                // 
                if (++j == n)
                    builder.append(s.charAt(i));
            }
            else {
                if (j > 0 && j < n)
                    builder.append(' ');
                j = 0;
            }
        }
        return builder.toString();
    }
    
    /**
     * Returns a version of the input String in which all words are reversed
     * @param  s  String to operate on
     * @return    String with reversed words
     */
    public static String flipWords(String s) {
        StringBuilder builder = new StringBuilder();
        boolean prevIsLetter = false;
        int i = 0; // index: first character after a word, general traversal
        int j = 0; // index: first letter of a word
        int k = 0; // index: traversal between j and i
        
        // iterate over the entire String
        while (i < s.length()) {
            // current char is a letter
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                if (!prevIsLetter)
                    j = i;
                prevIsLetter = true;
            }
            else {
                if (prevIsLetter) {
                    for (k = i - 1; k >= j; k--)
                        builder.append(s.charAt(k));
                }
                builder.append(s.charAt(i));
                prevIsLetter = false;
            }
            i++;
        }
        // necessary when the last char is a letter
        if (prevIsLetter) {
            for (k = i - 1; k >= j; k--)
                builder.append(s.charAt(k));
        }
        return builder.toString();
    }
    
    /***********************************************************************************************************
     *                                 Oh man! Extra credit beyond this Point!                                 *
     ***********************************************************************************************************/
    
    /**
     * Returns a version of the input String in which all words
     * and non-letter sequences are arranged vertically.
     * @param  s  String to operate on
     * @return    String with vertically-arranged words
     */
    public static String transpose(String s) {
        StringBuilder builder = new StringBuilder();
        final int longestWordLength = calcLongestWordLength(s);
        
        // iterate one time fewer than needed
        for (int i = 1; i < longestWordLength; i++) {
            builder.append(everyNthCharSpecial(s, i));
            builder.append("\n");
        }
        // necessary to avoid adding an unecesary trailing newline character
        builder.append(everyNthCharSpecial(s, longestWordLength));
        
        return builder.toString();
    }
    
    /**
     * Returns a String of the <em>n</em>th letter of each
     * word (including non-letter sequences) in the input String
     * @param  s  String to operate on
     * @param  n  Target character place
     * @return    String with reversed words
     */
    private static String everyNthCharSpecial(String s, int n) {
        StringBuilder builder = new StringBuilder();
        int j = 0; // the place of the current char within a word
        int k = 0; // the place of the current char within a non-letter sequence
        
        // iterate over input String
        for (int i = 0; i < s.length(); i++) {
            // current char is a letter
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                if (++j == n)
                    builder.append(s.charAt(i));
            }
            // current char is non-letter non-whitespace
            else if (s.charAt(i) != ' ') {
                if (++k == n)
                    builder.append(s.charAt(i));
            }
            // current char is whitespace
            else {
                if (j > 0 && j < n)
                    builder.append(' ');
                j = 0;
                if (k > 0 && k < n)
                    builder.append(' ');
                k = 0;
            }
        }
        return builder.toString();
    }
    
    /**
     * Returns the length of the longest word (including non-letter sequences) 
     * in the input String
     * @param  s  String to search
     * @return    Length of the longest word
     */
    private static int calcLongestWordLength(String s) {
        int maxWordLength = 0;     // the length of the longest word
        int currentWordLength = 0; // the length of the current ...
        int maxSeqLength = 0;      // the length of the longest non-whitespace sequence
        int currentSeqLength = 0;  // the length of the current ...
        // iterate over input String
        for (int i = 0; i < s.length(); i++) {
            // current char is a letter
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
                currentWordLength++;
            // current char is a non-letter non-whitespace
            else if (s.charAt(i) != ' ')
                currentSeqLength++;
            // current char is whitespace
            else {
                if (currentWordLength > maxWordLength)
                    maxWordLength = currentWordLength;
                if (currentSeqLength > maxSeqLength)
                    maxSeqLength = currentSeqLength;
                currentWordLength = 0;
                currentSeqLength = 0;
            }
        }
        if (maxWordLength == 0 && maxSeqLength == 0) {
            maxWordLength = currentWordLength;
            maxSeqLength = currentSeqLength;
        }
        if (maxSeqLength > maxWordLength)
            return maxSeqLength;
        return maxWordLength;
    }
}