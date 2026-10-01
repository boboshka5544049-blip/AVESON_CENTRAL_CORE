package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int DARK = Color.rgb(8, 8, 18);
    private static final int CARD = Color.rgb(16, 14, 32);
    private static final int CARD_2 = Color.rgb(22, 18, 42);

    private static final int PURPLE = Color.rgb(185, 100, 255);
    private static final int BLUE = Color.rgb(80, 150, 255);

    private static final int WHITE = Color.WHITE;
    private static final int GRAY = Color.rgb(165, 165, 185);
    private static final int GREEN = Color.rgb(70, 220, 130);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showHome();
    }

    // ============================================================
    // HOME
    // ============================================================

    private void showHome() {

        LinearLayout layout = createRoot();

        addMenuButton(
                layout,
                "☰  MENU",
                v -> showMenu()
        );

        layout.addView(
                createTitle("AVESON CENTRAL")
        );

        layout.addView(
                createSubtitle(
                        "Global Management & Control Platform"
                )
        );

        layout.addView(
                createStatus("●  CENTRAL SYSTEM ONLINE")
        );

        layout.addView(
                createSectionTitle("CONTROL CENTER")
        );

        addRoom(
                layout,
                "AVESON ARTIST CONTROL",
                "Artist management & submissions",
                v -> showRoom(
                        "AVESON ARTIST CONTROL",
                        "Artist management & submissions"
                )
        );

        addRoom(
                layout,
                "AVESON MUSIC CONTROL",
                "Music platform management",
                v -> showRoom(
                        "AVESON MUSIC CONTROL",
                        "Music platform management"
                )
        );

        addRoom(
                layout,
                "AVESON DISTRIBUTION CONTROL",
                "Global music distribution",
                v -> showRoom(
                        "AVESON DISTRIBUTION CONTROL",
                        "Global music distribution"
                )
        );

        addRoom(
                layout,
                "AVESON STUDIO CONTROL",
                "Studio management",
                v -> showRoom(
                        "AVESON STUDIO CONTROL",
                        "Studio management"
                )
        );

        addRoom(
                layout,
                "AVESON FILMS CONTROL",
                "Films & visual content management",
                v -> showRoom(
                        "AVESON FILMS CONTROL",
                        "Films & visual content management"
                )
        );

        addRoom(
                layout,
                "AVESON MAGAZINE CONTROL",
                "Magazine & editorial management",
                v -> showRoom(
                        "AVESON MAGAZINE CONTROL",
                        "Magazine & editorial management"
                )
        );

        addRoom(
                layout,
                "AVESON DAW CONTROL",
                "AVESON DAW management",
                v -> showRoom(
                        "AVESON DAW CONTROL",
                        "AVESON DAW management"
                )
        );

        addRoom(
                layout,
                "AVESON DJ CONTROL",
                "AVESON DJ management",
                v -> showRoom(
                        "AVESON DJ CONTROL",
                        "AVESON DJ management"
                )
        );

        addRoom(
                layout,
                "AVESON MUSIC PLAYER CONTROL",
                "Music Player management",
                v -> showRoom(
                        "AVESON MUSIC PLAYER CONTROL",
                        "Music Player management"
                )
        );

        TextView footer = new TextView(this);

        footer.setText(
                "\nAVESON CENTRAL CORE\n" +
                "Global Music Ecosystem Control\n\n" +
                "CORE VERSION 1.0"
        );

        footer.setTextColor(GRAY);
        footer.setTextSize(12);
        footer.setGravity(Gravity.CENTER);

        footer.setPadding(
                dp(10),
                dp(25),
                dp(10),
                dp(40)
        );

        layout.addView(footer);

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // MENU
    // ============================================================

    private void showMenu() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showHome()
        );

        layout.addView(
                createTitle("☰ MENU")
        );

        layout.addView(
                createSubtitle(
                        "AVESON CENTRAL CORE"
                )
        );

        addMenuItem(
                layout,
                "Analytics",
                v -> showMessage("Analytics")
        );

        addMenuItem(
                layout,
                "Royalty Control",
                v -> showMessage("Royalty Control")
        );

        addMenuItem(
                layout,
                "Security",
                v -> showSecurity()
        );

        addMenuItem(
                layout,
                "Parameters",
                v -> showParameters()
        );

        addMenuItem(
                layout,
                "System Status",
                v -> showSystemStatus()
        );

        addMenuItem(
                layout,
                "AVESON Central Info",
                v -> showCentralInfo()
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // PARAMETERS
    // ============================================================

    private void showParameters() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showMenu()
        );

        layout.addView(
                createTitle("PARAMETERS")
        );

        addActionButton(
                layout,
                "Languages",
                v -> showLanguages()
        );

        addActionButton(
                layout,
                "Appearance",
                v -> showMessage("Appearance")
        );

        addActionButton(
                layout,
                "Notifications",
                v -> showMessage("Notifications")
        );

        addActionButton(
                layout,
                "Music Parameters",
                v -> showMessage("Music Parameters")
        );

        addActionButton(
                layout,
                "Distribution Parameters",
                v -> showDistributionParameters()
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // LANGUAGES
    // ============================================================

    private void showLanguages() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showParameters()
        );

        layout.addView(
                createTitle("LANGUAGES")
        );

        String[] languages = {
                "English",
                "O'zbek",
                "Русский",
                "Español",
                "Français",
                "Deutsch",
                "Türkçe",
                "العربية",
                "中文",
                "한국어"
        };

        for (String language : languages) {

            addActionButton(
                    layout,
                    language,
                    v -> showMessage(language)
            );
        }

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // DISTRIBUTION PARAMETERS
    // ============================================================

    private void showDistributionParameters() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showParameters()
        );

        layout.addView(
                createTitle("DISTRIBUTION PARAMETERS")
        );

        addActionButton(
                layout,
                "Distribution Standards",
                v -> showMessage("Distribution Standards")
        );

        addActionButton(
                layout,
                "Platform Settings",
                v -> showMessage("Platform Settings")
        );

        addActionButton(
                layout,
                "Release Delivery Settings",
                v -> showMessage("Release Delivery Settings")
        );

        addActionButton(
                layout,
                "Territory Settings",
                v -> showMessage("Territory Settings")
        );

        addActionButton(
                layout,
                "Distribution Formats",
                v -> showMessage("Distribution Formats")
        );

        addActionButton(
                layout,
                "Delivery Rules",
                v -> showMessage("Delivery Rules")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // SECURITY
    // ============================================================

    private void showSecurity() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showMenu()
        );

        layout.addView(
                createTitle("SECURITY")
        );

        addActionButton(
                layout,
                "Access Control",
                v -> showMessage("Access Control")
        );

        addActionButton(
                layout,
                "Permission Management",
                v -> showMessage("Permission Management")
        );

        addActionButton(
                layout,
                "Authorized Devices",
                v -> showMessage("Authorized Devices")
        );

        addActionButton(
                layout,
                "Login Activity",
                v -> showMessage("Login Activity")
        );

        addActionButton(
                layout,
                "Security Protection",
                v -> showMessage("Security Protection")
        );

        addActionButton(
                layout,
                "Security Events",
                v -> showMessage("Security Events")
        );

        addActionButton(
                layout,
                "Password & Authentication",
                v -> showMessage("Password & Authentication")
        );

        addActionButton(
                layout,
                "Network & API Security",
                v -> showMessage("Network & API Security")
        );

        addActionButton(
                layout,
                "Security Logs",
                v -> showMessage("Security Logs")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // SYSTEM STATUS
    // ============================================================

    private void showSystemStatus() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showMenu()
        );

        layout.addView(
                createTitle("SYSTEM STATUS")
        );

        addStatusRow(
                layout,
                "Central System",
                "ONLINE"
        );

        addStatusRow(
                layout,
                "Database",
                "READY"
        );

        addStatusRow(
                layout,
                "Backend",
                "READY"
        );

        addStatusRow(
                layout,
                "API",
                "READY"
        );

        addStatusRow(
                layout,
                "HTTPS / SSL",
                "ACTIVE"
        );

        addStatusRow(
                layout,
                "Services",
                "ONLINE"
        );

        addStatusRow(
                layout,
                "Storage",
                "READY"
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // CENTRAL INFO
    // ============================================================

    private void showCentralInfo() {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showMenu()
        );

        layout.addView(
                createTitle("AVESON CENTRAL CORE")
        );

        layout.addView(
                createSubtitle(
                        "Global Music Ecosystem Control"
                )
        );

        LinearLayout card = createCard();

        addInfo(
                card,
                "SYSTEM",
                "AVESON CENTRAL CORE"
        );

        addInfo(
                card,
                "ROLE",
                "Global Management & Control"
        );

        addInfo(
                card,
                "PLATFORM",
                "AVESON Ecosystem"
        );

        addInfo(
                card,
                "APPLICATION ID",
                "com.aveson.central.core"
        );

        addInfo(
                card,
                "STATUS",
                "ONLINE"
        );

        layout.addView(card);

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // CONTROL ROOM
    // ============================================================

    private void showRoom(
            String roomName,
            String description
    ) {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showHome()
        );

        layout.addView(
                createTitle(roomName)
        );

        layout.addView(
                createSubtitle(description)
        );

        layout.addView(
                createStatus(
                        "●  CONTROL ROOM READY"
                )
        );

        LinearLayout card = createCard();

        addInfo(
                card,
                "ROOM",
                roomName
        );

        addInfo(
                card,
                "SYSTEM",
                "AVESON CENTRAL CORE"
        );

        addInfo(
                card,
                "STATUS",
                "READY"
        );

        layout.addView(card);

        addActionButton(
                layout,
                "CONTROL DASHBOARD",
                v -> showMessage(
                        roomName + " Dashboard"
                )
        );

        addActionButton(
                layout,
                "SETTINGS",
                v -> showMessage(
                        roomName + " Settings"
                )
        );

        addActionButton(
                layout,
                "SYSTEM STATUS",
                v -> showMessage(
                        roomName + " System Status"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // MESSAGE
    // ============================================================

    private void showMessage(String titleText) {

        LinearLayout layout = createRoot();

        addBackButton(
                layout,
                v -> showHome()
        );

        layout.addView(
                createTitle(titleText)
        );

        layout.addView(
                createSubtitle(
                        "AVESON CENTRAL CORE"
                )
        );

        layout.addView(
                createStatus("●  READY")
        );

        LinearLayout card = createCard();

        addInfo(
                card,
                "SECTION",
                titleText
        );

        addInfo(
                card,
                "STATUS",
                "READY"
        );

        addInfo(
                card,
                "SYSTEM",
                "AVESON CENTRAL CORE"
        );

        layout.addView(card);

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // ROOT
    // ============================================================

    private LinearLayout createRoot() {

        LinearLayout layout =
                new LinearLayout(this);

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

    private ScrollView createScroll(
            LinearLayout content
    ) {

        ScrollView scroll =
                new ScrollView(this);

        scroll.setFillViewport(true);
        scroll.setBackgroundColor(DARK);

        scroll.addView(content);

        return scroll;
    }

    private void setScreen(View view) {

        setContentView(view);
    }

    // ============================================================
    // TEXT
    // ============================================================

    private TextView createTitle(
            String text
    ) {

        TextView title =
                new TextView(this);

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

    private TextView createSubtitle(
            String text
    ) {

        TextView subtitle =
                new TextView(this);

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

    private TextView createStatus(
            String text
    ) {

        TextView status =
                new TextView(this);

        status.setText(text);
        status.setTextColor(GREEN);
        status.setTextSize(13);
        status.setGravity(Gravity.CENTER);

        status.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(18)
        );

        return status;
    }

    private TextView createSectionTitle(
            String text
    ) {

        TextView title =
                new TextView(this);

        title.setText(text);
        title.setTextColor(PURPLE);
        title.setTextSize(13);

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setPadding(
                dp(6),
                dp(14),
                dp(6),
                dp(8)
        );

        return title;
    }

    // ============================================================
    // ROOM
    // ============================================================

    private void addRoom(
            LinearLayout layout,
            String titleText,
            String subtitleText,
            View.OnClickListener listener
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(16),
                dp(14),
                dp(16),
                dp(14)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(CARD_2);
        bg.setCornerRadius(
                dp(16)
        );

        bg.setStroke(
                dp(1),
                Color.rgb(70, 55, 120)
        );

        card.setBackground(bg);

        TextView title =
                new TextView(this);

        title.setText(titleText);
        title.setTextColor(WHITE);
        title.setTextSize(16);

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        TextView subtitle =
                new TextView(this);

        subtitle.setText(subtitleText);
        subtitle.setTextColor(GRAY);
        subtitle.setTextSize(12);

        subtitle.setPadding(
                0,
                dp(5),
                0,
                0
        );

        card.addView(title);
        card.addView(subtitle);

        card.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(86)
                );

        params.setMargins(
                0,
                dp(6),
                0,
                dp(6)
        );

        layout.addView(
                card,
                params
        );
    }

    // ============================================================
    // BUTTONS
    // ============================================================

    private void addMenuButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                createButton(
                        text,
                        PURPLE
                );

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(58)
                );

        params.setMargins(
                0,
                0,
                0,
                dp(14)
        );

        layout.addView(
                button,
                params
        );
    }

    private void addBackButton(
            LinearLayout layout,
            View.OnClickListener listener
    ) {

        Button button =
                createButton(
                        "← BACK",
                        BLUE
                );

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(52)
                );

        params.setMargins(
                0,
                0,
                0,
                dp(10)
        );

        layout.addView(
                button,
                params
        );
    }

    private void addMenuItem(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        addActionButton(
                layout,
                text,
                listener
        );
    }

    private void addActionButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                createButton(
                        text,
                        BLUE
                );

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(54)
                );

        params.setMargins(
                0,
                dp(6),
                0,
                dp(6)
        );

        layout.addView(
                button,
                params
        );
    }

    private Button createButton(
            String text,
            int strokeColor
    ) {

        Button button =
                new Button(this);

        button.setText(text);
        button.setTextColor(WHITE);
        button.setTextSize(14);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(CARD);
        bg.setCornerRadius(
                dp(14)
        );

        bg.setStroke(
                dp(1),
                strokeColor
        );

        button.setBackground(bg);

        button.setPadding(
                dp(12),
                dp(4),
                dp(12),
                dp(4)
        );

        return button;
    }

    // ============================================================
    // CARD
    // ============================================================

    private LinearLayout createCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(16),
                dp(16),
                dp(16),
                dp(16)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(CARD);
        bg.setCornerRadius(
                dp(16)
        );

        bg.setStroke(
                dp(1),
                Color.rgb(55, 45, 90)
        );

        card.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        params.setMargins(
                0,
                dp(10),
                0,
                dp(14)
        );

        card.setLayoutParams(params);

        return card;
    }

    private void addInfo(
            LinearLayout card,
            String label,
            String value
    ) {

        TextView text =
                new TextView(this);

        text.setText(
                label + "\n" + value
        );

        text.setTextColor(GRAY);
        text.setTextSize(13);

        text.setPadding(
                0,
                dp(7),
                0,
                dp(7)
        );

        card.addView(text);
    }

    private void addStatusRow(
            LinearLayout layout,
            String name,
            String status
    ) {

        LinearLayout card =
                createCard();

        TextView text =
                new TextView(this);

        text.setText(
                name + "\n● " + status
        );

        text.setTextColor(WHITE);
        text.setTextSize(14);

        card.addView(text);

        layout.addView(card);
    }

    // ============================================================
    // BACK
    // ============================================================

    @Override
    public void onBackPressed() {

        showHome();
    }

    // ============================================================
    // DP
    // ============================================================

    private int dp(int value) {

        return (int) (
                value *
                getResources()
                        .getDisplayMetrics()
                        .density
        );
    }
                    }
