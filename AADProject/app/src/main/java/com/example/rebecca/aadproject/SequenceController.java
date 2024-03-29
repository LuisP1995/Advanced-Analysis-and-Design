package com.example.rebecca.aadproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rebecca on 09/02/2017.
 */

public class SequenceController {
    private SequenceModel _sequenceModel;
    private int _round;
    private SequenceScreen _screen;
    private List<ImageButton> _buttonList;
    private List<Integer> _userInput;
    private Integer _score;
    private ArrayList<ImageButton> _unusedButton;
    private Animation _animation;

    private static int HIGH_SCORE = 120;
    private static int POINT = 5;
    private static int DURATION = 1500;
    private static int MAX_ROUNDS = 5;
    private static int BONUS_POINTS = 20;

    public SequenceController(SequenceScreen sequenceScreen) {
        _score = 0;
        _screen = sequenceScreen;
        _userInput = new ArrayList<>();
        _round = 1;
        ProfileModel profileModel= new ProfileModel(_screen);
        _sequenceModel = new SequenceModel(_screen);
    }

    public void setupGame() {

        _sequenceModel.setButtons();

        _unusedButton = _sequenceModel.getUnusedButtons();
        List<ImageButton> buttonList = _sequenceModel.getActiveButtons();
        Collections.shuffle(buttonList);

        _buttonList = buttonList;
        setRound();

        setButtonListeners();
    }

    private void setButtonListeners() {
        for(final ImageButton button: _buttonList){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(_animation.hasEnded()) {
                        _userInput.add(view.getId());
                        view.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.ADD);
                        view.setEnabled(false);
                        checkRoundEnd();
                    }
                }
            });
        }

        for(final ImageButton button: _unusedButton){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(_animation.hasEnded()) {
                        _userInput.add(view.getId());
                        view.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.ADD);
                        view.setEnabled(false);
                        checkRoundEnd();
                    }
                }
            });
        }
    }

    private void setRound() {
        TextView text = (TextView) _screen.findViewById(R.id.seqRound);
        text.setText("Round: " + Integer.toString(_round));
    }

    public void playSequence() {
        int count =1;

        Animation newAnimation = null;
        for (ImageButton button: _buttonList) {
            newAnimation = new AlphaAnimation(1,0);
            newAnimation.setDuration(DURATION);
            newAnimation.setStartOffset(DURATION *count);
            button.startAnimation(newAnimation);
            count ++;
        }
        _animation = newAnimation;

    }

    public void checkRoundEnd(){
        if (_userInput.size() == _buttonList.size()){
            for (int i =0;i<_buttonList.size();i++){
                ImageButton button = _buttonList.get(i);
                Integer inputId = _userInput.get(i);
                if (button.getId() == inputId){
                    _score+= POINT;
                }
            }
            setScoreOnScreen();
            if (_round != MAX_ROUNDS) {
                _round++;
                nextRound();
            }
            else
            {
                if(_score == HIGH_SCORE){
                    Toast.makeText(_screen.getApplicationContext(), "Bonus Points Rewarded", Toast.LENGTH_SHORT).show();
                    _score += BONUS_POINTS;
                }

                Intent newIntent = new Intent(_screen, GameCompletionScreen.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("newScore", _score);
                bundle.putString("game", "Sequence");
                newIntent.putExtras(bundle);
                _screen.startActivity(newIntent);
                _screen.finish();
            }
        }
    }

    protected void setScoreOnScreen() {
        TextView scoreButton = (TextView) _screen.findViewById(R.id.seqScore);
        scoreButton.setText("Score: " + _score);
    }

    private void nextRound() {
        _userInput = new ArrayList<>();
        setRound();
        if (_unusedButton.size() != 0) {
            ImageButton button = _unusedButton.get(0);
            button.setVisibility(View.VISIBLE);
            _buttonList.add(button);
            _unusedButton.remove(button);
        }
        resetButtons();
        Collections.shuffle(_buttonList);
        playSequence();
    }

    private void resetButtons() {
        for(ImageButton button: _buttonList){
            button.getBackground().clearColorFilter();
            button.setEnabled(true);
        }
    }
}