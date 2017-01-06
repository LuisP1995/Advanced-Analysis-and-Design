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

        StartGame(message);
    }

    private void StartGame(String message) {
        if (message.equals("Pairs")){
            Button button = (Button)findViewById(R.id.proceed);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(TutorialScreen.this, SplashScreen.class); //Change to pairsGame
                    startActivity(newIntent);
                }
            });
        }
        if (message.equals("Seq")){
            Button button = (Button)findViewById(R.id.proceed);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(TutorialScreen.this, SplashScreen.class); //Change to SequenceGame
                    startActivity(newIntent);
                }
            });
        }
        if (message.equals("Image")){
            Button button = (Button)findViewById(R.id.proceed);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(TutorialScreen.this, SplashScreen.class); //Change to ImageGame
                    startActivity(newIntent);
                }
            });
        }
    }
}
