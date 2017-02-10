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

class ImageGameCompletionPresenter {

    private ImageGameCompletionScreen imageScreen;
    private float newScore;
    private String[][] wrongAnswers;
    private float[] imageData;
    private ProfileModel profModel;
    private int wrongWordIndex = 0;

    ImageGameCompletionPresenter(ImageGameCompletionScreen imageCompScreen, float newScore, String[][] wrongAnswers) {
        this.imageScreen = imageCompScreen;
        this.newScore = newScore;
        this.wrongAnswers = wrongAnswers;
        wrongWordIndex = 0;
        profModel = new ProfileModel(imageScreen.getApplicationContext());
        if (wrongAnswers[wrongWordIndex][0] == null) {
            imageScreen.disableWrongWords();
        }
        updateImageScores();
        setUpCompletionScreen();
        setButtonListeners();
    }

    private void setButtonListeners() {
        final Button quit_button = (Button) imageScreen.findViewById(R.id.imageComplitionQuit);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(imageScreen, MainScreen.class);
                imageScreen.startActivity(newIntent);
            }

        });

        final Button replay_button = (Button) imageScreen.findViewById(R.id.ImageComplitionPlayAgain);
        replay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(imageScreen, ImageGameScreen.class);
                imageScreen.startActivity(newIntent);
            }

        });

        final Button wrongWords_button = (Button) imageScreen.findViewById(R.id.imageComplitionWrong);
        wrongWords_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageScreen.setContentView(R.layout.wrong_words_screen);
                setWrongAnswersButtons();
                updateNavButtons();
                updateWrongWordDisplay();
            }

        });
    }

    private void  setWrongAnswersButtons()
    {
        final Button done_button = (Button) imageScreen.findViewById(R.id.doneButton);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageScreen.setContentView(R.layout.activity_image_game_complition_screen);
                setUpCompletionScreen();
                setButtonListeners();
            }

        });

        final Button next_button = (Button) imageScreen.findViewById(R.id.imageNextBtn);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wrongWordIndex != wrongAnswers.length || wrongAnswers[wrongWordIndex+1][0] == null) {
                    wrongWordIndex++;
                    updateNavButtons();
                    updateWrongWordDisplay();
                }
            }

        });

        final Button previous_button = (Button) imageScreen.findViewById(R.id.imagePreviousBtn);
        previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wrongWordIndex > 0) {
                    wrongWordIndex--;
                    updateNavButtons();
                    updateWrongWordDisplay();
                }
            }

        });
    }

    private void updateNavButtons() {
        if(wrongWordIndex == 0) {
            imageScreen.alterPreviousState(false);
        } else {
            imageScreen.alterPreviousState(true);
        }

        if (wrongWordIndex == wrongAnswers.length-1 || wrongAnswers[wrongWordIndex+1][0] == null) {
            imageScreen.alterNextState(false);
        } else {
            imageScreen.alterNextState(true);
        }
    }

    private void updateWrongWordDisplay() {
        ImageView wrongWordImg = (ImageView) imageScreen.findViewById(R.id.wrongWordImage);
        int id = imageScreen.getResources().getIdentifier(wrongAnswers[wrongWordIndex][0], "mipmap", imageScreen.getPackageName());
        wrongWordImg.setImageResource(id);
        TextView correctWord = (TextView) imageScreen.findViewById(R.id.wrongWord);
        correctWord.setText(wrongAnswers[wrongWordIndex][1]);
    }

    private void updateImageScores() {
        profModel.updateImageScore((int)newScore);
        profModel.saveProfile(false);
    }

    private void loadImageScores() {
        imageData = profModel.getImageScores();
    }

    private void setUpCompletionScreen() {
        loadImageScores();
        imageScreen.setScore(newScore);
        imageScreen.setGraph(imageData);
    }
}
