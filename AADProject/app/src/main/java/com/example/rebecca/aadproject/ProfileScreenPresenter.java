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

    public float[] getPairsData() {
        return pm.getPairsScores();
    }
    public int getPairsGamesPlayed() {return pm.getPairsPlays();}
    public float[] getSequenceData() {
        return pm.getSequenceScores();
    }
    public int getSequenceGamesPlayed() {return pm.getSequencePlays();}
    public float[] getImageData() {
        return pm.getImageScores();
    }
    public int getImageGamesPlayed() {return pm.getImagePlays();}

    public String getUserName(){
        return pm.getUserName();
    }
    private boolean loadProfile() {
        pm.loadProfile();
        return pm.checkProfileExists();
    }

}
