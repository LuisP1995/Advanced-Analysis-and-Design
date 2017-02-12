package com.example.rebecca.aadproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Rebecca on 09/02/2017.
 */

public class SequenceController {
    private ProfileModel _profModel;
    private int _round;
    private SequenceScreen _screen;
    private List<ImageButton> _buttonList;
    private List<Integer> _userInput;
    private Integer _score;
    private ArrayList<ImageButton> _unusedButton;

    public SequenceController(SequenceScreen sequenceScreen) {
        _score = 0;
        _screen = sequenceScreen;
        _userInput = new ArrayList<>();
        _round = 1;
        _profModel = new ProfileModel(_screen);
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

        Button but = (Button) _screen.findViewById(R.id.seqStartButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaySequence();
                view.setVisibility(View.INVISIBLE);
            }
        });

        for(final ImageButton button: _buttonList){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _userInput.add(view.getId());
                    CheckRoundEnd();
                }
            });
        }

        for(final ImageButton button: _unusedButton){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _userInput.add(view.getId());
                    CheckRoundEnd();
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
        for (ImageButton button: _buttonList) {
            Animation newAnimation = new AlphaAnimation(1,0);
            newAnimation.setDuration(1500);
            newAnimation.setStartOffset(1500 *count);
            button.startAnimation(newAnimation);
            count ++;
        }
    }

    public void CheckRoundEnd(){
        if (_userInput.size() == _buttonList.size()){
            for (int i =0;i<_buttonList.size();i++){
                ImageButton button = _buttonList.get(i);
                Integer inputId = _userInput.get(i);
                if (button.getId() == inputId){
                    _score++;
                }
            }
            Toast.makeText(_screen.getApplicationContext(), "Score: " + _score, Toast.LENGTH_SHORT).show(); // To be deleted
            //bonus if round 5 and score 24
            if (_round != 5) {
                _round++;
                nextRound();
            }
            else
            {
                Intent newIntent = new Intent(_screen, ImageGameCompletionScreen.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("newScore", _score);
                bundle.putString("game", "Sequence");
                newIntent.putExtras(bundle);
                _screen.startActivity(newIntent);
            }
        }
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
        Collections.shuffle(_buttonList);
        PlaySequence();
    }
}
