/**
 * Card objects represent a playing card.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.24
 */
public class Card {
    
    /** Whether this card is face up or face down **/
    private boolean isFaceUp = true;
    
    /** The face value of this card **/
    private Face face;
    
    /** The suit of this card **/
    private Suit suit;
    
    /**
     * Constructs a Card given a literal Face and Suit
     * @param  face  the face value of this card
     * @param  suit  the suit of this card
     */
    public Card(Face face, Suit suit) {
        this(face, suit, true);
    }
    
    /**
     * Constructs a Card given a literal Face and Suit and specifies flip status
     * @param  face      the face value of this card
     * @param  suit      the suit of this card
     * @param  isFaceUp  whether this card is face up
     */
    public Card(Face face, Suit suit, Boolean isFaceUp) {
        this.face = face;
        this.suit = suit;
        this.isFaceUp = isFaceUp;
    }
    
    /**
     * Constructs a Card given names of a Face and Suit
     * @param  face  the face value of this card
     * @param  suit  the suit of this card
     */
    public Card(String face, String suit) {
        this(face, suit, true);
    }
    
    /**
     * Constructs a Card given names of a Face and Suit and specifies flip status
     * @param  face      the face value of this card
     * @param  suit      the suit of this card
     * @param  isFaceUp  whether this card is face up
     */
    public Card(String face, String suit, Boolean isFaceUp) {
        this.face = Face.getFaceByName(face);
        this.suit = Suit.getSuitByName(suit);
        this.isFaceUp = isFaceUp;
    }
    
    /**
     * Returns the Face of this Card
     * @return Card Face
     */
    public Face getFace() {
        return this.face;
    }
    
    /**
     * Returns the Suit of this Card
     * @return Card Suit
     */
    public Suit getSuit() {
        return this.suit;
    }
    
    /**
     * Returns the flip status of this Card
     * @return Card flip status
     */
    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }
    
    /**
     * Sets the flip status of this Card
     * @param  b  Card flip status
     */
    public void setIsFaceUp(boolean b) {
        this.isFaceUp = b;
    }
    
    /**
     * Returns the symbolic representation of this Card
     * @return Card symbol
     */
    @Override
    public String toString() {
        if (isFaceUp)
            return this.face.toString() + this.suit.toString();
        return "XX";
    }
    
    /** enum for the possible face values of this card **/
    public enum Face {
        ACE("Ace"), TWO("Two"), THREE("Three"), FOUR("Four"), FIVE("Five"), SIX("Six"), SEVEN("Seven"),
        EIGHT("Eight"), NINE("Nine"), TEN("Ten"), JACK("Jack"), QUEEN("Queen"), KING("King");
        
        /** the name of this Face **/
        private String name;
        /** the symbol of this Face **/
        private String symbol;
        
        /**
         * Constructs a Face
         * @param  name  the name of the face value of this card
         */
        private Face(String name) {
            this.name = name;
            // convert the name to a symbol
            switch (name.toLowerCase()) {
                case "ace":   this.symbol = "A";
                    break;
                case "two":   this.symbol = "2";
                    break;
                case "three": this.symbol = "3";
                    break;
                case "four":  this.symbol = "4";
                    break;
                case "five":  this.symbol = "5";
                    break;
                case "six":   this.symbol = "6";
                    break;
                case "seven": this.symbol = "7";
                    break;
                case "eight": this.symbol = "8";
                    break;
                case "nine":  this.symbol = "9";
                    break;
                case "ten":   this.symbol = "10";
                    break;
                case "jack":  this.symbol = "J";
                    break;
                case "queen": this.symbol = "Q";
                    break;
                case "king":  this.symbol = "K";
                    break;
            }
        }
        
        /**
         * Returns the Face bearing the given name
         * @param  name  the name of this Face
         * @return  Face of name
         */        
        public static Face getFaceByName(String name) throws IllegalArgumentException {
            try {
                return Face.valueOf(name.toUpperCase());
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The face value \"" + name + "\" was not found", e);
            }
        }
        
        /**
         * Returns the symbolic representation of this Face
         * @return Face symbol
         */
        @Override
        public String toString() {
            return this.symbol;
        }
    }
    
    /** enum for the possible suits of this card **/
    public enum Suit {
        SPADES("Spades"), HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs");
        
        /** the name of this Suit **/
        private String name;
        /** the symbol of this Suit **/
        private String symbol;
        /** the color of this Suit **/
        private String color;
        
        /**
         * Constructs a Suit
         * @param  name  the name of the suit of this card
         */
        private Suit(String name) {
            this.name = name;
            // convert the name to a symbol
            // NOTE: if your IDE font does not support the Unicode below, use the commented lines instead
            switch (name.toLowerCase()) {
                case "spades":
                    this.symbol = "\u2660";
//                    this.symbol = "S";
                    this.color = "black";
                    break;
                case "hearts":
                    this.symbol = "\u2764";
//                    this.symbol = "H";
                    this.color = "red";
                    break;
                case "diamonds":
                    this.symbol = "\u2666";
//                    this.symbol = "D";
                    this.color = "red";
                    break;
                case "clubs":
                    this.symbol = "\u2663";
//                    this.symbol = "C";
                    this.color = "black";
                    break;
            }
        }
        
        /**
         * Returns the color of this Suit
         * @return  Suit color
         */
        public String getColor() {
            return this.color;
        }
        
        /**
         * Returns the Suit bearing the given name
         * @param  name  the name of the Suit
         * @return  Suit of name
         * @throws  IllegalArgumentException  given an illegal name
         */
        public static Suit getSuitByName(String name) throws IllegalArgumentException {
            try {
                return Suit.valueOf(name.toUpperCase());
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The suit value \"" + name + "\" was not found", e);
            }
        }
        
        /**
         * Returns the symbolic representation of this Suit
         * @return Suit symbol
         */
        @Override
        public String toString() {
            return this.symbol;
        }
    }
}
