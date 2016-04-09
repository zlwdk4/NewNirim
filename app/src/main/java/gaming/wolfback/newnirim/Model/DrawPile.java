package gaming.wolfback.newnirim.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gaming.wolfback.newnirim.Utility.Card;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class DrawPile {
    private static List<Card> deck;
    public DrawPile(){
        deck = new ArrayList<Card>();
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    public int getDeckCount(){
        return deck.size();
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card top(int offset){
        offset++;
        return deck.get(deck.size()-offset);
    }

    public Card draw(int offset){
        offset++;
        Card tempCard = new Card(deck.get(deck.size()-offset).getId(),
                deck.get(deck.size()-offset).getColor(),
                deck.get(deck.size()-offset).getType());

        deck.remove(deck.size() - offset);
        return tempCard;
    }

    public void addCardToDeck(Card cardToBeAdded){
        deck.add(cardToBeAdded);
    }

}
