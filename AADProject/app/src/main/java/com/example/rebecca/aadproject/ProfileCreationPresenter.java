package com.example.rebecca.aadproject;

/**
 * Created by Adam on 29/12/2016.
 */

public class ProfileCreationPresenter {
    private ProfileModel pm;
    private ProfileCreateScreen ps;

    ProfileCreationPresenter(ProfileCreateScreen ps){
        this.ps = ps;
        pm = new ProfileModel(this.ps);
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
    }
}
