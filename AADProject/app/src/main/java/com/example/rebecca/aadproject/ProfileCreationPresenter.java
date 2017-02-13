package com.example.rebecca.aadproject;

import android.content.Intent;

/**
 * Created by Adam on 29/12/2016.
 * Profile creation presenter class.
 */

class ProfileCreationPresenter {
    private ProfileModel _profileModel;
    private ProfileCreateScreen _profileScreen;

    ProfileCreationPresenter(ProfileCreateScreen profileScreen){
        _profileScreen = profileScreen;
        _profileModel = new ProfileModel(_profileScreen);
    }

    boolean profileExist(){
        return _profileModel.checkProfileExists();
    }

    void createNewProfile(String userName, int avatar) {
        _profileModel.clear();
        _profileModel.setUserName(userName);
        _profileModel.setAvatar(avatar);
        boolean successSave = _profileModel.saveProfile(false);
        if(!successSave) {
            Intent newIntent = new Intent(_profileScreen, ProfileCreateScreen.class);
            _profileScreen.startActivity(newIntent);
        }
    }
}
