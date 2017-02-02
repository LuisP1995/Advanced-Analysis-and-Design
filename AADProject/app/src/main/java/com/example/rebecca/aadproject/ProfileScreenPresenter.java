package com.example.rebecca.aadproject;

import java.io.IOException;

/**
 * Created by Adam on 05/01/2017.
 */

public class ProfileScreenPresenter {
    private ProfileModel pm;
    private ProfileScreen ps;

    ProfileScreenPresenter(ProfileScreen ps){
        this.ps = ps;
        pm = new ProfileModel(this.ps);
        loadProfile();

    }

    public float[] getPairsScoresData() {
        return pm.getPairsScores();
    }
    public int getPairsGamesPlayed() {return pm.getPairsPlays();}
    public int getPairsAverage() {
        return getAverageScore(pm.getPairsScores());
    }


    public float[] getSequenceScoresData() {
        return pm.getSequenceScores();
    }
    public int getSequenceGamesPlayed() {return pm.getSequencePlays();}
    public int getSequenceAverage() {
        return getAverageScore(pm.getSequenceScores());
    }

    public float[] getImageScoresData() {
        return pm.getImageScores();
    }
    public int getImageGamesPlayed() {return pm.getImagePlays();}
    public int getImageAverage() {
        return getAverageScore(pm.getImageScores());
    }

    public String getUserName(){
        return pm.getUserName();
    }
    private boolean loadProfile() {
        try {
            pm.loadProfile();
            return pm.checkProfileExists();
        } catch (IOException e) {
            return false;
        }
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
