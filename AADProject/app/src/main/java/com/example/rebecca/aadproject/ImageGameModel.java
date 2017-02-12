package com.example.rebecca.aadproject;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Adam on 06/02/2017.
 */

class ImageGameModel {

    private Context mContext;
    private static int rounds = 0;
    private static final int NUM_WORDS_ROUND = 5;
    private ArrayList<String[]> gameData = new ArrayList<String[]>();

    ImageGameModel (Context context) {
        mContext = context;
        rounds = 0;
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        try {
            Resources res = mContext.getResources();
            XmlResourceParser parser = res.getXml(R.xml.imagegamewords);
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                String data = "";
                if (name.equals("round")) {
                    String[] tempData = new String[NUM_WORDS_ROUND];
                    for(int j = 0; j < NUM_WORDS_ROUND; j++)
                    {
                        parser.nextTag();
                        data = parser.nextText();
                        tempData[j] = data;
                    }
                    gameData.add(tempData);
                    rounds++;
                }
            }
            parser.close();

            Collections.shuffle(gameData);

        } catch (Exception e) {
            // TODO need to deal with this
            System.err.println(e.getMessage());
        }
    }

    String[] getRound(int round) {
        return (round < rounds ? gameData.get(round) : null);
    }
}
