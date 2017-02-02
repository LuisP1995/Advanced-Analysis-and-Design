package com.example.rebecca.aadproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProfileScreen extends AppCompatActivity {

    static final String GAMES_PLAYED_DEFAULT = "Games Played: ";
    static final String AVERAGE_SCORE_DEFAULT = "Average Score: ";

    private ProfileScreenPresenter psp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        psp = new ProfileScreenPresenter(this);

        TabSetup();
        GraphSetup();

        TextView textview = (TextView)findViewById(R.id.username_text);
        textview.setText(psp.getUserName());
        //ScoreSetup
    }

    protected void GraphSetup() {
        GraphView graph = (GraphView) findViewById(R.id.pairsGraph);
        TextView gamesPlayed = (TextView) findViewById(R.id.pairsGp);
        TextView averageScore = (TextView) findViewById(R.id.pairsAvg);
        String toDisplay = "";

        float[] scores = psp.getPairsScoresData();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);

        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + psp.getPairsGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + psp.getPairsAverage();
        averageScore.setText(toDisplay);

        graph = (GraphView) findViewById(R.id.seqGraph);
        gamesPlayed = (TextView) findViewById(R.id.seqGp);
        averageScore = (TextView) findViewById(R.id.seqAvg);
        scores = psp.getSequenceScoresData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);
        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + psp.getSequenceGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + psp.getSequenceAverage();
        averageScore.setText(toDisplay);

        graph = (GraphView) findViewById(R.id.imageGraph);
        gamesPlayed = (TextView) findViewById(R.id.imgGp);
        averageScore = (TextView) findViewById(R.id.imgAvg);
        scores = psp.getImageScoresData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);
        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + psp.getImageGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + psp.getImageAverage();
        averageScore.setText(toDisplay);
    }

    private void TabSetup() {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Pairs");
        spec.setContent(R.id.pairs);
        spec.setIndicator("Pairs");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Sequence");
        spec.setContent(R.id.sequence);
        spec.setIndicator("Sequence");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Image");
        spec.setContent(R.id.image);
        spec.setIndicator("Image");
        host.addTab(spec);
    }
}
