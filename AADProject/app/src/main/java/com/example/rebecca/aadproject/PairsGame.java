package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PairsGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_game);

        Button exitBtn = (Button)findViewById(R.id.pairsExit);

        exitBtn.setOnClickListener(new View.OnClickListener() //navigate back to the main screen
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(PairsGame.this, GenScoreScreen.class);
                startActivity(newIntent);
            }
        });
    }
}
