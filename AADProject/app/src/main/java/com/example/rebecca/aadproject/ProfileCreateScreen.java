package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileCreateScreen extends AppCompatActivity {

    private ProfileCreationPresenter pcp;
    private int avatar = -1;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create_screen);

        pcp = new ProfileCreationPresenter(this);

        if(!pcp.profileExist()) {
            SetAvatarButtonListeners();

            SetSubmitButtonListener();
        }
        else
        {
            Intent newIntent = new Intent(ProfileCreateScreen.this, MainScreen.class);
            startActivity(newIntent);
        }
    }

    private void SetSubmitButtonListener() {
        Button submit_button = (Button) findViewById(R.id.submit);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText textField = (EditText)findViewById(R.id.username);
                userName = textField.getText().toString();

                if(profileValid()) {
                    createNewProfile();
                    Intent newIntent = new Intent(ProfileCreateScreen.this, MainScreen.class);
                    startActivity(newIntent);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SetAvatarButtonListeners() {
        ImageButton avt1 = (ImageButton)findViewById(R.id.avatar1);
        avt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = 1;
            }
        });

        ImageButton avt2 = (ImageButton)findViewById(R.id.avatar2);
        avt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = 2;
            }
        });

        ImageButton avt3 = (ImageButton)findViewById(R.id.avatar3);
        avt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = 3;
            }
        });

        ImageButton avt4 = (ImageButton)findViewById(R.id.avatar4);
        avt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = 4;
            }
        });
    }

    protected boolean profileValid() {
        return (userName != "" && avatar != -1);
    }

    protected void createNewProfile() {
        pcp.createNewProfile(userName, avatar);
    }
}
