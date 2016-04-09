package gaming.wolfback.newnirim;

import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//package gaming.wolfback.newnirim.View;

import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import gaming.wolfback.newnirim.Controller.Facade;
import gaming.wolfback.newnirim.Controller.Controller;
import gaming.wolfback.newnirim.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnDragListener {
    private Controller controller = new Controller();
    private ImageButton[] handButtons;
    private TextView doorRed;
    private TextView doorBlue;
    private TextView doorGreen;
    private TextView doorBrown;
    private TextView nightmareView;
    //currentIndexOfLabUI keeps track of where the next image to be placed in the lab should be
    private int currentIndexOfLabUI = 0;
    //currentIndexOfLabToBePulledFrom keeps track of which index in the lab we should be pulling from
    private int currentIndexOfLabToBePulledFrom;
    private ImageView discardPileView;
    private ImageView lastLab;
    private int cardNum;
    private float x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListenersForHand();


        setInitialCardsInHand();
        setHeightAndWidthOfHandButtons(90, 60);
        setHeightAndWidthOfLab(90, 60);




        View.OnDragListener dropListner = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                // Log.d("v id", String.valueOf(v.getId()));
                //Log.d("event id", String.valueOf(getLabResourceId(7)));
                int dragEvent = event.getAction();
                switch (dragEvent){


                    case DragEvent.ACTION_DROP:
                        View dragged = (View) event.getLocalState();

                        if (v.getId() == R.id.playCard){
                            ClipData theData = event.getClipData();
                            int theInd;
                            //CharSequence theChars = theData.getItemAt(0).toStrineg();
                            String theS = theData.getItemAt(0).coerceToText(getApplicationContext()).toString();
                            //theInd = Integer.parseInt(theData.getItemAt(0).toString());
                            Character theC = theS.charAt(theS.length() - 1);
                            theInd = Integer.parseInt(theC.toString());
                            //Log.d("cardNum = ", Integer.toString(theInd));

                            playCard(theInd);
                        }

                        else if (v.getId() == R.id.crystalBall){
                            String proph = "Prophecy failed :(";
                            proph = controller.getCardTypeFromHand(cardNum);
                            if(controller.getCardTypeFromHand(cardNum) == "key"){
                                proph = "Prophecy successful :)";
                                prophecize(cardNum);
                            }
                            Toast cbToast = Toast.makeText(getApplicationContext(), proph, Toast.LENGTH_LONG);
                            cbToast.show();
                        }

                        else if (v.getId() == R.id.dicardPile){
                            ClipData theData = event.getClipData();
                            int theInd;
                            String theS = theData.getItemAt(0).coerceToText(getApplicationContext()).toString();
                            Character theC = theS.charAt(theS.length() - 1);
                            theInd = Integer.parseInt(theC.toString());
                            discardCard(theInd);
                        }
                        Log.d("here it", "made it");

                        //
                        break;
                    case DragEvent.ACTION_DRAG_STARTED:
                        //
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //v
                        Log.d("IT HAS", "ENTERED!!!!");
                        //playCard(cardNum);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //
                        break;
                }
                return true;
            }
        };



        discardPileView = (ImageView) findViewById(R.id.discardPileId);
        doorRed = (TextView) findViewById(R.id.doorIdRed);
        doorBlue = (TextView) findViewById(R.id.doorIdBlue);
        doorGreen = (TextView) findViewById(R.id.doorIdGreen);
        doorBrown = (TextView) findViewById(R.id.doorIdBrown);
        nightmareView = (TextView) findViewById(R.id.nightmareId);

        lastLab = (ImageView) findViewById(R.id.LabId7);
        ImageView discPile = (ImageView) findViewById(R.id.dicardPile);
        ImageView crystalBall = (ImageView) findViewById(R.id.crystalBall);
        TextView playCard = (TextView) findViewById(R.id.playCard);

        //TextView theL = (TextView) findViewById(R.id.theListener);
        playCard.setOnDragListener(dropListner);
        discPile.setOnDragListener(dropListner);
        crystalBall.setOnDragListener(dropListner);
        //nightmareView.setOnDragListener(this);
    }
    private void setOnClickListenersForHand(){
        handButtons = new ImageButton[5];
        for (int i = 0; i < handButtons.length; ++i){
            String buttonID = "hand" + (i);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            handButtons[i] = ((ImageButton) findViewById(resID));
            handButtons[i].setOnClickListener(this);
            handButtons[i].setOnTouchListener(this);
        }
    }

    private void setHeightAndWidthOfHandButtons(int h, int w){

       /* for (int i = 0; i < handButtons.length; ++i){
            handButtons[i].getLayoutParams().width = w;
            handButtons[i].getLayoutParams().height = h;
        } */
    }

    private void setHeightAndWidthOfLab(int h, int w){
       /* labViews = new ImageView[8];
=======
        for (ImageView imageButton : handButtons){
            imageButton.getLayoutParams().width = w;
            imageButton.getLayoutParams().height = h;
        }
    }

    private void setHeightAndWidthOfLab(int h, int w){
        ImageView[] labViews = new ImageView[8];
>>>>>>> cef1f8aadf5010edb3399d2ad30b7241d4797d0d
        for (int i = 0; i < labViews.length; ++i){
            String labID = "LabId" + (i);
            int resID = getResources().getIdentifier(labID, "id", getPackageName());
            labViews[i] = ((ImageView) findViewById(resID));
            labViews[i].getLayoutParams().width = w;
            labViews[i].getLayoutParams().height = h;

        } */

    }

    @Override
    public boolean onTouch(View v, MotionEvent me){
        x = me.getX();
        y = me.getY();

        cardNum = R.id.hand0;

        for (cardNum = 0; cardNum < handButtons.length; cardNum++) {
            if (handButtons[cardNum].getId() == v.getId()) {
                if (controller.isValidPlay(cardNum)) {
                    Log.d("CardNum in OnTouch" , Integer.toString(cardNum));

                    DragShadow dragShadow = new DragShadow(v);

                    //used to pass the metadata through (card id being used)
                    ClipData data = ClipData.newPlainText("id", String.valueOf(cardNum));

                    v.startDrag(data, dragShadow, v, 0);
                }

            }

        }



        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        Log.i("here it", "ISSSSSSSSSSSS");
        int dragAction = event.getAction();
        switch (dragAction){

            case DragEvent.ACTION_DRAG_STARTED:
                //
                Log.i("Drag has", "STARTED");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i("here it", "asdfasdfasdf");
                //
                playCard(cardNum);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //
                break;
            case DragEvent.ACTION_DROP:
                View dragged = (View) event.getLocalState();
                View target = v;

                if (target.getId() == getLabResourceId(7)){

                    ClipData theData = event.getClipData();
                    cardNum = Integer.parseInt(theData.toString());
                    Log.d("cardNum = ", Integer.toString(cardNum));


                    playCard(cardNum);
                }
                Log.i("here it", "made it");

                //
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                //
                break;
        }
        return false;
    }


    private class DragShadow extends View.DragShadowBuilder{

        ColorDrawable greyBox;


        public DragShadow(View view) {
            super(view);
            greyBox = new ColorDrawable(Color.BLACK);

        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            greyBox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            View v = getView();

            int height = (int)v.getHeight()/2;
            int width = (int)v.getWidth()/2;

            greyBox.setBounds(0,0,width, height);


            shadowSize.set(width, height);

            shadowTouchPoint.set((int)width/2, (int)height/2);
        }
    }

    @Override
    public void onClick(View v) {




        for (cardNum = 0; cardNum < handButtons.length; cardNum++) {
            if (handButtons[cardNum].getId() == v.getId()) {
                if(controller.isValidPlay(cardNum)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Play or Discard");
                    builder.setMessage("Do you want to play or discard this card?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            playCard(cardNum);
                        }

                    });
                    builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            discardCard(cardNum);
                        }
                    });
                    builder.show();
                    break;
                }
                else{
                    discardCard(cardNum);
                    break;
                }
            }
        }
    }
    private void playCard(int cardNum){
        controller.playCard(cardNum);
        displayDoorCounts();
        displayNightmareCount();
        incrementIndexOfLabUI();
        shiftCardsInLab();
        displayCardsInLab();
        updateImageOfHand(cardNum);
    }

    private void discardCard(int cardNum){
        controller.discardCard(cardNum);
        updateImageOfDiscard();
        updateImageOfHand(cardNum);
    }

    private void prophecize(int cardNum){
        controller.prophecize(cardNum);
    }

    //*************************Discard UI Stuff*******************************************************************************//////
    private void updateImageOfDiscard(){
        String colorAndTypeOfCard = controller.getColorAndTypeOfTopDiscard();
        int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        Picasso.with(this).load(cardImageResourceId).fit().into(discardPileView);
        //discardPileView.setImageResource(cardImageResourceId);
    }
    //*****************************Hand UI stuff************************************************************************************////
    private void updateImageOfHand(int cardNum){
        String colorAndTypeOfCard = controller.getCardColorAndTypeFromHand(cardNum);
        int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        Picasso.with(this).load(cardImageResourceId).fit().into(handButtons[cardNum]);
        //handButtons[cardNum].setImageResource(cardImageResourceId);
    }

    private void setInitialCardsInHand() {
        for (int i = 0; i < handButtons.length; ++i) {
            String colorAndTypeOfCard = controller.getCardColorAndTypeFromHand(i);
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            Picasso.with(this).load(cardImageResourceId).fit().into(handButtons[cardNum]);
        }

    }
//************************************************************************************************************************************

    private void displayNightmareCount(){
        nightmareView.setText(Integer.toString(controller.getNightmareCount()));
    }

    private void displayDoorCounts(){
        doorRed.setText(Integer.toString(controller.getRedDoorCount()));
        doorBlue.setText(Integer.toString(controller.getBlueDoorCount()));
        doorGreen.setText(Integer.toString(controller.getGreenDoorCount()));
        doorBrown.setText(Integer.toString(controller.getBrownDoorCount()));
    }
    private int getCardImageResourceId (String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId (int indexOfLab){
        String labIdName = "LabId" + indexOfLab;
        return getResources().getIdentifier(labIdName, "id", getPackageName());
    }

    //*********************Lab UI stuff*********************************************************************************************************************///
    private void incrementIndexOfLabUI(){
        currentIndexOfLabUI++;
    }

    //The end result of calling this function is that all of the cards in the lab are shifted to the left if necessary
    //preconditions: the lab is full (currently that is when there are 8 cards in the lab)
    //post conditions: the cards in the lab will all be shifted left. The card that was in the 0th place is out of view. And the next place
    //that a card will be places is in the 8th spot (index 7)
    private void shiftCardsInLab(){
        if (currentIndexOfLabUI == 8) {
            currentIndexOfLabToBePulledFrom++;
            displayCardsInLab();
            currentIndexOfLabUI = 7;
        }
    }

    //preconditions: none
    //post conditions: up to seven cards will be displayed on the screen
    private void displayCardsInLab(){
        int i = currentIndexOfLabToBePulledFrom;
        for (currentIndexOfLabUI = 0; currentIndexOfLabUI < 7; ++currentIndexOfLabUI){
            String colorAndTypeOfCard = controller.getCardColorAndTypeFromLab(i);
            if (colorAndTypeOfCard.equals("null"))
                break;
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            updateLabImage(cardImageResourceId);
            i++;
        }
    }

    private void updateLabImage(int cardResId){
        ImageView theLab = (ImageView) findViewById(getLabResourceId(currentIndexOfLabUI));
        Picasso.with(this).load(cardResId).fit().into(theLab);
        //theLab.setImageResource(cardResId);
    }
//**************************************************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
