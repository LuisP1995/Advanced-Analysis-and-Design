package com.example.rebecca.aadproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rebecca on 16/02/2017.
 */

@RunWith(AndroidJUnit4.class)
public class SequenceControllerTest {
    @Rule
    public ActivityTestRule<SequenceScreen> mActivityRule = new ActivityTestRule<>(SequenceScreen.class);

    SequenceScreen _sequenceScreen;

    @Before
    public void setup(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, SequenceScreen.class);
        _sequenceScreen = mActivityRule.launchActivity(intent);
    }

    @Test
    public void loadsRound()throws Exception{

        TextView text = (TextView) _sequenceScreen.findViewById(R.id.seqRound);
        String message = text.getText().toString();

        assertEquals("Round: 1", message);
    }

    @Test
    public void CheckOnClickBorder() throws Exception{

        _sequenceScreen.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageButton button = (ImageButton) _sequenceScreen.findViewById(R.id.seqImg1);
                button.performClick();
            }
        });

        ImageButton button = (ImageButton) _sequenceScreen.findViewById(R.id.seqImg1);
        ColorFilter filter = button.getBackground().getColorFilter();


    }
}
