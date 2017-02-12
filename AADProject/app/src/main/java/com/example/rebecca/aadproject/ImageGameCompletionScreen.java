package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ImageGameCompletionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game_complition_screen);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            ImageGameCompletionPresenter imageCompPres = new ImageGameCompletionPresenter(this, extras.getFloat("newScore"), (String[][])extras.getSerializable("wrongAnswers"));
        }

    }

    void setScore(float newScore) {
        TextView score = (TextView) findViewById(R.id.imageCompletionScore);
        score.setText(String.valueOf((int)newScore));
    }

    void setGraph(float[] imageData) {
        TextView newAverage = (TextView) findViewById(R.id.imageComplitionAverage);
        newAverage.setText(String.valueOf(getNewAverageScore(imageData)));

        GraphView graph = (GraphView) findViewById(R.id.imageCompletionGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, imageData[0]),
                new DataPoint(1, imageData[1]),
                new DataPoint(2, imageData[2]),
                new DataPoint(3, imageData[3]),
                new DataPoint(4, imageData[4])
        });
        graph.addSeries(series);
    }

    void disableWrongWords() {
        Button wrongWords_button = (Button) findViewById(R.id.imageComplitionWrong);
        wrongWords_button.setEnabled(false);
    }

    void alterNextState(boolean enable) {
        Button next_button = (Button) findViewById(R.id.imageNextBtn);
        next_button.setEnabled(enable);
    }

    void alterPreviousState(boolean enable) {
        Button previous_button = (Button) findViewById(R.id.imagePreviousBtn);
        previous_button.setEnabled(enable);
    }

    private int getNewAverageScore(float[] data) {
        for(int i = 0; i <data.length; i++) {
            if(data[i] == 0) {
                return i == 0 ? 0 : Math.round(data[i-1]);
            }
        }
        return Math.round(data[data.length-1]);
    }
}
