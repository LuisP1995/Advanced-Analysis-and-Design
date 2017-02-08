package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

/*
* Author: Luis Parcon - N0499977
* Pairs game activity, this activity will be the game screen used to play
* the pairs games, which will be a 4x4 grid
* http://opengameart.org/content/playing-cards-vector-png - deck of cards download link origin
*
*/


public class PairsGame extends AppCompatActivity
{
    static final int correct =  5;
    //Stores the selections made by the user to compare
    private MemoryGameCard firstSelection;
    private MemoryGameCard secondSelection;

    private int cardsInGrid; //the number of cards in the 4x4 game space
    private int[] cards;

    protected int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_game);

        Button exitBtn = (Button)findViewById(R.id.pairsExit);

        exitBtn.setOnClickListener(new View.OnClickListener() //navigate back to the main screen
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(PairsGame.this, GenScoreScreen.class);
                startActivity(newIntent);
            }
        });
    }

    public void updateScore(int correct)
    {
        score += correct;
    }
}
