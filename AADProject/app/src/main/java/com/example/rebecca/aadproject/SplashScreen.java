package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        Thread myThread = new Thread(); //for splash screen purposes

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(SplashScreen.this);
    }

    @Override
    public void onClick(View v)
    {
        Intent newIntent = new Intent(SplashScreen.this, MainScreen.class); //Instead main to CreateProfile
        startActivity(newIntent);
    }
}
