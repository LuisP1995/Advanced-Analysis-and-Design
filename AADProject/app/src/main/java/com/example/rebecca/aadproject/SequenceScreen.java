package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SequenceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence_game);

        SequenceController contr = new SequenceController(this);
        contr.SetupGame();

    }
}
