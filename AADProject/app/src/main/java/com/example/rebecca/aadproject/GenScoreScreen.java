package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class GenScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_score_screen);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_MESSAGE);

        TextView textview = (TextView)findViewById(R.id.score);
        textview.setText(message);
    }
}
