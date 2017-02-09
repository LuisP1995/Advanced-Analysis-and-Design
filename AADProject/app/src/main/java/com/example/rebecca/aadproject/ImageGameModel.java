package com.example.rebecca.aadproject;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.Arrays;


/**
 * Created by Adam on 06/02/2017.
 */

public class ImageGameModel {

    private Context mContext;
    private static int ROUNDS = 2;
    private static int NUM_WORDS_ROUND = 5;
    private Object [] [] gameData = new Object[ROUNDS][NUM_WORDS_ROUND];

    ImageGameModel (Context context) {
        mContext = context;
        loadDataFromFile();
    }

    void loadDataFromFile() {
        try {
            Resources res = mContext.getResources();
            XmlResourceParser parser = res.getXml(R.xml.imagegamewords);
            int i = 0;
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                String data = "";
                if (name.equals("round")) {
                    for(int j = 0; j < NUM_WORDS_ROUND; j++)
                    {
                        parser.nextTag();
                        data = parser.nextText();
                        gameData[i][j] = data;
                    }
                    i++;
                }
            }
            parser.close();

        } catch (Exception e) {
            // TODO need to deal with this
            System.err.println(e.getMessage());
        }
    }

    String[] getRound(int round) {
        return Arrays.copyOf(gameData[round], gameData[round].length, String[].class);
    }
}
