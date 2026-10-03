package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
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

    private final int PURPLE = Color.rgb(185, 100, 255);
    private final int BLUE = Color.rgb(80, 150, 255);
    private final int DARK = Color.rgb(8, 8, 18);
    private final int CARD = Color.rgb(15, 15, 30);
    private final int TEXT = Color.WHITE;
    private final int TEXT_GRAY = Color.rgb(165, 165, 185);
    private final int GREEN = Color.rgb(70, 220, 130);
    private final int YELLOW = Color.rgb(240, 190, 70);

    public CentralMenu(Activity activity, Runnable homeAction) {
        this.activity = activity;
        this.homeAction = homeAction;
    }

    public void show() {
        showGlobalMenu();
    }

    private LinearLayout createRoot() {

        LinearLayout layout =
                new LinearLayout(activity);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                dp(20),
                dp(38),
                dp(20),
                dp(56)
        );

        layout.setBackgroundColor(DARK);

        return layout;
    }

    private ScrollView createScroll() {

        ScrollView scroll =
                new ScrollView(activity);

        scroll.setFillViewport(true);

        return scroll;
    }

    private void setScreen(LinearLayout layout) {

        ScrollView scroll =
                createScroll();

        scroll.addView(
                layout,
                new ScrollView.LayoutParams(
                        ScrollView.LayoutParams.MATCH_PARENT,
                        ScrollView.LayoutParams.WRAP_CONTENT
                )
        );

        activity.setContentView(scroll);
    }

    private TextView createTitle(String text) {

        TextView title =
                new TextView(activity);

        title.setText(text);
        title.setTextColor(TEXT);
        title.setTextSize(25);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, 1);

        title.setPadding(
                0,
                dp(10),
                0,
                dp(10)
        );

        return title;
    }

    private TextView createSubtitle(String text) {

        TextView subtitle =
                new TextView(activity);

        subtitle.setText(text);
        subtitle.setTextColor(TEXT_GRAY);
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);

        subtitle.setPadding(
                0,
                0,
                0,
                dp(20)
        );

        return subtitle;
    }

    private Button createButton(
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                new Button(activity);

        button.setText(text);
        button.setTextColor(TEXT);
        button.setTextSize(15);
        button.setAllCaps(false);

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);
        background.setCornerRadius(
                dp(16)
        );
        background.setStroke(
                dp(1),
                PURPLE
        );

        button.setBackground(background);

        button.setPadding(
                dp(16),
                dp(8),
                dp(16),
                dp(8)
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(58)
                );

        params.setMargins(
                0,
                dp(6),
                0,
                dp(6)
        );

        button.setLayoutParams(params);

        button.setOnClickListener(listener);

        return button;
    }

    private void addButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        layout.addView(
                createButton(
                        text,
                        listener
                )
        );
    }

    private LinearLayout createCard(String title) {

        LinearLayout card =
                new LinearLayout(activity);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(16),
                dp(16),
                dp(16),
                dp(16)
        );

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);
        background.setCornerRadius(
                dp(18)
        );
        background.setStroke(
                dp(1),
                BLUE
        );

        card.setBackground(background);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                dp(8),
                0,
                dp(8)
        );

        card.setLayoutParams(params);

        TextView titleView =
                new TextView(activity);

        titleView.setText(title);
        titleView.setTextColor(PURPLE);
        titleView.setTextSize(17);
        titleView.setTypeface(null, 1);

        titleView.setPadding(
                0,
                0,
                0,
                dp(10)
        );

        card.addView(titleView);

        return card;
    }

    private void addInfo(
            LinearLayout layout,
            String name,
            String value
    ) {

        TextView info =
                new TextView(activity);

        info.setText(
                name + "\n" + value
        );

        info.setTextColor(TEXT_GRAY);
        info.setTextSize(14);

        info.setPadding(
                0,
                dp(6),
                0,
                dp(6)
        );

        layout.addView(info);
    }

    private void addSectionButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        addButton(
                layout,
                text,
                listener
        );
    }

    private int dp(int value) {

        return (int)
                (value *
                        activity
                                .getResources()
                                .getDisplayMetrics()
                                .density
                        + 0.5f);
    }


    // =========================================================
    // AVESON CENTRAL MENU
    // =========================================================

    private void showGlobalMenu() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "AVESON CENTRAL MENU"
                )
        );

        layout.addView(
                createSubtitle(
                        "Global Central Management"
                )
        );

        addButton(
                layout,
                "📊 Analytics",
                v -> showAnalytics()
        );

        addButton(
                layout,
                "💰 Royalty Control",
                v -> showRoyaltyControl()
        );

        addButton(
                layout,
                "🔐 Security",
                v -> showSecurity()
        );

        addButton(
                layout,
                "⚙️ Parameters",
                v -> showParameters()
        );

        addButton(
                layout,
                "🛡️ System Status",
                v -> showSystemStatus()
        );

        addButton(
                layout,
                "ℹ️ AVESON Central Info",
                v -> showAvesonCentralInfo()
        );

        addButton(
                layout,
                "← BACK",
                v -> homeAction.run()
        );

        setScreen(layout);
    }
