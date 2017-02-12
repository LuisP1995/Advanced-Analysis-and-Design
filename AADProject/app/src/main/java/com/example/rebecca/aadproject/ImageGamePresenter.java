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

    private String _correctAnswer = "";
    private String [][] wrongAnswers = new String[MAX_ROUNDS][2];
    private int _tryCount = 0;
    private int round = 0;
    private float score = 0;
    private String[] _roundData;
    private int _wrongIndex = 0;
    private ImageGameModel _gameModel;
    private ImageGameScreen _imageGameScreen;


    ImageGamePresenter(ImageGameScreen imageGameScreen) {
        _imageGameScreen = imageGameScreen;
        round = 0;
        score = 0;
        _wrongIndex = 0;
        _gameModel = new ImageGameModel(_imageGameScreen.getApplicationContext());
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
            _tryCount++;
            if (_tryCount == MAX_TRIES) {
                // max tries exceeded save wrong word and load next round
                wrongAnswers[_wrongIndex][0] = _roundData[0];
                wrongAnswers[_wrongIndex][1] = _roundData[1];
                _wrongIndex++;
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

        Intent newIntent = new Intent(_imageGameScreen, ImageGameCompletionScreen.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("newScore", score);
        bundle.putSerializable("wrongAnswers", wrongAnswers);
        bundle.putString("game", "Image");
        newIntent.putExtras(bundle);
        _imageGameScreen.startActivity(newIntent);
        _imageGameScreen.finish();
    }

    private void loadNextRound() {
        _tryCount = 0;

        // update game screen text
        _imageGameScreen.setRound(round);
        _imageGameScreen.setScore(score);

        //load in and set the new data
        _roundData = _gameModel.getRound(round);
        _correctAnswer = _roundData[1];
        _imageGameScreen.setImage(_roundData[0]);

        //randomise the word positions
        String [] words = {_roundData[1], _roundData[2], _roundData[3], _roundData[4]};
        Collections.shuffle(Arrays.asList(words));

        // setup all the buttons
        _imageGameScreen.setButton1Enabled(true);
        _imageGameScreen.setButton2Enabled(true);
        _imageGameScreen.setButton3Enabled(true);
        _imageGameScreen.setButton4Enabled(true);

        _imageGameScreen.setButton1Text(words[0]);
        _imageGameScreen.setButton2Text(words[1]);
        _imageGameScreen.setButton3Text(words[2]);
        _imageGameScreen.setButton4Text(words[3]);
    }

    private void setButtonListeners() {
        final Button word1_button = (Button) _imageGameScreen.findViewById(R.id.wordBtn1);
        word1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageGameScreen.setButton1Enabled(checkCorrect(word1_button.getText().toString()));
                playedRound(word1_button.getText().toString());
            }

        });

        final Button word2_button = (Button) _imageGameScreen.findViewById(R.id.wordBtn2);
        word2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageGameScreen.setButton2Enabled(checkCorrect(word2_button.getText().toString()));
                playedRound(word2_button.getText().toString());
            }

        });

        final Button word3_button = (Button) _imageGameScreen.findViewById(R.id.wordBtn3);
        word3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageGameScreen.setButton3Enabled(checkCorrect(word3_button.getText().toString()));
                playedRound(word3_button.getText().toString());
            }

        });

        final Button word4_button = (Button) _imageGameScreen.findViewById(R.id.wordBtn4);
        word4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageGameScreen.setButton4Enabled(checkCorrect(word4_button.getText().toString()));
                playedRound(word4_button.getText().toString());
            }

        });
    }

    private boolean checkCorrect(String selectedW) {
        return _correctAnswer == selectedW;
    }

    private void updateScore() {
        score += ROUND_SCORE/(_tryCount +1);
    }
}
