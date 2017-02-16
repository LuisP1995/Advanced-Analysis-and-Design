package com.example.rebecca.aadproject;

import android.content.Context;

/**
 * Created by Adam on 16/02/2017.
 */

public class ProfileSetup {

    final static int NUMBER_GAMES = 5;
    final static int DEFAULT_SCORE = 10;

    ProfileModel profileModel;

    ProfileSetup(Context appContext) {
        this.profileModel = new ProfileModel(appContext);
    }

    public void clearProfile() {
        profileModel.clear();
        profileModel.saveProfile(false);
    }

    public void setupBasicProfile() {
        profileModel.clear();

        profileModel.setUserName("Test");
        profileModel.setAvatar(1);

        for (int i = 0; i < NUMBER_GAMES; i++)
        {
            profileModel.updateImageScore(DEFAULT_SCORE);
            profileModel.updatePairsScore(DEFAULT_SCORE);
            profileModel.updateSequenceScore(DEFAULT_SCORE);
        }

        profileModel.saveProfile(false);
    }
}
