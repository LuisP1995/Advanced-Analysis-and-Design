package com.example.rebecca.aadproject;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.Random;

/*
* Author: Luis Parcon - N0499977
* Pairs game activity, this activity will be the game screen used to play
* the pairs games, which will be a 4x4 grid
* http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin - for frontside
*b
*/


public class PairsGame extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_game);

        PairsController pairsController = new PairsController(this);
        pairsController.setupGame();

    }

}
