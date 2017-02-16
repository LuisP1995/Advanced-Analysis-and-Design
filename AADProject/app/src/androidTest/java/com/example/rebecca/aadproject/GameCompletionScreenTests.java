package com.example.rebecca.aadproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Adam on 16/02/2017.
 */


@RunWith(AndroidJUnit4.class)
public class GameCompletionScreenTests {

    @Rule
    public ActivityTestRule<GameCompletionScreen> mActivityRule = new ActivityTestRule<>(GameCompletionScreen.class);

    @Test
    public void checkCompletionScreenDisplaysData() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        ProfileModel profileModel = new ProfileModel(appContext);
        float[] pairsScores = profileModel.getPairsScores();
        int pairsPlays  = profileModel.getPairsPlays();

        Intent intent = new Intent(appContext, GameCompletionScreen.class);

        Bundle bundle = new Bundle();
        bundle.putFloat("newScore", 40);
        bundle.putString("game", "Pairs");
        intent.putExtras(bundle);

        GameCompletionScreen completionScreen = mActivityRule.launchActivity(intent);

        TextView newAverage = (TextView) completionScreen.findViewById(R.id.imageComplitionAverage);
        assertNotEquals("Make sure the average score is updated.","",newAverage.getText());

        TextView completionScore = (TextView) completionScreen.findViewById(R.id.imageCompletionScore);
        assertNotEquals("Make sure the score is updated.","",completionScore.getText());
        assertEquals("Make sure new score is correct.", "40", completionScore.getText());

        GraphView graph = (GraphView) completionScreen.findViewById(R.id.imageCompletionGraph);
        List<Series> series = graph.getSeries();

        assertNotEquals("Make sure that the graph is populated.", 0, series.size());
    }

}
