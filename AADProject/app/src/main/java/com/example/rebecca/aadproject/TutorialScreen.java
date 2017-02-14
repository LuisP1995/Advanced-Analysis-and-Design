package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class TutorialScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_screen);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_MESSAGE);

        TutorialPresenter tutorialPresenter = new TutorialPresenter(this);
        tutorialPresenter.showTutorial(message);
    }
}