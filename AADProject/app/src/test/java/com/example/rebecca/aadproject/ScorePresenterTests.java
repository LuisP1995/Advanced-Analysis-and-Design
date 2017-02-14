package com.example.rebecca.aadproject;

import android.app.Instrumentation;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rebecca on 14/02/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ScorePresenterTests {
    @Mock
    ImageGameCompletionScreen screenMock;

    @Mock
    Button button;

    @Before
    public void setup(){
        when(screenMock.findViewById(R.id.imageComplitionQuit)).thenReturn(button);
        when(screenMock.findViewById(R.id.imageComplitionWrong)).thenReturn(button);
        when(screenMock.findViewById(R.id.ImageComplitionPlayAgain)).thenReturn(button);
    }

    @Test
    public void genericTest(){
        ScorePresenter sp = new ScorePresenter(screenMock, 1, "Image");

        verify(screenMock, times(2)).findViewById(Mockito.anyInt());
    }

}
