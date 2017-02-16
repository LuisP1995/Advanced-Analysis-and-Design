package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rebecca on 14/02/2017.
 */

public class TutorialController {
    private TutorialScreen _screen;

    public TutorialController(TutorialScreen tutorialScreen) {
        _screen = tutorialScreen;
    }

    public void showTutorial(String message) {
        if (message.equals("Pairs")){
            StartPairs();
        }
        else if (message.equals("Sequence")){
            StartSeq();
        }
        else if (message.equals("Image")){
            StartImg();
        }
    }

    private void StartImg() {
        instructFirst("Select the word that most accurately matches the picture.");
        instructSecond("If you get the wrong word, then that word will be disabled. Select another.");
        instructThird("You will have a total of 3 tries per round.");
        instructFourth("Hit \"Proceed\" to play!");

        SetImages();
        SetProceedButton(ImageGameScreen.class);
    }

    private void StartSeq() {
        instructFirst("Watch the sequence play");
        instructSecond("Wait for the sequence to finish");
        instructThird("Press the buttons in the same order of the sequence shown");
        instructFourth("Hit \"Proceed\" to play!");

        SetImages();
        SetProceedButton(SequenceScreen.class);
    }

    private void StartPairs() {
        instructFirst("Click on a tile");
        instructSecond("Match the tiles with the same image");
        instructThird("Take as few turns as possible");
        instructFourth("Hit \"Proceed\" to play!");

        SetImages();
        instructFirst("Pairs Instructions");
        SetProceedButton(PairsGame.class);
    }

    private void instructFourth(String instruction) {
        TextView textview = (TextView)_screen.findViewById(R.id.step4);
        textview.setText("4." + instruction);
    }

    private void instructThird(String instruction) {
        TextView textview = (TextView)_screen.findViewById(R.id.step3);
        textview.setText("3. " + instruction);
    }

    private void instructSecond(String instruction) {
        TextView textview = (TextView)_screen.findViewById(R.id.step2);
        textview.setText("2." + instruction);
    }

    private void instructFirst(String instruction) {
        TextView textview = (TextView)_screen.findViewById(R.id.step1);
        textview.setText("1. " +instruction);
    }

    private void SetImages() {
        ImageView tutImage = (ImageView) _screen.findViewById(R.id.TutImg1);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) _screen.findViewById(R.id.TutImg2);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) _screen.findViewById(R.id.TutImg3);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) _screen.findViewById(R.id.TutImg4);
        tutImage.setVisibility(View.INVISIBLE);
    }

    private void SetProceedButton(final Class Screen) {
        Button button = (Button)_screen.findViewById(R.id.proceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_screen, Screen);
                _screen.startActivity(newIntent);
                _screen.finish();
            }
        });
    }
}