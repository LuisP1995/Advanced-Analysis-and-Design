package com.example.rebecca.aadproject;

import android.animation.StateListAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rebecca on 09/02/2017.
 */

public class SequenceController {
    private SequenceScreen _screen;
    private List<ImageButton> _buttonList;
    private final Animation _animation;
    private final Animation _animationOrg;
    //private score

    public SequenceController(SequenceScreen sequenceScreen) {
        _screen = sequenceScreen;
        _animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        _animation.setDuration(1000); // duration - half a second
        _animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        _animationOrg = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        _animationOrg.setDuration(1000); // duration - half a second
        _animationOrg.setInterpolator(new LinearInterpolator()); // do not alter animation rate
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
                StartGame();
            }
        });
    }

    public void StartGame() {
        final ImageButton sb2 = (ImageButton) _screen.findViewById(R.id.seqImg2);

        Animation.AnimationListener anilistener = new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
                public void onAnimationEnd(Animation animation){
                    sb2.startAnimation(_animationOrg);
                }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        ImageButton sb1 = (ImageButton) _screen.findViewById(R.id.seqImg1);

        _animation.setAnimationListener(anilistener);

        sb1.startAnimation(_animation);
        //play sequence
        //store user sequence
        //score calculate
    }
}
