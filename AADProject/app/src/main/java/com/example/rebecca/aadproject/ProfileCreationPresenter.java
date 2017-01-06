package com.example.rebecca.aadproject;
import android.content.Intent;

import java.io.IOException;

/**
 * Created by Adam on 29/12/2016.
 */

public class ProfileCreationPresenter {
    private ProfileModel pm;
    private ProfileCreationScreen ps;

    ProfileCreationPresenter(ProfileCreationScreen ps){
        this.ps = ps;
        pm = new ProfileModel(this.ps);
        profileLoader();
    }

    void profileLoader() {
        if(!loadProfile()) {
            ps.showForm();
        } else {
            loadMainScreen();
        }
    }

    private void loadMainScreen() {
        Intent newIntent = new Intent(ps, MainScreen.class);
        ps.startActivity(newIntent);
    }

    void createNewProfile(String userName, int avatar) {
        pm.clear();
        pm.setUserName(userName);
        pm.setAvatar(avatar);
        pm.updateSequenceScore(45);
        pm.updatePairsScore(22);
        pm.updateImageScore(44);
        pm.updateSequenceScore(43);
        pm.updatePairsScore(24);
        pm.updateImageScore(67);
        pm.saveProfile();
        loadMainScreen();
    }

    private boolean loadProfile() {
        return false;
        /*try {
            pm.loadProfile();
            return pm.checkProfileExists();
        } catch (IOException e) {
            return false;
        }*/
    }

}
