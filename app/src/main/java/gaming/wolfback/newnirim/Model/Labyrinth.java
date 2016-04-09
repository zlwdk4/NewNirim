package gaming.wolfback.newnirim.Model;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gaming.wolfback.newnirim.Utility.Card;

/**
 * Created by Jarren on 2/19/2016.
 */
public class Labyrinth {
    private static List<Card> labList;
    public Labyrinth()
    {
        labList = new ArrayList<Card>();

    }
    public void addCard(Card newCard)
    {
       labList.add(newCard);

    }

    public void removeCard(int index)
    {
        labList.remove(index);
    }

    public Card getCard (int index){
        if (index >= 0 && index < labList.size())
        return labList.get(index);
        else{
            Card nullCard = new Card(0, "null", "null");
            return nullCard;
        }

    }

    public int getSize(){
        return labList.size();
    }

    public void getLabString()
    {
        //int i = 0;
        String theLabString = " ";

        for (int i = 0; i < labList.size(); ++i)
        {
            theLabString += ("Card #" + i + " " + labList.get(i).getColor() +
                    " " + labList.get(i).getType() + "  -  ");
        }

        Log.d("TESTLOG", theLabString);
    }
}
