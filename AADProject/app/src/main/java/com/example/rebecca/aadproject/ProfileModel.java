package com.example.rebecca.aadproject;

import org.xmlpull.v1.*;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import android.os.Debug;
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

class ProfileModel {

    private static final String FILE_NAME = "profile.xml";
    private Context _mContext;
    private static final int HISTORY_AMOUNT = 5;

    private String _userName;
    private int _avatar;
    private float[] _pairsScores = new float[HISTORY_AMOUNT];
    private int _pairsPlays;
    private float[] _sequenceScores = new float[HISTORY_AMOUNT];
    private int _sequencePlays;
    private float[] _imageScores = new float[HISTORY_AMOUNT];
    private int _imagePlays;

    ProfileModel (Context mContext)
    {
        _mContext = mContext;

        for(int i =0; i < HISTORY_AMOUNT; i++) {
            _imageScores[i] = 0;
            _pairsScores[i] = 0;
            _sequenceScores[i] = 0;
        }

        loadProfile();
    }

    void clear() {
        _userName = "";
        _avatar = -1;
        _pairsPlays = 0;
        _sequencePlays = 0;
        _imagePlays = 0;
        for(int i =0; i < HISTORY_AMOUNT; i++) {
            _imageScores[i] = 0;
            _pairsScores[i] = 0;
            _sequenceScores[i] = 0;
        }
    }

    boolean checkProfileExists() {
        return (_userName != null && _userName != "" && _avatar >= 0);
    }

    void setUserName(String userName) {
        _userName = userName;
    }

    String getUserName() {
        return _userName;
    }

    void setAvatar(int avatar) {
        _avatar = avatar;
    }

    int getAvatar() {
        return _avatar;
    }

    float[] getImageScores() {
        return _imageScores;
    }

    int getImagePlays() {
        return _imagePlays;
    }

    void updateImageScore(int newScore) {
        if(_imagePlays == 0) {
            _imageScores[_imagePlays] = newScore;
        }
        else if(_imagePlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                _imageScores[i-1] = _imageScores[i];
            }
            _imageScores[HISTORY_AMOUNT-1] = ((_imageScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            _imageScores[_imagePlays] = (_imageScores[_imagePlays -1] + newScore) / 2;
        }
        _imagePlays++;
    }

    float[] getSequenceScores() {
        return _sequenceScores;
    }

    int getSequencePlays() {
        return _sequencePlays;
    }

    void updateSequenceScore(int newScore) {
        if(_sequencePlays == 0) {
            _sequenceScores[_sequencePlays] = newScore;
        }
        else if(_sequencePlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                _sequenceScores[i-1] = _sequenceScores[i];
            }
            _sequenceScores[HISTORY_AMOUNT-1] = ((_sequenceScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            _sequenceScores[_sequencePlays] = (_sequenceScores[_sequencePlays -1] + newScore) / 2;
        }
        _sequencePlays++;
    }

    float[] getPairsScores() {
        return _pairsScores;
    }

    int getPairsPlays() {
        return _pairsPlays;
    }

    void updatePairsScore(int newScore) {
        if(_pairsPlays == 0) {
            _pairsScores[_pairsPlays] = newScore;
        }
        else if(_pairsPlays >= HISTORY_AMOUNT) {
            for(int i = 1; i < HISTORY_AMOUNT; i++) {
                _pairsScores[i-1] = _pairsScores[i];
            }
            _pairsScores[HISTORY_AMOUNT-1] = ((_pairsScores[HISTORY_AMOUNT-2]) + newScore) / 2;
        } else {
            _pairsScores[_pairsPlays] = (_pairsScores[_pairsPlays -1] + newScore) / 2;
        }
        _pairsPlays++;
    }

    void loadProfile() {
        Pair<float [], Integer> data;

        try {
            XmlPullParser parser = Xml.newPullParser();
            FileInputStream is = _mContext.openFileInput(FILE_NAME);
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
                            _userName = parser.getText();
                            parser.nextTag();
                        }
                        break;
                    case "avatar":
                        if (parser.next() == XmlPullParser.TEXT) {
                            _avatar = Integer.parseInt(parser.getText());
                            parser.nextTag();
                        }
                        break;
                    case "pairs":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            _pairsScores[i] = data.first[i];
                        }
                        _pairsPlays = data.second;
                        break;
                    case "sequence":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            _sequenceScores[i] = data.first[i];
                        }
                        _sequencePlays = data.second;
                        break;
                    case "image":
                        data = extractScorePlays(parser);
                        for(int i = 0; i < HISTORY_AMOUNT; i++) {
                            _imageScores[i] = data.first[i];
                        }
                        _imagePlays = data.second;
                        break;
                    default:
                        break;
                }
            }
            is.close();
        } catch (Exception e) {
            //Log.e("Error", e.getMessage());

            clear(); // forces create new profile
        }
    }

    boolean saveProfile(Boolean reWrite) {

        try {
            FileOutputStream os = _mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String profileData;
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "profile");
            xmlSerializer.startTag(null, "username");
            xmlSerializer.text(_userName);
            xmlSerializer.endTag(null, "username");
            xmlSerializer.startTag(null, "avatar");
            xmlSerializer.text(Integer.toString(_avatar));
            xmlSerializer.endTag(null, "avatar");
            xmlSerializer.startTag(null, "pairs");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(_pairsScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(_pairsPlays));
            xmlSerializer.endTag(null, "plays");
            xmlSerializer.endTag(null, "pairs");
            xmlSerializer.startTag(null, "sequence");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(_sequenceScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(_sequencePlays));
            xmlSerializer.endTag(null, "plays");
            xmlSerializer.endTag(null, "sequence");
            xmlSerializer.startTag(null, "image");
            for(int i = 0; i < HISTORY_AMOUNT; i++){
                xmlSerializer.startTag(null, "score"+i);
                xmlSerializer.text(Float.toString(_imageScores[i]));
                xmlSerializer.endTag(null, "score"+i);
            }
            xmlSerializer.startTag(null, "plays");
            xmlSerializer.text(Integer.toString(_imagePlays));
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
//            Log.e("Error", e.getMessage());
            if(!reWrite) {
                saveProfile(true); // attempt another save
            } else {
                return false;
            }
        }
        return true;
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

    public float[] getScores(String game) {
        switch(game){
            case "Sequence": return getSequenceScores();
            case "Pairs": return getPairsScores();
            case "Image": return getImageScores();
        }
        return new float[]{};
     }

    public void updateScore(String game, int score) {
        switch(game){
            case "Sequence": updateSequenceScore(score);
                break;
            case "Pairs": updatePairsScore(score);
                break;
            case "Image": updateImageScore(score);
                break;
        }
    }
}
