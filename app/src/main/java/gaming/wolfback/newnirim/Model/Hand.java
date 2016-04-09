package gaming.wolfback.newnirim.Model;

import gaming.wolfback.newnirim.Utility.Card;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class Hand {
    private static Card[] hand;
    private final int maxCardsInHand = 5;

    public Hand(){
       hand = new Card[5];
        hand[0] = null;
        hand[1] = null;
        hand[2] = null;
        hand[3] = null;
        hand[4] = null;

    }
    public Hand(Card c0, Card c1, Card c2, Card c3, Card c4){
            hand[0] = c0;
            hand[1] = c1;
            hand[2] = c2;
            hand[3] = c3;
            hand[4] = c4;

    }

    public void addCard(Card newCard){
        int i = 0;
        while(i!=maxCardsInHand && hand[i] != null){
            i++;
        }
        if (i==5){
            return;
        }
        else
        hand[i] = newCard;
    }


    public Card removeCard(int index){
        Card tempCard = hand[index];
        hand[index] = null;
        return tempCard;
    }
    public Card getCard (int index){
        if (index >= 0 && index < hand.length)
            return hand[index];
        else{
            Card nullCard = new Card(0, "null", "null");
            return nullCard;
        }
    }
}


