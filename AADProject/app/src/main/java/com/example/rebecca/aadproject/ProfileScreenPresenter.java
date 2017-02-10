package com.example.rebecca.aadproject;

import java.io.IOException;

/**
 * Created by Adam on 05/01/2017.
 */

class ProfileScreenPresenter {
    private ProfileModel profileModel;

    ProfileScreenPresenter(ProfileScreen profileScreen){
        profileModel = new ProfileModel(profileScreen.getApplicationContext());
        loadProfile();

    }

    float[] getPairsScoresData() {
        return profileModel.getPairsScores();
    }
    int getPairsGamesPlayed() {return profileModel.getPairsPlays();}
    int getPairsAverage() {
        return getAverageScore(profileModel.getPairsScores());
    }


    float[] getSequenceScoresData() {
        return profileModel.getSequenceScores();
    }
    int getSequenceGamesPlayed() {return profileModel.getSequencePlays();}
    int getSequenceAverage() {
        return getAverageScore(profileModel.getSequenceScores());
    }

    float[] getImageScoresData() {
        return profileModel.getImageScores();
    }
    int getImageGamesPlayed() {return profileModel.getImagePlays();}
    int getImageAverage() {
        return getAverageScore(profileModel.getImageScores());
    }

    String getUserName(){
        return profileModel.getUserName();
    }

    private boolean loadProfile() {
        profileModel.loadProfile();
        return profileModel.checkProfileExists();
    }

    private int getAverageScore(float [] scores) {
        for(int i = 0; i <scores.length; i++) {
            if(scores[i] == 0) {
                return i == 0 ? 0 : Math.round(scores[i-1]);
            }
        }
        return Math.round(scores[scores.length-1]);
    }



}
