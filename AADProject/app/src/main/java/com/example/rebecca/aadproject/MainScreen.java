package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        MainController mainController = new MainController(this);
    }

    void setSequenceGameState(boolean state) {
        Button seqButton = (Button) findViewById(R.id.seq_button);
        seqButton.setEnabled(state);
    }

    void setImageGameState(boolean state) {
        Button imgButton = (Button) findViewById(R.id.img_button);
        imgButton.setEnabled(state);
    }
}