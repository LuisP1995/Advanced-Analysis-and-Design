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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rebecca on 09/02/2017.
 */

public class SequenceController {
    private int _round;
    private SequenceScreen _screen;
    private List<ImageButton> _buttonList;
    private List<Integer> _userInput;
    private Integer _score;
    private ArrayList<ImageButton> _unusedButton;
    private Animation _animation;

    public SequenceController(SequenceScreen sequenceScreen) {
        _score = 0;
        _screen = sequenceScreen;
        _userInput = new ArrayList<>();
        _round = 1;
        ProfileModel profileModel= new ProfileModel(_screen);
    }

    public void SetupGame() {

        ImageButton sb1 = (ImageButton) _screen.findViewById(R.id.seqImg1);
        sb1.setImageResource(R.mipmap.blackstar);

        ImageButton sb2 = (ImageButton) _screen.findViewById(R.id.seqImg2);
        sb2.setImageResource(R.mipmap.purplediamond);

        ImageButton sb3 = (ImageButton) _screen.findViewById(R.id.seqImg3);
        sb3.setImageResource(R.mipmap.orangecircle);

        ImageButton sb4 = (ImageButton) _screen.findViewById(R.id.seqImg4);
        sb4.setImageResource(R.mipmap.greensqr);
        sb4.setVisibility(View.INVISIBLE);

        ImageButton sb5 = (ImageButton) _screen.findViewById(R.id.seqImg5);
        sb5.setImageResource(R.mipmap.bluetriangle);
        sb5.setVisibility(View.INVISIBLE);

        ImageButton sb6 = (ImageButton) _screen.findViewById(R.id.seqImg6);
        sb6.setImageResource(R.mipmap.yellowhexagon);
        sb6.setVisibility(View.INVISIBLE);

        _unusedButton = new ArrayList<>(Arrays.asList(sb4,sb5,sb6));

        List<ImageButton> buttonList = new ArrayList<>(Arrays.asList(sb1,sb2,sb3));

        Collections.shuffle(buttonList);

        _buttonList = buttonList;
        SetRound();


        for(final ImageButton button: _buttonList){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(_animation.hasEnded()) {
                        _userInput.add(view.getId());
                        view.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.ADD);
                        view.setEnabled(false);
                        CheckRoundEnd();
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
                        CheckRoundEnd();
                    }
                }
            });
        }
    }

    private void SetRound() {
        TextView text = (TextView) _screen.findViewById(R.id.seqRound);
        text.setText("Round: " + Integer.toString(_round));
    }

    public void PlaySequence() {
        int count =1;

        Animation newAnimation = null;
        for (ImageButton button: _buttonList) {
            newAnimation = new AlphaAnimation(1,0);
            newAnimation.setDuration(1500);
            newAnimation.setStartOffset(1500 *count);
            button.startAnimation(newAnimation);
            count ++;
        }
        _animation = newAnimation;

    }

    public void CheckRoundEnd(){
        if (_userInput.size() == _buttonList.size()){
            for (int i =0;i<_buttonList.size();i++){
                ImageButton button = _buttonList.get(i);
                Integer inputId = _userInput.get(i);
                if (button.getId() == inputId){
                    _score+= 5;
                }
            }
            SetScoreOnScreen();
            if (_round != 5) {
                _round++;
                nextRound();
            }
            else
            {
                if(_score == 120){
                    Toast.makeText(_screen.getApplicationContext(), "Bonus Points Rewarded", Toast.LENGTH_SHORT).show();
                    _score += 20;
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

    protected void SetScoreOnScreen() {
        TextView scoreButton = (TextView) _screen.findViewById(R.id.seqScore);
        scoreButton.setText("Score: " + _score);
    }

    private void nextRound() {
        _userInput = new ArrayList<>();
        SetRound();
        if (_unusedButton.size() != 0) {
            ImageButton button = _unusedButton.get(0);
            button.setVisibility(View.VISIBLE);
            _buttonList.add(button);
            _unusedButton.remove(button);
        }
        ResetButtons();
        Collections.shuffle(_buttonList);
        PlaySequence();
    }

    private void ResetButtons() {
        for(ImageButton button: _buttonList){
            button.getBackground().clearColorFilter();
            button.setEnabled(true);
        }
    }

    protected List<ImageButton> getButtonList(){return _buttonList;}
}
