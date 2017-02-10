package com.example.rebecca.aadproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Adam on 05/02/2017.
 * Presenter controls the image game.
 */

class ImageGamePresenter {

    private static final int MAX_ROUNDS = 3;
    private static final int MAX_TRIES = 3;
    private static final int ROUND_SCORE = 30;

    private String correctAnswer = "";
    private String [][] wrongAnswers = new String[MAX_ROUNDS][2];
    private int tryCount = 0;
    private int round = 0;
    private float score = 0;
    private String[] roundData;
    private int wrongIndex = 0;
    private ImageGameModel gameModel;
    private ImageGameScreen imageGameScreen;


    ImageGamePresenter(ImageGameScreen imageGameScreen) {
        this.imageGameScreen = imageGameScreen;
        round = 0;
        score = 0;
        wrongIndex = 0;
        gameModel = new ImageGameModel(this.imageGameScreen.getApplicationContext());
        setButtonListeners();
        loadNextRound();
    }

    private void playedRound(String selectedW) {
        if(checkCorrect(selectedW)) {
            // valid choice update score then load next round
            round++;
            updateScore();
            if(round >= MAX_ROUNDS) {
                displayCompletionScreen();
            } else {
                loadNextRound();
            }
        } else {
            tryCount++;
            if (tryCount == MAX_TRIES) {
                // max tries exceeded save wrong word and load next round
                wrongAnswers[wrongIndex][0] = roundData[0];
                wrongAnswers[wrongIndex][1] = roundData[1];
                wrongIndex++;
                round++;
                if(round >= MAX_ROUNDS) {
                    displayCompletionScreen();
                } else {
                    loadNextRound();
                }
            }
        }
    }

    private void displayCompletionScreen(){

        Intent newIntent = new Intent(imageGameScreen, ImageGameCompletionScreen.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("newScore", score);
        bundle.putSerializable("wrongAnswers", wrongAnswers);
        newIntent.putExtras(bundle);
        imageGameScreen.startActivity(newIntent);

    }

    private void loadNextRound() {
        tryCount = 0;

        // update game screen text
        imageGameScreen.setRound(round);
        imageGameScreen.setScore(score);

        //load in and set the new data
        roundData = gameModel.getRound(round);
        correctAnswer = roundData[1];
        imageGameScreen.setImage(roundData[0]);

        //randomise the word positions
        String [] words = {roundData[1], roundData[2], roundData[3], roundData[4]};
        Collections.shuffle(Arrays.asList(words));

        // setup all the buttons
        imageGameScreen.setButton1Enabled(true);
        imageGameScreen.setButton2Enabled(true);
        imageGameScreen.setButton3Enabled(true);
        imageGameScreen.setButton4Enabled(true);

        imageGameScreen.setButton1Text(words[0]);
        imageGameScreen.setButton2Text(words[1]);
        imageGameScreen.setButton3Text(words[2]);
        imageGameScreen.setButton4Text(words[3]);
    }

    private void setButtonListeners() {
        final Button word1_button = (Button) imageGameScreen.findViewById(R.id.wordBtn1);
        word1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageGameScreen.setButton1Enabled(checkCorrect(word1_button.getText().toString()));
                playedRound(word1_button.getText().toString());
            }

        });

        final Button word2_button = (Button) imageGameScreen.findViewById(R.id.wordBtn2);
        word2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageGameScreen.setButton2Enabled(checkCorrect(word2_button.getText().toString()));
                playedRound(word2_button.getText().toString());
            }

        });

        final Button word3_button = (Button) imageGameScreen.findViewById(R.id.wordBtn3);
        word3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageGameScreen.setButton3Enabled(checkCorrect(word3_button.getText().toString()));
                playedRound(word3_button.getText().toString());
            }

        });

        final Button word4_button = (Button) imageGameScreen.findViewById(R.id.wordBtn4);
        word4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageGameScreen.setButton4Enabled(checkCorrect(word4_button.getText().toString()));
                playedRound(word4_button.getText().toString());
            }

        });
    }

    private boolean checkCorrect(String selectedW) {
        return correctAnswer == selectedW;
    }

    private void updateScore() {
        score += ROUND_SCORE/(tryCount+1);
    }
}
