package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProfileScreen extends AppCompatActivity {

    static final String GAMES_PLAYED_DEFAULT = "Games Played: ";
    static final String AVERAGE_SCORE_DEFAULT = "Average Score: ";

    private ProfileScreenController _psp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        _psp = new ProfileScreenController(this);

        tabSetup();
        graphSetup();

        TextView textview = (TextView)findViewById(R.id.username_text);
        textview.setText(_psp.getUserName());
        setAvatar();
    }

    private void setAvatar() {
        ImageView imageButton = (ImageView)findViewById(R.id.avatarImage);
        int avatar = _psp.getAvatar();

        if(avatar == 1){
            imageButton.setImageResource(R.mipmap.purplediamond);
        }
        if(avatar == 2){
            imageButton.setImageResource(R.mipmap.bluetriangle);
        }
        if(avatar == 3){
            imageButton.setImageResource(R.mipmap.greensqr);
        }
        if(avatar == 4){
            imageButton.setImageResource(R.mipmap.orangecircle);
        }
    }

    protected void graphSetup() {
        GraphView graph = (GraphView) findViewById(R.id.pairsGraph);
        TextView gamesPlayed = (TextView) findViewById(R.id.pairsGp);
        TextView averageScore = (TextView) findViewById(R.id.pairsAvg);
        String toDisplay = "";

        float[] scores = _psp.getPairsScoresData();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);

        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + _psp.getPairsGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + _psp.getPairsAverage();
        averageScore.setText(toDisplay);

        graph = (GraphView) findViewById(R.id.seqGraph);
        gamesPlayed = (TextView) findViewById(R.id.seqGp);
        averageScore = (TextView) findViewById(R.id.seqAvg);
        scores = _psp.getSequenceScoresData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);
        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + _psp.getSequenceGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + _psp.getSequenceAverage();
        averageScore.setText(toDisplay);

        graph = (GraphView) findViewById(R.id.imageGraph);
        gamesPlayed = (TextView) findViewById(R.id.imgGp);
        averageScore = (TextView) findViewById(R.id.imgAvg);
        scores = _psp.getImageScoresData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);
        // toDisplay declared before concatenation while set texts is prone to break
        toDisplay = GAMES_PLAYED_DEFAULT + _psp.getImageGamesPlayed();
        gamesPlayed.setText(toDisplay);
        toDisplay = AVERAGE_SCORE_DEFAULT + _psp.getImageAverage();
        averageScore.setText(toDisplay);
    }

    private void tabSetup() {
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
