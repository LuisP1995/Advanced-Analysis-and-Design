package com.example.rebecca.aadproject;

import org.junit.Test;

import static org.junit.Assert.*;
import android.content.Context;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProfileUnitTests {

    @Test
    public void pairGameScoreUpdatesCorrectly() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        for (int i =0; i <pm.getPairsScores().length; i++)
        {
            assertEquals("Assert all pairs scores are 0 to start.", 0, pm.getPairsScores()[i], 0);
        }

        pm.updatePairsScore(10);
        assertEquals("Assert pairs score updates correctly position 1.", 10, pm.getPairsScores()[0], 0);

        pm.updatePairsScore(20);
        assertEquals("Assert pairs score updates correctly position 2.", 15, pm.getPairsScores()[1], 0);

        pm.updatePairsScore(30);
        assertEquals("Assert pairs score updates correctly position 3.", 22.5, pm.getPairsScores()[2], 0);

        pm.updatePairsScore(40);
        assertEquals("Assert pairs score updates correctly position 4.", 31.25, pm.getPairsScores()[3], 0);

        pm.updatePairsScore(50);
        assertEquals("Assert pairs score updates correctly position 5.", 40.625, pm.getPairsScores()[4], 0);

        pm.updatePairsScore(50);
        assertEquals("Assert pairs score updates correctly position 1.", 15, pm.getPairsScores()[0], 0);
        assertEquals("Assert pairs score updates correctly position 2.", 22.5, pm.getPairsScores()[1], 0);
        assertEquals("Assert pairs score updates correctly position 3.", 31.25, pm.getPairsScores()[2], 0);
        assertEquals("Assert pairs score updates correctly position 4.", 40.625, pm.getPairsScores()[3], 0);
        assertEquals("Assert pairs score updates correctly position 5.", 45.3125, pm.getPairsScores()[4], 0);
    }

    @Test
    public void imageGameScoreUpdatesCorrectly() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        for (int i =0; i <pm.getImageScores().length; i++)
        {
            assertEquals("Assert all image scores are 0 to start.", 0, pm.getImageScores()[i], 0);
        }

        pm.updateImageScore(10);
        assertEquals("Assert image score updates correctly position 1.", 10, pm.getImageScores()[0], 0);

        pm.updateImageScore(20);
        assertEquals("Assert image score updates correctly position 2.", 15, pm.getImageScores()[1], 0);

        pm.updateImageScore(30);
        assertEquals("Assert image score updates correctly position 3.", 22.5, pm.getImageScores()[2], 0);

        pm.updateImageScore(40);
        assertEquals("Assert image score updates correctly position 4.", 31.25, pm.getImageScores()[3], 0);

        pm.updateImageScore(50);
        assertEquals("Assert image score updates correctly position 5.", 40.625, pm.getImageScores()[4], 0);

        pm.updateImageScore(50);
        assertEquals("Assert image score updates correctly position 1.", 15, pm.getImageScores()[0], 0);
        assertEquals("Assert image score updates correctly position 2.", 22.5, pm.getImageScores()[1], 0);
        assertEquals("Assert image score updates correctly position 3.", 31.25, pm.getImageScores()[2], 0);
        assertEquals("Assert image score updates correctly position 4.", 40.625, pm.getImageScores()[3], 0);
        assertEquals("Assert image score updates correctly position 5.", 45.3125, pm.getImageScores()[4], 0);
    }

    @Test
    public void sequenceGameScoreUpdatesCorrectly() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        for (int i =0; i <pm.getSequenceScores().length; i++)
        {
            assertEquals("Assert all sequence scores are 0 to start.", 0, pm.getSequenceScores()[i], 0);
        }

        pm.updateSequenceScore(10);
        assertEquals("Assert sequence score updates correctly position 1.", 10, pm.getSequenceScores()[0], 0);

        pm.updateSequenceScore(20);
        assertEquals("Assert sequence score updates correctly position 2.", 15, pm.getSequenceScores()[1], 0);

        pm.updateSequenceScore(30);
        assertEquals("Assert sequence score updates correctly position 3.", 22.5, pm.getSequenceScores()[2], 0);

        pm.updateSequenceScore(40);
        assertEquals("Assert sequence score updates correctly position 4.", 31.25, pm.getSequenceScores()[3], 0);

        pm.updateSequenceScore(50);
        assertEquals("Assert sequence score updates correctly position 5.", 40.625, pm.getSequenceScores()[4], 0);

        pm.updateSequenceScore(50);
        assertEquals("Assert sequence score updates correctly position 1.", 15, pm.getSequenceScores()[0], 0);
        assertEquals("Assert sequence score updates correctly position 2.", 22.5, pm.getSequenceScores()[1], 0);
        assertEquals("Assert sequence score updates correctly position 3.", 31.25, pm.getSequenceScores()[2], 0);
        assertEquals("Assert sequence score updates correctly position 4.", 40.625, pm.getSequenceScores()[3], 0);
        assertEquals("Assert sequence score updates correctly position 5.", 45.3125, pm.getSequenceScores()[4], 0);
    }

    @Test
    public void usernameUpdates() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        assertEquals("Username is empty to start.", "", pm.getUserName());

        pm.setUserName("username");

        assertEquals("Username updates correctly.", "username", pm.getUserName());
    }

    @Test
    public void avatarUpdates() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        assertEquals("Check avatar starts as -1 default.", -1, pm.getAvatar());

        pm.setAvatar(5);

        assertEquals("Avatar updates correctly.", 5, pm.getAvatar());
    }
}