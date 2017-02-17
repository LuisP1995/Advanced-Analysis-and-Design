package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Adam on 12/02/2017.
 */

class MainController {
    private ProfileModel profileModel;
    private MainScreen _mainScreen;
    private static final int REQUIRED_PLAYS = 10;
    private static final int REQUIRED_SCORE = 50;

    MainController(MainScreen mainScreen) {
        _mainScreen = mainScreen;
        setButtonListeners();
        profileModel = new ProfileModel(mainScreen.getApplicationContext());
        lockGames();
    }

    private void lockGames() {
        _mainScreen.setSequenceGameState(validateGameUnlock(profileModel.getPairsScores(), profileModel.getPairsPlays()));
        _mainScreen.setImageGameState(validateGameUnlock(profileModel.getSequenceScores(), profileModel.getSequencePlays()));
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

        return plays >= REQUIRED_PLAYS || (scoreCount > 0 && totalScore/scoreCount >= REQUIRED_SCORE && plays > 1);
    }

    private void setButtonListeners() {
        Button settings_button = (Button) _mainScreen.findViewById(R.id.settings_button);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_mainScreen, SettingScreen.class);
                _mainScreen.startActivity(newIntent);
            }
        });

        Button profile_button = (Button) _mainScreen.findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_mainScreen, ProfileScreen.class);
                _mainScreen.startActivity(newIntent);
            }
        });

        Button info_button = (Button) _mainScreen.findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mainScreen.getApplicationContext(),
                        "This application has been developed with the intention to aid stroke survivors suffering with memory loss.",
                        Toast.LENGTH_LONG).show();
            }
        });

        Button pairs_button = (Button) _mainScreen.findViewById(R.id.pairs_button);
        pairs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Pairs");
                _mainScreen.startActivity(newIntent);
            }
        });

        Button seq_button = (Button) _mainScreen.findViewById(R.id.seq_button);
        seq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Sequence");
                _mainScreen.startActivity(newIntent);
            }
        });

        Button img_button = (Button) _mainScreen.findViewById(R.id.img_button);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_mainScreen, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Image");
                _mainScreen.startActivity(newIntent);
            }
        });
    }
}