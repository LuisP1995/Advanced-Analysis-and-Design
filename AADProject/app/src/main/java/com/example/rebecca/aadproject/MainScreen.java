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
        MainPresenter mainPresenter = new MainPresenter(this);
    }

    void setSequenceGameState(boolean state) {
        Button seq_button = (Button) findViewById(R.id.seq_button);
        seq_button.setEnabled(state);
    }

    void setImageGameState(boolean state) {
        Button img_button = (Button) findViewById(R.id.img_button);
        img_button.setEnabled(state);
    }


}
