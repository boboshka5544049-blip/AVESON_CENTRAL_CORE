package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CentralMenu {

    private final Activity activity;
    private final Runnable homeAction;

    private static final int DARK =
            Color.rgb(8, 8, 18);

    private static final int CARD =
            Color.rgb(16, 14, 32);

    private static final int PURPLE =
            Color.rgb(185, 100, 255);

    private static final int BLUE =
            Color.rgb(80, 150, 255);

    private static final int WHITE =
            Color.WHITE;

    private static final int GRAY =
            Color.rgb(165, 165, 185);

    public CentralMenu(
            Activity activity,
            Runnable homeAction
    ) {
        this.activity = activity;
        this.homeAction = homeAction;
    }

    // ============================================================
    // MAIN MENU
    // ============================================================

    public void show() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                BLUE,
                v -> goHome()
        );

        layout.addView(
                createTitle("☰ MENU")
        );

        layout.addView(
                createSubtitle("AVESON CENTRAL CORE")
        );

        addMenuItem(layout, "Analytics");
        addMenuItem(layout, "Royalty Control");
        addMenuItem(layout, "Security");
        addMenuItem(layout, "Parameters");
        addMenuItem(layout, "System Status");
        addMenuItem(layout, "AVESON Central Info");

        TextView footer = new TextView(activity);

        footer.setText(
                "\nAVESON CENTRAL CORE\n" +
                "Global Music Ecosystem Control"
        );

        footer.setTextColor(GRAY);
        footer.setTextSize(12);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(
                dp(8),
                dp(22),
                dp(8),
                dp(30)
        );

        layout.addView(footer);

        ScrollView scroll = new ScrollView(activity);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(DARK);
        scroll.setClipToPadding(false);
        scroll.setPadding(0, 0, 0, dp(12));
        scroll.addView(layout);

        activity.setContentView(scroll);
    }

    // ============================================================
    // MENU ITEMS
    // ============================================================

    private void addMenuItem(
            LinearLayout layout,
            String page
    ) {
        addButton(
                layout,
                page,
                BLUE,
                v -> openPage(page)
        );
    }

    // ============================================================
    // PAGE NAVIGATION
    // ============================================================

    private void openPage(String page) {

        CentralMenuContent content =
                new CentralMenuContent(
                        activity,
                        this::show
                );

        content.showPage(page);
    }

    private void goHome() {

        if (homeAction != null) {
            homeAction.run();
        } else {
            activity.finish();
        }
    }

    // ============================================================
    // ROOT
    // ============================================================

    private LinearLayout createRoot() {

        LinearLayout layout =
                new LinearLayout(activity);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(35)
        );

        layout.setBackgroundColor(DARK);

        return layout;
    }

    // ============================================================
    // TEXT
    // ============================================================

    private TextView createTitle(String text) {

        TextView title =
                new TextView(activity);

        title.setText(text);
        title.setTextColor(WHITE);
        title.setTextSize(25);
        title.setGravity(Gravity.CENTER);

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setPadding(
                dp(8),
                dp(20),
                dp(8),
                dp(8)
        );

        return title;
    }

    private TextView createSubtitle(String text) {

        TextView subtitle =
                new TextView(activity);

        subtitle.setText(text);
        subtitle.setTextColor(GRAY);
        subtitle.setTextSize(13);
        subtitle.setGravity(Gravity.CENTER);

        subtitle.setPadding(
                dp(8),
                dp(2),
                dp(8),
                dp(18)
        );

        return subtitle;
    }

    // ============================================================
    // BUTTONS
    // ============================================================

    private void addButton(
            LinearLayout layout,
            String text,
            int strokeColor,
            View.OnClickListener listener
    ) {

        Button button = new Button(activity);

        button.setText(text);
        button.setTextColor(WHITE);
        button.setTextSize(14);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);
        background.setCornerRadius(dp(14));

        background.setStroke(
                dp(1),
                strokeColor
        );

        button.setBackground(background);

        button.setPadding(
                dp(12),
                dp(4),
                dp(12),
                dp(4)
        );

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(54)
                );

        params.setMargins(
                0,
                dp(6),
                0,
                dp(6)
        );

        layout.addView(button, params);
    }

    // ============================================================
    // DP
    // ============================================================

    private int dp(int value) {

        return (int) (
                value *
                activity.getResources()
                        .getDisplayMetrics()
                        .density
        );
    }
}
