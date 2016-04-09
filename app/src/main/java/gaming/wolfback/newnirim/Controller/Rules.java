package gaming.wolfback.newnirim.Controller;

/**
 * Created by Jarren on 3/12/2016.
 */
public class Rules {
    public boolean isValidPlayRegardingType(String prevCardType, String currentCardType) {
        if (prevCardType.equals("null"))
            return true;
        else
            return !(prevCardType.equals(currentCardType));
    }

    public boolean isSequenceOfThreeOfSameColor(String c1Color, String c2Color, String c3Color) {
        if (c1Color.equals(c2Color) && c2Color.equals(c3Color)) {
            return true;
        } else return false;
    }

    public boolean didScore(String[] colorOfCards, int numCards) {
        boolean didScore = false;
        if(numCards < 3){
            return false;
        }
        if (isSequenceOfThreeOfSameColor(colorOfCards[0], colorOfCards[1], colorOfCards[2])) {
            didScore = true;
            if (numCards >= 4 && colorOfCards[0].equals(colorOfCards[3])) {
                didScore = false;
                if (numCards == 6 && colorOfCards[0].equals(colorOfCards[5])) {
                    didScore = true;
                }
            }
        }
        return didScore;
    }

}

