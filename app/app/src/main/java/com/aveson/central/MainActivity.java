package com.aveson.central;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);

        text.setText("AVESON CENTRAL TEST");
        text.setTextColor(Color.WHITE);
        text.setTextSize(24);
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.rgb(8, 8, 18));

        setContentView(text);
    }
}
