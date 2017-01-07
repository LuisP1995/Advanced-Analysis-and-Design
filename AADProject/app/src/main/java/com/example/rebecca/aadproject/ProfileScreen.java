package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProfileScreen extends AppCompatActivity {

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

    private void GraphSetup() {
        GraphView graph = (GraphView) findViewById(R.id.pairsGraph);
        float[] scores = psp.getPairsData();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);


        graph = (GraphView) findViewById(R.id.seqGraph);
        scores = psp.getSequenceData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);

        graph = (GraphView) findViewById(R.id.imageGraph);
        scores = psp.getImageData();
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, scores[0]),
                new DataPoint(1, scores[1]),
                new DataPoint(2, scores[2]),
                new DataPoint(3, scores[3]),
                new DataPoint(4, scores[4])
        });
        graph.addSeries(series);

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
