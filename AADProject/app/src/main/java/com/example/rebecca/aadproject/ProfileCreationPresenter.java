package com.example.rebecca.aadproject;

/**
 * Created by Adam on 29/12/2016.
 * Profile creation presenter class.
 */

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
        pm.saveProfile();
    }
}
