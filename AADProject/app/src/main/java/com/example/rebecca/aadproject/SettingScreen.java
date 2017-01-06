package com.example.rebecca.aadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        Button button = (Button)findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Spinner spinner = (Spinner) findViewById(R.id.fontSpinner);
                String message = spinner.getSelectedItem().toString();
                TextView textview = (TextView) findViewById(R.id.font_textview);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.mediumText));

            }});
    }
}
