package com.example.rebecca.aadproject;

import org.xmlpull.v1.*;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;

/**
 * Created by Adam on 20/12/2016.
 *
 * This class contains the data for a users profile.
 * It interacts with the xml file "profile.xml" to load, save and update profile information.
 * Public functions allow the presenter classes to take this information and display it as views as well
 * as update the information as it changes.
 *
 */

public class ProfileModel {

    private static final String FILE_NAME = "profile.xml";
    private Context mContext;
    private static int HISTORY_AMOUNT = 5;

    private String userName;
    private int avatar;
    private float[] pairsScores = new float[HISTORY_AMOUNT];
    private int pairsPlays;
    private float[] sequenceScores = new float[HISTORY_AMOUNT];
    private int sequencePlays;
    private float[] imageScores = new float[HISTORY_AMOUNT];
    private int imagePlays;

    ProfileModel (Context mContext)
    {
        this.mContext = mContext;

        for(int i =0; i < HISTORY_AMOUNT; i++) {
            imageScores[i] = 0;
            pairsScores[i] = 0;
            sequenceScores[i] = 0;
        }

        //Only allowing profile create to happen once
        try
        {
            loadProfile();
        }catch(Exception ex){
            clear();
        }
    }

    void clear() {
        userName = "";
        avatar = -1;
        pairsPlays = 0;
        sequencePlays = 0;
        imagePlays = 0;
        for(int i =0; i < HISTORY_AMOUNT; i++) {
            imageScores[i] = 0;
            pairsScores[i] = 0;
            sequenceScores[i] = 0;
        }
    }

    boolean checkProfileExists() {
        return (!userName.isEmpty() && avatar >= 0);
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return userName;
    }

    void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    int getAvatar() {
        return avatar;
    }

    float[] getImageScores() {
        return imageScores;
    }

    int getImagePlays() {
        return imagePlays;
    }

    void updateImageScore(int newScore) {
        if(imagePlays == 0) {
            imageScores[imagePlays] = newScore;
        }
        else if(imagePlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                imageScores[i-1] = imageScores[i];
            }
            imageScores[HISTORY_AMOUNT-1] = ((imageScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            imageScores[imagePlays] = (imageScores[imagePlays-1] + newScore) / 2;
        }
        imagePlays++;
    }

    float[] getSequenceScores() {
        return sequenceScores;
    }

    int getSequencePlays() {
        return sequencePlays;
    }

    void updateSequenceScore(int newScore) {
        if(sequencePlays == 0) {
            sequenceScores[sequencePlays] = newScore;
        }
        else if(sequencePlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                sequenceScores[i-1] = sequenceScores[i];
            }
            sequenceScores[HISTORY_AMOUNT-1] = ((sequenceScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            sequenceScores[sequencePlays] = (sequenceScores[sequencePlays-1] + newScore) / 2;
        }
        sequencePlays++;
    }

    float[] getPairsScores() {
        return pairsScores;
    }

    int getPairsPlays() {
        return pairsPlays;
    }

    void updatePairsScore(int newScore) {
        if(pairsPlays == 0) {
            pairsScores[pairsPlays] = newScore;
        }
        else if(pairsPlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                pairsScores[i-1] = pairsScores[i];
            }
            pairsScores[HISTORY_AMOUNT-1] = ((pairsScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            pairsScores[pairsPlays] = (pairsScores[pairsPlays-1] + newScore) / 2;
        }
        pairsPlays++;
    }

    void loadProfile() throws IOException {
        Pair<float [], Integer> data;
        XmlPullParser parser = Xml.newPullParser();
        FileInputStream is = mContext.openFileInput(FILE_NAME);
        try {
            parser.setInput(new InputStreamReader(is));
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                switch (name)
                {
                    case "username":
                        if (parser.next() == XmlPullParser.TEXT) {
                            userName = parser.getText();
                            parser.nextTag();
                        }
                        break;
                    case "avatar":
                        if (parser.next() == XmlPullParser.TEXT) {
                            avatar = Integer.parseInt(parser.getText());
                            parser.nextTag();
                        }
                        break;
                    case "pairs":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            pairsScores[i] = data.first[i];
                        }
                        pairsPlays = data.second;
                        break;
                    case "sequence":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            sequenceScores[i] = data.first[i];
                        }
                        sequencePlays = data.second;
                        break;
                    case "image":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            imageScores[i] = data.first[i];
                        }
                        imagePlays = data.second;
                        break;
                    default:
                        break;
                }
            }
            is.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            // Should force create new profile.
        }
    }

    void saveProfile() {

        try {
            FileOutputStream os = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String profileData;
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "profile");
            xmlSerializer.startTag(null, "username");
            xmlSerializer.text(userName);
            xmlSerializer.endTag(null, "username");
            xmlSerializer.startTag(null, "avatar");
            xmlSerializer.text(Integer.toString(avatar));
            xmlSerializer.endTag(null, "avatar");
            xmlSerializer.startTag(null, "pairs");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(pairsScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(pairsPlays));
            xmlSerializer.endTag(null, "plays");
            xmlSerializer.endTag(null, "pairs");
            xmlSerializer.startTag(null, "sequence");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(sequenceScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(sequencePlays));
            xmlSerializer.endTag(null, "plays");
            xmlSerializer.endTag(null, "sequence");
            xmlSerializer.startTag(null, "image");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(imageScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(imagePlays));
            xmlSerializer.endTag(null, "plays");
            xmlSerializer.endTag(null, "image");
            xmlSerializer.endTag(null, "profile");
            xmlSerializer.endDocument();
            xmlSerializer.flush();

            // get the xml as a string
            profileData = writer.toString();

            // write the xml
            os.write(profileData.getBytes());

            os.close();
        } catch(Exception e) {
            Log.e("Error", e.getMessage());
            // application should crash or attempt 1 rewrite.
        }
    }

    private Pair<float[], Integer> extractScorePlays(XmlPullParser parser) throws Exception {
        float[] scores = new float[HISTORY_AMOUNT];
        int plays = 0;
        String name = "";

        while (parser.next() != XmlPullParser.END_TAG) {
            name = parser.getName();
            if(name.startsWith("score")) {
                if (parser.next() == XmlPullParser.TEXT) {
                    scores[Integer.parseInt(name.substring(name.length() - 1))] = Float.parseFloat(parser.getText());
                    parser.nextTag();
                }
            } else if(parser.getName().equals("plays")) {
                if (parser.next() == XmlPullParser.TEXT) {
                    plays = Integer.parseInt(parser.getText());
                    parser.nextTag();
                }
            }
        }
        return new Pair<>(scores, plays);
    }
}
