package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class TutorialScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_screen);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_MESSAGE);

        TextView textview = (TextView)findViewById(R.id.step1);
        textview.setText(message);

        if (message.equals("Pairs")){
            StartPairs();
        }
        if (message.equals("Seq")){
            StartSeq();
        }
        if (message.equals("Image")){
            StartImg();
        }
    }

    private void StartImg() {
        //Set step texts
        //Set images
        SetProceedButton(SplashScreen.class); //Change to gameClass
    }

    private void StartSeq() {
        //Set step texts
        //Set images
        SetProceedButton(SplashScreen.class); //Change to gameClass
    }

    private void StartPairs() {
        //Set step texts
        //Set images
        SetProceedButton(SplashScreen.class); //Change to gameClass
    }

    private void SetProceedButton(final Class Screen) {
        Button button = (Button)findViewById(R.id.proceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(TutorialScreen.this, Screen);
                startActivity(newIntent);
            }
        });
    }

}
