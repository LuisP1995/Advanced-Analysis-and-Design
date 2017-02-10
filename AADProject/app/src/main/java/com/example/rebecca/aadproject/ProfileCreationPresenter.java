package com.example.rebecca.aadproject;

import android.content.Intent;

/**
 * Created by Adam on 29/12/2016.
 * Profile creation presenter class.
 */

class ProfileCreationPresenter {
    private ProfileModel profileModel;
    private ProfileCreateScreen profileScreen;

    ProfileCreationPresenter(ProfileCreateScreen profileScreen){
        this.profileScreen = profileScreen;
        profileModel = new ProfileModel(this.profileScreen);
    }

    boolean profileExist(){
        return profileModel.checkProfileExists();
    }

    void createNewProfile(String userName, int avatar) {
        profileModel.clear();
        profileModel.setUserName(userName);
        profileModel.setAvatar(avatar);
        boolean successSave = profileModel.saveProfile(false);
        if(!successSave) {
            Intent newIntent = new Intent(profileScreen, ProfileCreateScreen.class);
            profileScreen.startActivity(newIntent);
        }
    }
}
