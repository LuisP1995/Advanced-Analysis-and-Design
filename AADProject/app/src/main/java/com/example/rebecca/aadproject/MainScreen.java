package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        SetButtonListeners();

    }

    private void SetButtonListeners() {
        Button settings_button = (Button) findViewById(R.id.settings_button);
        settings_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent newIntent = new Intent(MainScreen.this, SettingScreen.class);
                startActivity(newIntent);
            }
        });

        Button profile_button = (Button) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent newIntent = new Intent(MainScreen.this, ProfileScreen.class);
                startActivity(newIntent);
            }
        });

        Button info_button = (Button) findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Information Here", Toast.LENGTH_LONG).show();
            }
        });
    }
}
