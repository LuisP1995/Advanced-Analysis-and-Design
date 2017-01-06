package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        SetButtonListeners();
    }

    private void SetButtonListeners() {
        Button settings_button = (Button) findViewById(R.id.settings_button);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainScreen.this, SettingScreen.class);
                startActivity(newIntent);
            }
        });

        Button profile_button = (Button) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainScreen.this, ProfileScreen.class);
                startActivity(newIntent);
            }
        });

        Button info_button = (Button) findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Information Here", Toast.LENGTH_LONG).show();
            }
        });

        Button pairs_button = (Button) findViewById(R.id.pairs_button);
        pairs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainScreen.this, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Pairs");
                startActivity(newIntent);
            }
        });

        Button seq_button = (Button) findViewById(R.id.seq_button);
        seq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainScreen.this, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Seq");
                startActivity(newIntent);
            }
        });

        Button img_button = (Button) findViewById(R.id.img_button);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainScreen.this, TutorialScreen.class);
                newIntent.putExtra(EXTRA_MESSAGE, "Image");
                startActivity(newIntent);
            }
        });
    }
}
