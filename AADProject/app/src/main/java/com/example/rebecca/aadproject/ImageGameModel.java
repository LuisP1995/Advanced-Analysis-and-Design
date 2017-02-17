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

    private Context _mContext;
    private static int ROUNDS = 0;
    private static final int NUM_WORDS_ROUND = 5;
    private ArrayList<String[]> _gameData = new ArrayList<String[]>();

    ImageGameModel (Context context) {
        _mContext = context;
        ROUNDS = 0;
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        try {
            Resources res = _mContext.getResources();
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
                    _gameData.add(tempData);
                    ROUNDS++;
                }
            }
            parser.close();

            Collections.shuffle(_gameData);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    String[] getRound(int round) {
        return (round < ROUNDS ? _gameData.get(round) : null);
    }
}