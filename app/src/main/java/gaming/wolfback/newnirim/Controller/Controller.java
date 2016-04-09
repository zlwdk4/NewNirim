package gaming.wolfback.newnirim.Controller;

import android.content.Context;
import android.support.v7.internal.widget.DecorContentParent;
import android.widget.Toast;

/**
 * Created by Jarren on 3/19/2016.
 */
public class Controller {
    public void playCard(int cardNum){
        facade.playCardIntoLabAndRemoveCardFromHand(cardNum);
        facade.updateNightmareCount();
        if(didScore()){
            facade.updateDoorCount();
        }
        facade.drawFromDeckIntoHand();
    }
    public void discardCard(int cardNum){
        facade.discardCardFromHand(cardNum);
        facade.drawFromDeckIntoHand();
    }
    public boolean isValidPlay(int indexOfCardInHand){
        int labSize = facade.getLabSize();
        String curLabType = facade.getLabType(labSize-1);
        String curHandType = facade.getCardTypeFromHand(indexOfCardInHand);
        return (rules.isValidPlayRegardingType(curLabType, curHandType));
    }
    //calls function to see how many cards should be given to rules
    //calls another function to get that many card colors from the labyrinth
    //then passes the card colors and number of cards to rules to see if it's the player scored
    public boolean didScore(){
        int numCards = numCardsToGiveToRules();
        String [] cardColors;
        cardColors = getArrayOfCardColorsFromLab(numCards);
        return rules.didScore(cardColors, numCards);
    }
    //a function to get the number of cards for rules. This will ask facade for the size of the lab
    //depending on the size of the lab, that's how many cards we need to give to rules
    private int numCardsToGiveToRules(){
        int labSize = facade.getLabSize();
        if (labSize < 3){
            return 0;
        }
        else if (labSize==3){
            return 3;
        }
        else if (labSize >=6){
            return 6;
        }
        else return 4;
    }
    //A function to get an array of strings from the lab.
    //These strings will be the colors of the cards in the lab starting with the most recently played card in the lab
    //It will grab as many colors as the parameter it is passed
    private String[] getArrayOfCardColorsFromLab(int numCards){
        int labSize = facade.getLabSize();
        String[] colorOfCardsArray = new String[numCards];
        for (int i = 0; i < numCards; ++i){
            colorOfCardsArray[i] = facade.getLabColor(labSize - 1 - i);
        }
        return colorOfCardsArray;
    }

    public int getRedDoorCount(){
        return facade.getRedDoorCount();
    }
    public int getGreenDoorCount(){
        return facade.getGreenDoorCount();
    }
    public int getBlueDoorCount(){
        return facade.getBlueDoorCount();
    }
    public int getBrownDoorCount(){
        return facade.getBrownDoorCount();
    }

    public String getColorAndTypeOfTopDiscard(){
        return facade.getColorAndTypeOfTopDiscard();
    }

    public String getCardColorAndTypeFromHand(int cardNum){
        return facade.getCardColorAndTypeFromHand(cardNum);
    }
    public String getCardColorAndTypeFromLab(int cardNum){
        return facade.getCardColorAndTypeFromLab(cardNum);
    }
    public String getCardTypeFromHand(int cardNum){
        return facade.getCardTypeFromHand(cardNum);
    }
    public int getNightmareCount(){
        return facade.getNightmareCount();
    }
    private Facade facade = new Facade();
    private Rules rules = new Rules();

    public void prophecize(int cardNum) {

    }


}
