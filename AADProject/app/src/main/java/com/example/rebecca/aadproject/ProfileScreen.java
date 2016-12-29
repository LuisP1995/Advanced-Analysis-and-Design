package com.example.rebecca.aadproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

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

        GraphView graph = (GraphView) findViewById(R.id.pairsGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}
