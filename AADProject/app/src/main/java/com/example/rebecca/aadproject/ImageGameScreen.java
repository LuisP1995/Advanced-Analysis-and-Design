package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageGameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game_screen);
        ImageGamePresenter igp = new ImageGamePresenter(this);
    }

    public void setScore(float score) {
        score = ((int) score * 100) / 100;
        String text = "Score: " + score;
        TextView scoreText = (TextView) findViewById(R.id.imageScoreText);
        scoreText.setText(text);
    }

    public void setRound(int round) {
        String text = "Round: " + (round + 1) ;
        TextView roundText = (TextView) findViewById(R.id.imageRoundText);
        roundText.setText(text);
    }

    public void setImage(String image) {
        ImageView gameImage = (ImageView) findViewById(R.id.imageGamePic);
    }

    public void setButton1Text(String text) {
        Button word_button = (Button) findViewById(R.id.wordBtn1);
        word_button.setText(text);
    }

    public void setButton2Text(String text) {
        Button word_button = (Button) findViewById(R.id.wordBtn2);
        word_button.setText(text);
    }

    public void setButton3Text(String text) {
        Button word_button = (Button) findViewById(R.id.wordBtn3);
        word_button.setText(text);
    }

    public void setButton4Text(String text) {
        Button word_button = (Button) findViewById(R.id.wordBtn4);
        word_button.setText(text);
    }

    public void setButton1Enabled(boolean enabled) {
        Button word_button = (Button) findViewById(R.id.wordBtn1);
        word_button.setEnabled(enabled);
    }

    public void setButton2Enabled(boolean enabled) {
        Button word_button = (Button) findViewById(R.id.wordBtn2);
        word_button.setEnabled(enabled);
    }

    public void setButton3Enabled(boolean enabled) {
        Button word_button = (Button) findViewById(R.id.wordBtn3);
        word_button.setEnabled(enabled);
    }

    public void setButton4Enabled(boolean enabled) {
        Button word_button = (Button) findViewById(R.id.wordBtn4);
        word_button.setEnabled(enabled);
    }
}
