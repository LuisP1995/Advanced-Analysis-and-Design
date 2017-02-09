package com.example.rebecca.aadproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rebecca on 09/02/2017.
 */

public class SequenceController {
    private SequenceScreen _screen;
    private List<ImageButton> _buttonList;
    //private score

    public SequenceController(SequenceScreen sequenceScreen) {
        _screen = sequenceScreen;

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

        List<ImageButton> buttonList = new ArrayList<>(Arrays.asList(sb1,sb2,sb3,sb4));

        Collections.shuffle(buttonList);

        _buttonList = buttonList;

        Button but = (Button) _screen.findViewById(R.id.seqStartButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaySequence();
            }
        });
    }

    public void PlaySequence() {
        int count =1;

        for (ImageButton button: _buttonList) {

            Animation newAnimation = new AlphaAnimation(1,0);
            newAnimation.setDuration(1000);
            newAnimation.setStartOffset(2000 *count);
            button.startAnimation(newAnimation);
            count ++;
        }

        //store user sequence
        //score calculate
    }
}
