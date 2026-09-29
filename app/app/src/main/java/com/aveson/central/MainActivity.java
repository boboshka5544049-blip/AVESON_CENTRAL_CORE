package com.aveson.central;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private final int PURPLE = Color.rgb(185, 100, 255);
    private final int BLUE = Color.rgb(80, 150, 255);
    private final int DARK = Color.rgb(8, 8, 18);
    private final int CARD = Color.rgb(15, 15, 30);
    private final int TEXT = Color.WHITE;
    private final int TEXT_GRAY = Color.rgb(165, 165, 185);
    private final int GREEN = Color.rgb(70, 220, 130);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(DARK);
        getWindow().setNavigationBarColor(DARK);

        showCentralHome();
    }

    @Override
    public void onBackPressed() {
        showCentralHome();
    }

    private LinearLayout createRoot() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(DARK);

        root.setPadding(
                dp(16),
                dp(38),
                dp(16),
                dp(56)
        );

        return root;
    }

    private ScrollView createScroll() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setBackgroundColor(DARK);
        return scroll;
    }

    private void setScreen(View view) {
        setContentView(view);
    }

    private TextView createTitle(String text) {
        TextView title = new TextView(this);

        title.setText(text);
        title.setTextColor(TEXT);
        title.setTextSize(23);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, dp(8), 0, dp(8));

        return title;
    }

    private TextView createSubtitle(String text) {
        TextView subtitle = new TextView(this);

        subtitle.setText(text);
        subtitle.setTextColor(TEXT_GRAY);
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(2), 0, dp(14));

        return subtitle;
    }

    private Button createButton(String text) {
        Button button = new Button(this);

        button.setText(text);
        button.setTextColor(TEXT);
        button.setTextSize(15);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(16));
        background.setStroke(dp(1), PURPLE);

        button.setBackground(background);

        return button;
    }

    private void addButton(
            LinearLayout parent,
            String text,
            View.OnClickListener listener
    ) {
        Button button = createButton(text);

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );

        params.setMargins(
                dp(4),
                dp(6),
                dp(4),
                dp(6)
        );

        parent.addView(button, params);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(this);

        card.setOrientation(LinearLayout.VERTICAL);

        card.setPadding(
                dp(16),
                dp(14),
                dp(16),
                dp(14)
        );

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(18));
        background.setStroke(
                dp(1),
                Color.rgb(70, 55, 95)
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

        return card;
    }

    private void addInfo(
            LinearLayout card,
            String label,
            String value
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        TextView left = new TextView(this);
        left.setText(label);
        left.setTextColor(TEXT_GRAY);
        left.setTextSize(14);

        TextView right = new TextView(this);
        right.setText(value);
        right.setTextColor(TEXT);
        right.setTextSize(14);
        right.setGravity(Gravity.RIGHT);

        row.addView(
                left,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        row.addView(
                right,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        card.addView(row);
    }

    private void addSectionButton(
            LinearLayout parent,
            String title,
            String subtitle,
            View.OnClickListener listener
    ) {
        Button button = createButton(
                title + "\n" + subtitle
        );

        button.setTextSize(15);
        button.setGravity(Gravity.CENTER_VERTICAL);
        button.setPadding(
                dp(18),
                0,
                dp(18),
                0
        );

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(62)
                );

        params.setMargins(
                0,
                dp(5),
                0,
                dp(5)
        );

        parent.addView(button, params);
    }

    private void showCentralHome() {

        LinearLayout root = createRoot();

        Button menu = createButton("☰ MENU");

        LinearLayout.LayoutParams menuParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );

        menuParams.setMargins(
                0,
                0,
                0,
                dp(12)
        );

        root.addView(menu, menuParams);

        root.addView(
                createTitle("AVESON CENTRAL")
        );

        root.addView(
                createSubtitle(
                        "Global Management & Control Platform"
                )
        );

        TextView online = new TextView(this);

        online.setText("● CENTRAL SYSTEM ONLINE");
        online.setTextColor(GREEN);
        online.setTextSize(14);
        online.setGravity(Gravity.CENTER);
        online.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        root.addView(online);

        TextView controlCenter =
                new TextView(this);

        controlCenter.setText("CONTROL CENTER");
        controlCenter.setTextColor(TEXT);
        controlCenter.setTextSize(16);
        controlCenter.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        controlCenter.setGravity(Gravity.CENTER);
        controlCenter.setPadding(
                0,
                dp(18),
                0,
                dp(8)
        );

        root.addView(controlCenter);

        addSectionButton(
                root,
                "🎵 AVESON ARTIST CONTROL",
                "Artist Management",
                v -> showMessage(
                        "AVESON ARTIST CONTROL"
                )
        );

        addSectionButton(
                root,
                "🎼 AVESON MUSIC CONTROL",
                "Music Management",
                v -> showMessage(
                        "AVESON MUSIC CONTROL"
                )
        );

        addSectionButton(
                root,
                "🌍 AVESON DISTRIBUTION CONTROL",
                "Distribution Management",
                v -> showMessage(
                        "AVESON DISTRIBUTION CONTROL"
                )
        );

        addSectionButton(
                root,
                "🎙️ AVESON STUDIO CONTROL",
                "Studio Management",
                v -> showMessage(
                        "AVESON STUDIO CONTROL"
                )
        );

        addSectionButton(
                root,
                "📰 AVESON MAGAZINE CONTROL",
                "Magazine Management",
                v -> showMessage(
                        "AVESON MAGAZINE CONTROL"
                )
        );

        addSectionButton(
                root,
                "🎬 AVESON FILMS",
                "Film Management",
                v -> showMessage(
                        "AVESON FILMS"
                )
        );

        LinearLayout infoCard = createCard();

        TextView infoTitle =
                new TextView(this);

        infoTitle.setText("AVESON CENTRAL");
        infoTitle.setTextColor(TEXT);
        infoTitle.setTextSize(17);
        infoTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        infoTitle.setPadding(
                0,
                0,
                0,
                dp(10)
        );

        infoCard.addView(infoTitle);

        addInfo(
                infoCard,
                "Platform",
                "AVESON"
        );

        addInfo(
                infoCard,
                "System",
                "CENTRAL"
        );

        addInfo(
                infoCard,
                "Management",
                "Unified Control"
        );

        root.addView(infoCard);

        menu.setOnClickListener(
                v -> showMessage("CENTRAL MENU")
        );

        ScrollView scroll = createScroll();
        scroll.addView(root);

        setScreen(scroll);
    }

    private void showMessage(String name) {

        LinearLayout root = createRoot();

        root.addView(
                createTitle(name)
        );

        root.addView(
                createSubtitle(
                        "AVESON CENTRAL"
                )
        );

        LinearLayout card = createCard();

        addInfo(
                card,
                "Section",
                name
        );

        addInfo(
                card,
                "Status",
                "AVAILABLE"
        );

        addInfo(
                card,
                "Data",
                "Not connected"
        );

        root.addView(card);

        addButton(
                root,
                "← BACK",
                v -> showCentralHome()
        );

        ScrollView scroll = createScroll();
        scroll.addView(root);

        setScreen(scroll);
    }

    private int dp(int value) {
        return (int) (
                value *
                getResources()
                        .getDisplayMetrics()
                        .density
        );
    }
}
