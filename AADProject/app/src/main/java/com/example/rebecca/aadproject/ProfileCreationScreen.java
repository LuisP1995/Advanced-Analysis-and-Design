package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileCreationScreen extends AppCompatActivity {

    private ProfileCreationPresenter pcp;
    private int avatar = -1;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageButton imgbut = (ImageButton)findViewById(R.id.avatar1);
        imgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = 1;
            }
        });

        pcp = new ProfileCreationPresenter(this);
    }

    protected void createNewProfile() {
        //should fire as event
        pcp.createNewProfile(userName, avatar);
    }

    protected void showForm() {
        setContentView(R.layout.activity_profile_creation_screen);
        SetButtonListeners();
    }

    private void SetButtonListeners() {
        Button submit_button = (Button) findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText textField = (EditText)findViewById(R.id.user_name);
                userName = textField.getText().toString();

                if(profileValid()) {
                    createNewProfile();
                }
            }
        });
    }

//    protected void setAvatar(View v) {
//        switch (v.getId()) {
//            case (R.id.avatar1):
//                avatar = 1;
//                break;
//            case (R.id.avatar2):
//                avatar = 2;
//                break;
//            case (R.id.avatar3):
//                avatar = 3;
//                break;
//            case (R.id.avatar4):
//                avatar = 4;
//                break;
//        }
//    }

    protected boolean profileValid() {
        return (userName != "" && avatar != -1);
    }
}
