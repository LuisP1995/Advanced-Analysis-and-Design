package com.example.rebecca.aadproject;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.content.Context;
/**
 * A custom button class in order to flip cards and compare values on the cards
 *
 * http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin
 * Created by Luis Parcon on 03/02/2017.
 */

public class MemoryGameCard extends Button //creation of custom button object inheriter
{
    //information obtained from the pairsGame activity
    private int row;
    private int column;
    private int imageID;

    //some game logic
    protected boolean isFlipped;
    protected boolean isMatch;

    //the storage for our images
    protected Drawable frontSide;
    protected Drawable backSide;

    /*
    constructor for custom button class
    param:context - a context value which is the activity
    param: row - row in the activity
    param: column - column in the activity
    param: imageID - the resource value for the image
    return: none
     */
    public MemoryGameCard(Context context, int row, int column, int imageID)
    {
        super(context);
        this.row = row;
        this.column = column;
        this.imageID = imageID;

        frontSide = AppCompatDrawableManager.get().getDrawable(context,imageID); //ensures compatible android type is used depending on the android version
        backSide = AppCompatDrawableManager.get().getDrawable(context, R.drawable.unknown_back);
    }
    /*
    flips a card object to either its back or front on call
    param: none
    return: none
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void flipCard()
    {
        if (isFlipped) //case 1; the card has already been flipped
        {
            setBackground(backSide);
            isFlipped = false;
        }

        else //case 2: the card has not been flipped
        {

        }
    }

    public boolean isMatch() {
        return isMatch;
    }

    public void setMatch(boolean match) {
        isMatch = match;
    }

    public int getImageID() {
        return imageID;
    }
}
