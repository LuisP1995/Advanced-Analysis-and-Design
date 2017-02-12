package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adam on 09/02/2017.
 * Controls what the completion screen looks like and handles button events.
 */

class ImageGameCompletionPresenter extends ScorePresenter {

    private String[][] _wrongAnswers;
    private int _wrongWordIndex = 0;

    public ImageGameCompletionPresenter(ImageGameCompletionScreen compScreen, float newScore,
                          String game, String[][] wrongAnswers) {
        super(compScreen, newScore, game);
        _wrongAnswers = wrongAnswers;
        _wrongWordIndex = 0;

        SetButtonListeners();
    }

    private void SetButtonListeners() {
        Button replay_button = (Button) _compScreen.findViewById(R.id.ImageComplitionPlayAgain);
        replay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_compScreen, ImageGameScreen.class);
                _compScreen.startActivity(newIntent);
            }

        });

        if (_wrongAnswers[_wrongWordIndex][0] != null) {
            Button wrongWords_button = (Button) _compScreen.findViewById(R.id.imageComplitionWrong);
            wrongWords_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _compScreen.setContentView(R.layout.wrong_words_screen); // should be activity

                    setWrongAnswersButtons();
                    updateNavButtons();
                    updateWrongWordDisplay();
                }

            });

            wrongWords_button.setVisibility(View.VISIBLE);
        }
    }

    private void  setWrongAnswersButtons()
    {
        Button done_button = (Button) _compScreen.findViewById(R.id.doneButton);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _compScreen.setContentView(R.layout.activity_image_game_complition_screen); //should close activity
                SetupCompletionScreen();
                UpdateScore();
                GeneralButtonListeners();
                SetButtonListeners();
            }
        });

        Button next_button = (Button) _compScreen.findViewById(R.id.imageNextBtn);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_wrongWordIndex != _wrongAnswers.length || _wrongAnswers[_wrongWordIndex+1][0] == null) {
                    _wrongWordIndex++;
                    updateNavButtons();
                    updateWrongWordDisplay();
                }
            }

        });

        Button previous_button = (Button) _compScreen.findViewById(R.id.imagePreviousBtn);
        previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_wrongWordIndex > 0) {
                    _wrongWordIndex--;
                    updateNavButtons();
                    updateWrongWordDisplay();
                }
            }

        });
    }

    private void updateNavButtons() {
        if(_wrongWordIndex == 0) {
            _compScreen.alterPreviousState(false);
        } else {
            _compScreen.alterPreviousState(true);
        }

        if (_wrongWordIndex == _wrongAnswers.length-1 || _wrongAnswers[_wrongWordIndex +1][0] == null) {
            _compScreen.alterNextState(false);
        } else {
            _compScreen.alterNextState(true);
        }
    }

    private void updateWrongWordDisplay() {
        ImageView wrongWordImg = (ImageView) _compScreen.findViewById(R.id.wrongWordImage);
        int id = _compScreen.getResources().getIdentifier(_wrongAnswers[_wrongWordIndex][0], "mipmap", _compScreen.getPackageName());
        wrongWordImg.setImageResource(id);
        TextView correctWord = (TextView) _compScreen.findViewById(R.id.wrongWord);
        correctWord.setText(_wrongAnswers[_wrongWordIndex][1]);
    }
}
