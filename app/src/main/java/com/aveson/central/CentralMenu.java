package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CentralMenu {

    private final Activity activity;

    public CentralMenu(Activity activity) {
        this.activity = activity;
    }

    public void show() {

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.rgb(8, 8, 18));

        // MENU kodi shu yerda bo‘ladi

    }
}
