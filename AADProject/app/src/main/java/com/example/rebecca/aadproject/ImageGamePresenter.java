package com.example.rebecca.aadproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Adam on 05/02/2017.
 */

public class ImageGamePresenter {

    private static final int MAX_ROUNDS = 2;
    private static final int MAX_TRYS = 3;
    private static final int ROUND_SCORE = 30;

    private String correctAnswer = "";
    private int [] wrongAnswers = new int[MAX_ROUNDS];
    private static int tryCount = 0;
    private static int round = 0;
    private static float score = 0;
    ImageGameModel gm;
    ImageGameScreen igs;


    ImageGamePresenter(ImageGameScreen igs) {
        this.igs = igs;
        for (int i = 0; i < MAX_ROUNDS; i++) {
            wrongAnswers[i] = 0;
        }
        gm = new ImageGameModel(this.igs.getApplicationContext());
        setButtonListeners();
        loadNextRound();
    }

    void playedRound(String selectedW) {
        if(checkCorrect(selectedW)) {
            round++;
            updateScore();
            if(round == MAX_ROUNDS) {
                displayComplitionScreen();
            } else {
                loadNextRound();
            }
        } else {
            tryCount++;
            if (tryCount == MAX_TRYS) {
                // max tries exceeded save wrong word and load next round
                wrongAnswers[round] = 1;
                round++;
                if(round == MAX_ROUNDS) {
                    displayComplitionScreen();
                } else {
                    loadNextRound();
                }
            }
        }
    }

    void displayComplitionScreen(){

        /*GraphView graph = (GraphView) igs.findViewById(R.id.imageCompletionGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);*/

        Intent newIntent = new Intent(igs, ImageGameComplitionScreen.class);
        igs.startActivity(newIntent);
        TextView newScore = (TextView) igs.findViewById(R.id.score);
        newScore.setText("Aia");
    }

    void loadNextRound() {
        tryCount = 0;
        igs.setRound(round);
        igs.setScore(score);

        String[] roundData = gm.getRound(round);
        correctAnswer = roundData[1];
        String [] words = {roundData[1], roundData[2], roundData[3], roundData[4]};
        Collections.shuffle(Arrays.asList(words));

        igs.setButton1Enabled(true);
        igs.setButton2Enabled(true);
        igs.setButton3Enabled(true);
        igs.setButton4Enabled(true);

        // randomise below
        igs.setButton1Text(words[0]);
        igs.setButton2Text(words[1]);
        igs.setButton3Text(words[2]);
        igs.setButton4Text(words[3]);
    }

    void setButtonListeners() {
        final Button word1_button = (Button) igs.findViewById(R.id.wordBtn1);
        word1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igs.setButton1Enabled(checkCorrect(word1_button.getText().toString()));
                playedRound(word1_button.getText().toString());
            }

        });

        final Button word2_button = (Button) igs.findViewById(R.id.wordBtn2);
        word2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igs.setButton2Enabled(checkCorrect(word2_button.getText().toString()));
                playedRound(word2_button.getText().toString());
            }

        });

        final Button word3_button = (Button) igs.findViewById(R.id.wordBtn3);
        word3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igs.setButton3Enabled(checkCorrect(word3_button.getText().toString()));
                playedRound(word3_button.getText().toString());
            }

        });

        final Button word4_button = (Button) igs.findViewById(R.id.wordBtn4);
        word4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igs.setButton4Enabled(checkCorrect(word4_button.getText().toString()));
                playedRound(word4_button.getText().toString());
            }

        });
    }

    boolean checkCorrect(String selectedW) {
        return correctAnswer == selectedW;
    }

    void updateScore() {
        score += ROUND_SCORE/(tryCount+1);
    }
}
