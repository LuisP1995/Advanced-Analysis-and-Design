package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Thread myThread = new Thread(); //for splash screen purposes

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v)
    {
        Intent newIntent = new Intent(MainActivity.this, MainScreen.class);
        newIntent.putExtra(EXTRA_MESSAGE, "Zu Li");
        startActivity(newIntent);
    }
}
