package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rebecca on 12/02/2017.
 */

public class ScorePresenter {
    protected float _score;
    protected ImageGameCompletionScreen _compScreen;
    protected ProfileModel _profModel;
    protected float[] _graphData;
    protected String _game;

    public ScorePresenter(){

    }

    public ScorePresenter(ImageGameCompletionScreen compScreen, float newScore, String game){
        _score = newScore;
        _compScreen = compScreen;
        _profModel = new ProfileModel(compScreen.getApplicationContext());
        _game = game;

        SetupCompletionScreen();
        UpdateScore();
        GeneralButtonListeners();
    }

    protected void GeneralButtonListeners() {
        Button quit_button = (Button) _compScreen.findViewById(R.id.imageComplitionQuit);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_compScreen, MainScreen.class);
                _compScreen.startActivity(newIntent);
            }

        });

//        Button replay_button = (Button) _compScreen.findViewById(R.id.ImageComplitionPlayAgain);
//        replay_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent newIntent = new Intent(_compScreen, SequenceScreen.class);
//                _compScreen.startActivity(newIntent);
//            }
//
//        });

        Button wrongWords_button = (Button) _compScreen.findViewById(R.id.imageComplitionWrong);
        wrongWords_button.setVisibility(View.INVISIBLE);
    }

    protected void SetupCompletionScreen() {
        LoadImageScores();
    }

    protected void LoadImageScores() {
        _graphData = _profModel.GetScores(_game);
    }

    protected void UpdateScore() {
        _profModel.UpdateScore(_game, (int)_score);
        _profModel.saveProfile(false);
        _compScreen.setScore(_score);
        _compScreen.setGraph(_graphData);
    }
}
