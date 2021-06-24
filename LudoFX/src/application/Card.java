package application;

public class Card {


    /**
     * Every new card created in the Controller.java (the class with the long list of cards) will be forced to take on paremeter.
     * That is the directory of the image.
     *
     * This will take the image directory that's in the parenthesis, and store it inside 'this.imageDir'
     */

    String imageDir; //this.imageDir
    int cardValue;

    public Card(String imageDir, int cardValue) {
        this.imageDir = imageDir;
        this.cardValue = cardValue;
    }
}
