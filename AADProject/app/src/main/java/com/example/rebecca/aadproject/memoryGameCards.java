package com.example.rebecca.aadproject;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.content.Context;
/**
 * A custom button class in order to flip cards and compare values on the cards
 *
 * http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin
 * Created by Luis Parcon on 03/02/2017.
 */

public class memoryGameCards extends Button
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
    public memoryGameCards(Context context, int row, int column, int imageID)
    {
        super(context);
        this.row = row;
        this.column = column;
        this.imageID = imageID;
    }
}
