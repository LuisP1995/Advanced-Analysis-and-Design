package com.example.rebecca.aadproject;

import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rebecca on 16/02/2017.
 */

public class SequenceModel {
    private SequenceScreen _screen;
    private ArrayList<ImageButton> _buttonList;
    private ArrayList<ImageButton> _unusedButton;

    public SequenceModel(SequenceScreen screen) {
        _screen = screen;
    }

    public void setButtons() {
        ImageButton sb1 = (ImageButton) _screen.findViewById(R.id.seqImg1);
        sb1.setImageResource(R.mipmap.blackstar);

        ImageButton sb2 = (ImageButton) _screen.findViewById(R.id.seqImg2);
        sb2.setImageResource(R.mipmap.purplediamond);

        ImageButton sb3 = (ImageButton) _screen.findViewById(R.id.seqImg3);
        sb3.setImageResource(R.mipmap.orangecircle);

        ImageButton sb4 = (ImageButton) _screen.findViewById(R.id.seqImg4);
        sb4.setImageResource(R.mipmap.greensqr);
        sb4.setVisibility(View.INVISIBLE);

        ImageButton sb5 = (ImageButton) _screen.findViewById(R.id.seqImg5);
        sb5.setImageResource(R.mipmap.bluetriangle);
        sb5.setVisibility(View.INVISIBLE);

        ImageButton sb6 = (ImageButton) _screen.findViewById(R.id.seqImg6);
        sb6.setImageResource(R.mipmap.yellowhexagon);
        sb6.setVisibility(View.INVISIBLE);

        _unusedButton = new ArrayList<>(Arrays.asList(sb4,sb5,sb6));

        _buttonList = new ArrayList<>(Arrays.asList(sb1,sb2,sb3));
    }


    public List<ImageButton> getActiveButtons() {
        return _buttonList;
    }

    public ArrayList<ImageButton> getUnusedButtons() {
        return _unusedButton;
    }
}
