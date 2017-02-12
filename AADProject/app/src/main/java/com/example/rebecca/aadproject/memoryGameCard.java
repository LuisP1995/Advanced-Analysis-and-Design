package com.example.rebecca.aadproject;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.content.Context;
import android.widget.GridLayout;

/**
 * A custom button class in order to flip cards and compare values on the cards
 *
 * http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin
 * Created by Luis Parcon on 03/02/2017.
 */

public class MemoryGameCard extends Button //creation of custom button object inheriter
{
    private static final int gamePixelDensity = 175;
    //information obtained from the pairsGame activity
    private int _row;
    private int _column;
    private int _imageID;

    //some game logic
    protected boolean isFlipped = false;
    protected boolean isMatch = false;

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
    public MemoryGameCard(Context gameScreen, int row, int column, int imageID)
    {
        super(gameScreen);
        _row = row;
        _column = column;
        _imageID = imageID;

        //AppCompatDrawableManager ensures compatible android type is used
        frontSide = AppCompatDrawableManager.get().getDrawable(gameScreen,imageID); //image is generated and assigned in the game activity
        backSide = AppCompatDrawableManager.get().getDrawable(gameScreen, R.drawable.unknown_back);
        setBackground(backSide);

        //generate the games layout in the game activity
        GridLayout.LayoutParams gameLayout = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(column));
        gameLayout.width = (int) getResources().getDisplayMetrics().density * gamePixelDensity;
        gameLayout.height = (int) getResources().getDisplayMetrics().density * gamePixelDensity;

        setLayoutParams(gameLayout);
    }
    /*
    flips a card object to either its back or front on call
    param: none
    return: none
     */
    public void flipCard()
    {
        if (isMatch) //case 1; the card has already been flipped
        {
            setBackground(backSide);
            isFlipped = false;
        }

        if(isFlipped) //case 2: the front image is showing
        {
            setBackground(backSide);
            isFlipped = false;
        }
        else //case 3: the card has not been flipped
        {
            setBackground(frontSide);
            isFlipped = true;
        }
    }

    //Generated setters and getters
    public boolean isMatch()
    {
        return isMatch;
    }

    public void setMatch(boolean match)
    {
        isMatch = match;
    }

    public int getImageID()
    {
        return _imageID;
    }

    public Drawable getFrontSide() {
        return frontSide;
    }
}
