package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Adam on 12/02/2017.
 */

class MainPresenter {
    private ProfileModel profileModel;
    private MainScreen mainScreen;
    private static final int REQUIRED_PLAYS = 10;
    private static final int REQUIRED_SCORE = 50;

    MainPresenter(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setButtonListeners();
        profileModel = new ProfileModel(mainScreen.getApplicationContext());
        lockGames();

    }

    private void lockGames() {
        mainScreen.setSequenceGameState(validateGameUnlock(profileModel.getPairsScores(), profileModel.getPairsPlays()));
        mainScreen.setImageGameState(validateGameUnlock(profileModel.getSequenceScores(), profileModel.getSequencePlays()));
    }

    private boolean validateGameUnlock(float[] scores, int plays) {
        int totalScore = 0;
        int scoreCount = 0;

        for (int i =0; i < scores.length; i++) {
            if (scores[i] > 0) {
                totalScore += (int) scores[i];
                scoreCount = i+1;
            }
        }

        return plays >= REQUIRED_PLAYS || totalScore/scoreCount >= REQUIRED_SCORE;
    }

    private void setButtonListeners() {
        Button settings_button = (Button) mainScreen.findViewById(R.id.settings_button);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mainScreen, SettingScreen.class);
                mainScreen.startActivity(newIntent);
            }
        });

        Button profile_button = (Button) mainScreen.findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mainScreen, ProfileScreen.class);
                mainScreen.startActivity(newIntent);
            }
        });

        Button info_button = (Button) mainScreen.findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainScreen.getApplicationContext(), "Information Here", Toast.LENGTH_LONG).show();
            }
        });

        Button pairs_button = (Button) mainScreen.findViewById(R.id.pairs_button);
        pairs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Pairs");
                mainScreen.startActivity(newIntent);
            }
        });

        Button seq_button = (Button) mainScreen.findViewById(R.id.seq_button);
        seq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Seq");
                mainScreen.startActivity(newIntent);
            }
        });

        Button img_button = (Button) mainScreen.findViewById(R.id.img_button);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Image");
                mainScreen.startActivity(newIntent);
            }
        });
    }
}