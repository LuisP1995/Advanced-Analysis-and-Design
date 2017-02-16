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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CreateProfileScreenTests {

    @Rule
    public ActivityTestRule<ProfileCreateScreen> mActivityRule = new ActivityTestRule<>(ProfileCreateScreen.class);

    @Test
    public void toastNoUsernameAvatar() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, ProfileCreateScreen.class);

        ProfileSetup profileSetup = new ProfileSetup(appContext);
        profileSetup.clearProfile();
        ProfileCreateScreen profileCreateScreen = mActivityRule.launchActivity(intent);

        final Button submit_button = (Button) profileCreateScreen.findViewById(R.id.submit);

        profileCreateScreen.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                submit_button.performClick();
            }
        });

        assertTrue("Submit doesnt cause profile create to loose focus if avatar and username have not changed.", profileCreateScreen.hasWindowFocus());

        final EditText userName = (EditText) profileCreateScreen.findViewById(R.id.username);
        final ImageButton avatar_button = (ImageButton) profileCreateScreen.findViewById(R.id.avatar1);

        profileCreateScreen.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userName.setText("name");
                avatar_button.performClick();
                submit_button.performClick();
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue("Submit closes profile create screen.", profileCreateScreen.isDestroyed());
    }

    @Test
    public void profileExistLoadsMainScreenInstead() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, ProfileCreateScreen.class);

        ProfileSetup profileSetup = new ProfileSetup(appContext);
        profileSetup.setupBasicProfile();

        ProfileCreateScreen profileCreateScreen = mActivityRule.launchActivity(intent);

        assertFalse("Profile screen doesn't show.", profileCreateScreen.hasWindowFocus());
    }

    @Test
    public void showProfileCreateScreenNoProfile() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, ProfileCreateScreen.class);

        ProfileSetup profileSetup = new ProfileSetup(appContext);
        profileSetup.clearProfile();

        ProfileCreateScreen profileCreateScreen = mActivityRule.launchActivity(intent);

        assertTrue("Profile screen shows.", profileCreateScreen.hasWindowFocus());
    }
}
