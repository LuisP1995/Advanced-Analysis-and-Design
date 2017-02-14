package com.example.rebecca.aadproject;

import android.content.Intent;
import android.widget.ImageButton;

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

        setAvatarImages();
    }

    private void setAvatarImages() {
        ImageButton button1 = (ImageButton) _profileScreen.findViewById(R.id.avatar1);
        button1.setImageResource(R.mipmap.purplediamond);
        ImageButton button2 = (ImageButton) _profileScreen.findViewById(R.id.avatar2);
        button2.setImageResource(R.mipmap.bluetriangle);
        ImageButton button3 = (ImageButton) _profileScreen.findViewById(R.id.avatar3);
        button3.setImageResource(R.mipmap.greensqr);
        ImageButton button4 = (ImageButton) _profileScreen.findViewById(R.id.avatar4);
        button4.setImageResource(R.mipmap.orangecircle);
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
