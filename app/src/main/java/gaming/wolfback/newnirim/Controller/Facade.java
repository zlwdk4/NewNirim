package gaming.wolfback.newnirim.Controller;

import gaming.wolfback.newnirim.Model.Counts;
import gaming.wolfback.newnirim.Model.Hand;
import gaming.wolfback.newnirim.Model.Labyrinth;
import gaming.wolfback.newnirim.Utility.Card;
import gaming.wolfback.newnirim.Model.DiscardPile;
import gaming.wolfback.newnirim.Model.DrawPile;

/**
 * Created by Jarren on 2/20/2016.
 */
public class Facade {
    public Facade(){
        String sun = "sun", moon = "moon", key = "key", blue = "blue", brown = "brown",
                green = "green", red = "red", nightmare = "nightmare", door = "door";

        int i = 1;

        for (int j = 0; j < 9; j++) {
            Card c = new Card(i, red, sun);
            drawPile.addCardToDeck(c);
            i++;
        }


        for (int j = 0; j < 8; j++) {
            Card c = new Card(i, blue, sun);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 7; j++) {
            Card c = new Card(i, green, sun);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 6; j++) {
            Card c = new Card(i, brown, sun);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 4; j++) {
            Card c = new Card(i, red, moon);
            i++;
            Card c1 = new Card(i, blue, moon);
            i++;
            Card c2 = new Card(i, green, moon);
            i++;
            Card c3 = new Card(i, brown, moon);
            i++;
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);

        }

        for (int j = 0; j < 3; j++) {
            Card c = new Card(i, red, key);
            i++;
            Card c1 = new Card(i, blue, key);
            i++;
            Card c2 = new Card(i, green, key);
            i++;
            Card c3 = new Card(i, brown, key);
            i++;
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);

        }

        for (int j = 0; j < 10; j++) {
            Card c = new Card(i, nightmare, nightmare);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 2; j++) {
            Card c = new Card(i, red, door);
            i++;
            Card c1 = new Card(i, blue, door);
            i++;
            Card c2 = new Card(i, green, door);
            i++;
            Card c3 = new Card(i, brown, door);
            i++;
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);
        }
        drawPile.shuffle();
        drawPile.shuffle();
        drawPile.shuffle();

        int j = 0;
        while (j!=5){
            drawFromDeckIntoHandInitial();
            j++;
        }

    }
    //**************************Hand stuff***********************************************//
    public String getCardColorAndTypeFromHand(int indexOfCard){
        String colorAndTypeOfCard = (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
    }

    public String getCardTypeFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getType());
    }

    public String getCardColorFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getColor());
    }

    public void discardCardFromHand(int indexOfCardInHand){
        discardPile.addCardToDiscard(hand.removeCard(indexOfCardInHand));
    }

    //****************************Lab stuff**********************************//
    public String getCardColorAndTypeFromLab(int indexOfCard){
        String colorAndTypeOfCard = (lab.getCard(indexOfCard).getColor()+ lab.getCard(indexOfCard).getType());
        if (colorAndTypeOfCard.equals("nullnull")){
            return "null";
        }
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
    }
    public int getLabSize(){
        return lab.getSize();
    }
    public String getLabColor(int indexOfCard){
        return lab.getCard(indexOfCard).getColor();
    }
    public String getLabType(int indexOfCard){
        return lab.getCard(indexOfCard).getType();
    }
    //************************Deck stuff*******************************//
    public String getCardTypeFromDeck(int offset){
        return drawPile.top(offset).getType();
    }
    //**********************Interaction stuff**************************//

    public void drawFromDeckIntoHandInitial(){
        int offset = 0;
        boolean nightmareOrDoorWasDrawn = false;
        while (getCardTypeFromDeck(offset).equals("nightmare") || getCardTypeFromDeck(offset).equals("door") ){
            offset++;
            nightmareOrDoorWasDrawn = true;
        }
        Card tempCard = drawPile.draw(offset);
        tempCard.setIsCardDrawn(true);
        hand.addCard(tempCard);

        if(nightmareOrDoorWasDrawn)
            drawPile.shuffle();
    }

    public void drawFromDeckIntoHand(){
        int offset = 0;
        boolean doorWasDrawn = false;
        while (getCardTypeFromDeck(offset).equals("door") ){
            offset++;
            doorWasDrawn = true;
        }
        Card tempCard = drawPile.draw(offset);
        tempCard.setIsCardDrawn(true);
        hand.addCard(tempCard);
        if(doorWasDrawn){
            drawPile.shuffle();
        }
    }

    public void playCardIntoLabAndRemoveCardFromHand(int indexOfCardInHand) {
        lab.addCard(hand.removeCard(indexOfCardInHand));
    }


    //*********************door stuff*****************************//
    public int getRedDoorCount() {
        return counts.getRedDoorCount();
    }

    public int getBlueDoorCount() {
        return counts.getBlueDoorCount();
    }

    public int getGreenDoorCount() {
        return counts.getGreenDoorCount();
    }

    public int getBrownDoorCount() {
        return counts.getBrownDoorCount();
    }

    public void updateDoorCount (){
        String colorOfCard = lab.getCard(lab.getSize() - 1).getColor();
        if (colorOfCard.equals("red") && counts.getRedDoorCount() <= 1) {
            counts.incrementRedDoorCount();
            return;
        } else if (colorOfCard.equals("blue")&& counts.getBlueDoorCount() <= 1) {
            counts.incrementBlueDoorCount();
            return;
        } else if (colorOfCard.equals("green")&& counts.getGreenDoorCount() <= 1) {
            counts.incrementGreenDoorCount();
            return;
        } else if (colorOfCard.equals("brown")&& counts.getBrownDoorCount() <= 1) {
            counts.incrementBrownDoorCount();
            return;
        }
    }
    //*********************nightmare stuff*****************************//
    public void updateNightmareCount(){
        if ((lab.getCard(lab.getSize()-1)).getType().equals("nightmare")){
            counts.incrementNightmareCount();
        }
    }
    public int getNightmareCount(){
        return counts.getNightmareCount();
    }
    //*****************Discard Pile stuff***************************//
    public String getColorAndTypeOfTopDiscard(){
        String colorAndType = (discardPile.top().getColor() + discardPile.top().getType());
        if (colorAndType.equals("nightmarenightmare")){
            colorAndType = "nightmare";
        }
        return colorAndType;
    }
    //****************Private Variables***************************///
    private DrawPile drawPile = new DrawPile();
    private Hand hand = new Hand();
    private Labyrinth lab = new Labyrinth();
    private DiscardPile discardPile = new DiscardPile();
    private Counts counts = new Counts();
}
