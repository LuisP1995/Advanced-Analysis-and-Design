package com.example.rebecca.aadproject;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        String instruction = "1. Select the word that most accurately matches the picture.";
        TextView textview = (TextView)findViewById(R.id.step1);
        textview.setText(instruction);

        instruction = "2. If you get the wrong word, then that word will be disabled. Select another.";
        textview = (TextView)findViewById(R.id.step2);
        textview.setText(instruction);

        instruction = "3. You will have a total of 3 tries per round.";
        textview = (TextView)findViewById(R.id.step3);
        textview.setText(instruction);

        instruction = "4. Hit \"Proceed\" to play!";
        textview = (TextView)findViewById(R.id.step4);
        textview.setText(instruction);

        ImageView tutImage = (ImageView) findViewById(R.id.TutImg1);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) findViewById(R.id.TutImg2);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) findViewById(R.id.TutImg3);
        tutImage.setVisibility(View.INVISIBLE);

        tutImage = (ImageView) findViewById(R.id.TutImg4);
        tutImage.setVisibility(View.INVISIBLE);

        SetProceedButton(ImageGameScreen.class);

    }

    private void StartSeq() {
        //Set step texts
        //Set images
        SetProceedButton(SplashScreen.class); //Change to gameClass
    }

    private void StartPairs() {
        //Set step texts
        //Set images
        SetProceedButton(PairsGame.class); //Change to gameClass
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
