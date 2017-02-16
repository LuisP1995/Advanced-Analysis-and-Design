package com.example.rebecca.aadproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rebecca on 14/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SequenceControllerTests {
    @Mock
    SequenceScreen sequenceScreen;

    @Mock
    ImageButton imagebutton;
    @Mock
    ImageButton imagebutton1;

    @Mock
    ImageButton imagebutton2;

    @Mock
    ImageButton imagebutton3;

    @Mock
    ImageButton imagebutton4;

    @Mock
    ImageButton imagebutton5;

    @Mock
    TextView textView;

    @Mock
    Button button;

    @Before
    public void setup(){
        when(sequenceScreen.findViewById(R.id.seqRound)).thenReturn(textView);
        when(sequenceScreen.findViewById(R.id.seqImg1)).thenReturn(imagebutton);
        when(sequenceScreen.findViewById(R.id.seqImg2)).thenReturn(imagebutton1);
        when(sequenceScreen.findViewById(R.id.seqImg3)).thenReturn(imagebutton2);
        when(sequenceScreen.findViewById(R.id.seqImg4)).thenReturn(imagebutton3);
        when(sequenceScreen.findViewById(R.id.seqImg5)).thenReturn(imagebutton4);
        when(sequenceScreen.findViewById(R.id.seqImg6)).thenReturn(imagebutton5);
    }

    @Test
    public void CorrectButtonSetup()throws Exception{
        SequenceController sc = new SequenceController(new SequenceScreen());
        sc.SetupGame();

        assertThat(sc.getButtonList().size(),is(3));
        imagebutton.performClick();
        imagebutton1.performClick();
        imagebutton2.performClick();

        CharSequence text = textView.getText();
        assertEquals("Round: 2", text);
    }

    @Test
    public void AllButtonsUpdated()throws Exception{
        SequenceController sc = new SequenceController(sequenceScreen);

        sc.SetupGame();

        verify(sequenceScreen, times(7)).findViewById(Mockito.anyInt());

    }

    //check if has border

    //check the round updates
}