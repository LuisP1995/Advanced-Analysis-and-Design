package com.example.rebecca.aadproject;

import org.junit.Test;

import static org.junit.Assert.*;
import android.content.Context;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gameScoreUpdatesCorrectly() throws Exception {
//        Context c = null;
//        ProfileModel pm = new ProfileModel(c);
//
//        pm.updatePairsScore(10);
//        assertEquals("First pairs play takes exact number.", 10, pm.getPairsScore(), 0);
//        assertEquals("First pairs play increments plays to 1.", 1, pm.getPairsPlays());
//
//        pm.updatePairsScore(20);
//        assertEquals("Second pairs play calculates average.", 15, pm.getPairsScore(), 0);
//        assertEquals("Second pairs play increments plays to 2.", 2, pm.getPairsPlays());
//
//        pm.updateSequenceScore(10);
//        assertEquals("First sequence play takes exact number.", 10, pm.getSequenceScore(), 0);
//        assertEquals("First sequence play increments plays to 1.", 1, pm.getSequencePlays());
//
//        pm.updateSequenceScore(20);
//        assertEquals("Second sequence play calculates average.", 15, pm.getSequenceScore(), 0);
//        assertEquals("Second sequence play increments plays to 2.", 2, pm.getSequencePlays());
//
//        pm.updateImageScore(10);
//        assertEquals("First image play takes exact number.", 10, pm.getImageScore(), 0);
//        assertEquals("First image play increments plays to 1.", 1, pm.getImagePlays());
//
//        pm.updateImageScore(20);
//        assertEquals("Second image play calculates average.", 15, pm.getImageScore(), 0);
//        assertEquals("Second image play increments plays to 2.", 2, pm.getImagePlays());
    }

    @Test
    public void usernameUpdates() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        assertNull(pm.getUserName());

        pm.setUserName("username");

        assertEquals("Username updates correctly.", "username", pm.getUserName());
    }

    @Test
    public void avatarUpdates() throws Exception {
        Context c = null;
        ProfileModel pm = new ProfileModel(c);

        assertEquals("Check avatar starts as 0 default.", 0, pm.getAvatar());

        pm.setAvatar(5);

        assertEquals("Avatar updates correctly.", 5, pm.getAvatar());
    }
}