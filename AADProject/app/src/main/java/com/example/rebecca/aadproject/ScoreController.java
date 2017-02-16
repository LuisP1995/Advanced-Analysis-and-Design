package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rebecca on 12/02/2017.
 */

public class ScoreController {
    protected float _score;
    protected GameCompletionScreen _compScreen;
    protected ProfileModel _profModel;
    protected float[] _graphData;
    protected String _game;

    public ScoreController(GameCompletionScreen compScreen, float newScore, String game){
        _score = newScore;
        _compScreen = compScreen;
        _profModel = new ProfileModel(compScreen.getApplicationContext());
        _game = game;

        setupCompletionScreen();
        updateScore();
        generalButtonListeners();
    }

    protected void generalButtonListeners() {
        Button quit_button = (Button) _compScreen.findViewById(R.id.imageComplitionQuit);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_compScreen, MainScreen.class);
                _compScreen.startActivity(newIntent);
                _compScreen.finish();
            }

        });

        Button wrongWords_button = (Button) _compScreen.findViewById(R.id.imageComplitionWrong);
        wrongWords_button.setVisibility(View.INVISIBLE);

        if(_game.equals("Sequence")) {
            setReplayButton(SequenceScreen.class);
        }
        else if(_game.equals("Pairs"))
        {
            setReplayButton(PairsGame.class);
        }
        else if (_game.equals("Image"))
        {
            setReplayButton(ImageGameScreen.class);
        }
    }

    private void setReplayButton(final Class newScreen) {
        Button replay_button = (Button) _compScreen.findViewById(R.id.ImageComplitionPlayAgain);
        replay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(_compScreen,  newScreen);
                _compScreen.startActivity(newIntent);
                _compScreen.finish();
            }

        });
    }

    protected void setupCompletionScreen() {
        loadImageScores();
    }

    protected void loadImageScores() {
        _graphData = _profModel.getScores(_game);
    }

    protected void updateScore() {
        _profModel.updateScore(_game, (int)_score);
        _profModel.saveProfile(false);
        _compScreen.setScore(_score);
        _compScreen.setGraph(_graphData);
    }
}