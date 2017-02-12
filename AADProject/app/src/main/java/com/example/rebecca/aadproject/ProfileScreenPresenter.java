package com.example.rebecca.aadproject;

/**
 * Created by Adam on 05/01/2017.
 */

class ProfileScreenPresenter {
    private ProfileModel _profileModel;

    ProfileScreenPresenter(ProfileScreen profileScreen){
        _profileModel = new ProfileModel(profileScreen.getApplicationContext());
        loadProfile();
    }

    float[] getPairsScoresData() {
        return _profileModel.getPairsScores();
    }
    int getPairsGamesPlayed() {return _profileModel.getPairsPlays();}
    int getPairsAverage() {
        return getAverageScore(_profileModel.getPairsScores());
    }


    float[] getSequenceScoresData() {
        return _profileModel.getSequenceScores();
    }
    int getSequenceGamesPlayed() {return _profileModel.getSequencePlays();}
    int getSequenceAverage() {
        return getAverageScore(_profileModel.getSequenceScores());
    }

    float[] getImageScoresData() {
        return _profileModel.getImageScores();
    }
    int getImageGamesPlayed() {return _profileModel.getImagePlays();}
    int getImageAverage() {
        return getAverageScore(_profileModel.getImageScores());
    }

    String getUserName(){
        return _profileModel.getUserName();
    }

    private boolean loadProfile() {
        _profileModel.loadProfile();
        return _profileModel.checkProfileExists();
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