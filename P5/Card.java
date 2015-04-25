/**
 * Card objects represent a playing card.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.24
 */
public class Card {
    
    /** Whether the card is face up or face down **/
    private boolean isFaceUp = true;
    
    /** The face value of the card **/
    private Face face;
    
    /** The suit of the card **/
    private Suit suit;
    
    /** enum for the possible face values of the card **/
    private enum Face {
        ACE("Ace"), TWO("Two"), THREE("Three"), FOUR("Four"), FIVE("Five"), SIX("Six"), SEVEN("Seven"),
        EIGHT("Eight"), NINE("Nine"), TEN("Ten"), JACK("Jack"), QUEEN("Queen"), KING("King");
        
        /** the name of the Face **/
        private String name;
        /** the symbol of the Face **/
        private String symbol;
        
        private Face(String name) {
            this.name = name;
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
         * @param  name  the name of the Face
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
         * Returns the symbolic representation of the Face
         * @return Face symbol
         */
        @Override
        public String toString() {
            return this.symbol;
        }
    }
    
    /** enum for the possible suits of the card **/
    private enum Suit {
        SPADES("Spades"), HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs");
        
        /** the name of the Suit **/
        private String name;
        /** the symbol of the Suit **/
        private String symbol;
        /** the color of the Suit **/
        private String color;
        
        /**
         * Constructs a Suit
         * @param  name  Suit name
         */
        private Suit(String name) {
            this.name = name;
            switch (name.toLowerCase()) {
                case "spades":
                    this.symbol = "\u2660";
                    this.color = "black";
                    break;
                case "hearts":
                    this.symbol = "\u2665";
                    this.color = "red";
                    break;
                case "diamonds":
                    this.symbol = "\u2666";
                    this.color = "red";
                    break;
                case "clubs":
                    this.symbol = "\u2663";
                    this.color = "black";
                    break;
            }
        }
        
        /**
         * Returns the color of the Suit
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
         * Returns the symbolic representation of the Suit
         * @return Suit symbol
         */
        @Override
        public String toString() {
            return this.symbol;
        }
    }
    
    /**
     * Constructs a Card
     * @param  face  the face value of the card
     * @param  suit  the suit of the card
     */
    public Card(String face, String suit) {
        this(face, suit, true);
    }
    
    /**
     * Constructs a Card
     * @param  face      the face value of the card
     * @param  suit      the suit of the card
     * @param  isFaceUp  whether the card is face up
     */
    public Card(String face, String suit, Boolean isFaceUp) {
        this.face = Face.getFaceByName(face);
        this.suit = Suit.getSuitByName(suit);
        this.isFaceUp = isFaceUp;
    }
    
    /**
     * Returns the Face of the Card
     * @return Card Face
     */
    public Face getFace() {
        return this.face;
    }
    
    /**
     * Returns the Suit of the Card
     * @return Card Suit
     */
    public Suit getSuit() {
        return this.suit;
    }
    
    /**
     * Returns the flip status of the Card
     * @return Card flip status
     */
    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }
    
    /**
     * Sets the flip status of the Card
     * @param  b  Card flip status
     */
    public void setIsFaceUp(boolean b) {
        this.isFaceUp = b;
    }
    
    /**
     * Returns the symbolic representation of the Card
     * @return Card symbol
     */
    @Override
    public String toString() {
        return this.face.toString() + this.suit.toString();
    }
}
