package com.example.rebecca.aadproject;

/**
 * Created by Adam on 13/02/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ImageGameTests {

    @Rule
    public ActivityTestRule<ImageGameScreen> mActivityRule = new ActivityTestRule<>(ImageGameScreen.class);

    @Test
    public void loadsFiveRounds() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ImageGameModel imageModel = new ImageGameModel(appContext);

        assertEquals("Loads round 1", 5, imageModel.getRound(0).length);
        assertEquals("Loads round 2", 5, imageModel.getRound(1).length);
        assertEquals("Loads round 3", 5, imageModel.getRound(2).length);
        assertEquals("Loads round 4", 5, imageModel.getRound(3).length);
        assertEquals("Loads round 5", 5, imageModel.getRound(4).length);
    }

    @Test
    public void loadsNextRound() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, ImageGameScreen.class);
        ImageGameScreen imageScreen = mActivityRule.launchActivity(intent);
        String[] words = new String[4];
        String[] newWords = new String[4];

        final Button word1_button = (Button) imageScreen.findViewById(R.id.wordBtn1);
        final Button word2_button = (Button) imageScreen.findViewById(R.id.wordBtn2);
        final Button word3_button = (Button) imageScreen.findViewById(R.id.wordBtn3);
        final Button word4_button = (Button) imageScreen.findViewById(R.id.wordBtn4);

        words[0] = String.valueOf(word1_button.getText());
        words[1] = String.valueOf(word2_button.getText());
        words[2] = String.valueOf(word3_button.getText());
        words[3] = String.valueOf(word4_button.getText());

        imageScreen.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                word1_button.performClick();
                word2_button.performClick();
                word3_button.performClick();
            }
        });

        newWords[0] = String.valueOf(word1_button.getText());
        newWords[1] = String.valueOf(word2_button.getText());
        newWords[2] = String.valueOf(word3_button.getText());
        newWords[3] = String.valueOf(word4_button.getText());

        assertNotEquals("Make sure the words are updated when a new round is loaded.",words, newWords);
    }


}
