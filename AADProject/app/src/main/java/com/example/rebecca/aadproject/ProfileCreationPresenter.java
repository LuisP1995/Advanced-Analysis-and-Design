package com.example.rebecca.aadproject;

import android.content.Intent;

/**
 * Created by Adam on 29/12/2016.
 */
//Test class to be removed

public class ProfileCreationPresenter {
    private ProfileModel pm;
    private ProfileCreateScreen ps;

    ProfileCreationPresenter(ProfileCreateScreen ps){
        this.ps = ps;
        pm = new ProfileModel(this.ps);
    }

    boolean profileExist(){
        return pm.checkProfileExists();
    }

    void createNewProfile(String userName, int avatar) {
        pm.clear();
        pm.setUserName(userName);
        pm.setAvatar(avatar);
        boolean successSave = pm.saveProfile(false);
        if(!successSave) {
            Intent newIntent = new Intent(ps, ProfileCreateScreen.class);
            ps.startActivity(newIntent);
        }
    }
}
