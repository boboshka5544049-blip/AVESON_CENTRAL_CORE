package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int DARK = Color.rgb(8, 8, 18);
    private static final int CARD = Color.rgb(15, 15, 30);
    private static final int CARD_2 = Color.rgb(20, 17, 38);

    private static final int PURPLE = Color.rgb(185, 100, 255);
    private static final int BLUE = Color.rgb(80, 150, 255);

    private static final int TEXT = Color.WHITE;
    private static final int TEXT_GRAY = Color.rgb(165, 165, 185);
    private static final int GREEN = Color.rgb(70, 220, 130);

    private LinearLayout mainContainer;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TextView test = new TextView(this);
    test.setText("AVESON CENTRAL TEST");
    test.setTextColor(Color.WHITE);
    test.setTextSize(25);
    test.setGravity(Gravity.CENTER);
    test.setBackgroundColor(Color.rgb(8, 8, 18));

    setContentView(test);
}

        showCentralHome();
    }

    // ============================================================
    // CENTRAL HOME
    // ============================================================

    private void showCentralHome() {

        LinearLayout layout = createRoot();

        // MENU
        addMenuButton(
                layout,
                "☰  MENU",
                v -> showGlobalMenu()
        );

        // TITLE
        layout.addView(
                createTitle("AVESON CENTRAL")
        );

        layout.addView(
                createSubtitle(
                        "Global Management & Control Platform"
                )
        );

        // STATUS
        TextView status = createStatus("●  SYSTEM ONLINE");
        layout.addView(status);

        // SECTION
        layout.addView(
                createSectionTitle("CONTROL ROOMS")
        );

        // ========================================================
        // CONTROL ROOMS
        // ========================================================

        addRoomButton(
                layout,
                "AVESON ARTIST CONTROL",
                "Artist management & submissions",
                v -> showControlRoom(
                        "AVESON ARTIST CONTROL",
                        "Artist management & submissions"
                )
        );

        addRoomButton(
                layout,
                "AVESON MUSIC CONTROL",
                "Music platform management",
                v -> showControlRoom(
                        "AVESON MUSIC CONTROL",
                        "Music platform management"
                )
        );

        addRoomButton(
                layout,
                "AVESON DISTRIBUTION CONTROL",
                "Global music distribution",
                v -> showControlRoom(
                        "AVESON DISTRIBUTION CONTROL",
                        "Global music distribution"
                )
        );

        addRoomButton(
                layout,
                "AVESON STUDIO CONTROL",
                "Studio management",
                v -> showControlRoom(
                        "AVESON STUDIO CONTROL",
                        "Studio management"
                )
        );

        addRoomButton(
                layout,
                "AVESON FILMS CONTROL",
                "Films & visual content management",
                v -> showControlRoom(
                        "AVESON FILMS CONTROL",
                        "Films & visual content management"
                )
        );

        addRoomButton(
                layout,
                "AVESON MAGAZINE CONTROL",
                "Magazine & editorial management",
                v -> showControlRoom(
                        "AVESON MAGAZINE CONTROL",
                        "Magazine & editorial management"
                )
        );

        addRoomButton(
                layout,
                "AVESON DAW CONTROL",
                "AVESON DAW management",
                v -> showControlRoom(
                        "AVESON DAW CONTROL",
                        "AVESON DAW management"
                )
        );

        addRoomButton(
                layout,
                "AVESON DJ CONTROL",
                "AVESON DJ management",
                v -> showControlRoom(
                        "AVESON DJ CONTROL",
                        "AVESON DJ management"
                )
        );

        addRoomButton(
                layout,
                "AVESON MUSIC PLAYER CONTROL",
                "Music Player management",
                v -> showControlRoom(
                        "AVESON MUSIC PLAYER CONTROL",
                        "Music Player management"
                )
        );

        // FOOTER
        TextView footer = new TextView(this);
        footer.setText(
                "\nAVESON CENTRAL\n" +
                "Global Music Ecosystem Control\n\n"
        );
        footer.setTextColor(TEXT_GRAY);
        footer.setTextSize(12);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(
                dp(16),
                dp(20),
                dp(16),
                dp(30)
        );

        layout.addView(footer);

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // GLOBAL MENU
    // ============================================================

    private void showGlobalMenu() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showCentralHome()
        );

        layout.addView(
                createTitle("☰ MENU")
        );

        layout.addView(
                createSubtitle(
                        "AVESON CENTRAL MANAGEMENT"
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
    // CONTROL ROOM SCREEN
    // ============================================================

    private void showControlRoom(
            String roomName,
            String description
    ) {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showCentralHome()
        );

        layout.addView(
                createTitle(roomName)
        );

        layout.addView(
                createSubtitle(description)
        );

        TextView status = createStatus(
                "●  CONTROL ROOM READY"
        );

        layout.addView(status);

        LinearLayout card = createCard();

        addInfo(
                card,
                "STATUS",
                "Ready for configuration"
        );

        addInfo(
                card,
                "SYSTEM",
                "AVESON CENTRAL"
        );

        addInfo(
                card,
                "ROOM",
                roomName
        );

        layout.addView(card);

        addSectionButton(
                layout,
                "CONTROL DASHBOARD",
                v -> showMessage(
                        roomName + " Dashboard"
                )
        );

        addSectionButton(
                layout,
                "SETTINGS",
                v -> showMessage(
                        roomName + " Settings"
                )
        );

        addSectionButton(
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
    // PARAMETERS
    // ============================================================

    private void showParameters() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        layout.addView(
                createTitle("PARAMETERS")
        );

        addButton(
                layout,
                "Languages",
                v -> showLanguages()
        );

        addButton(
                layout,
                "Appearance",
                v -> showAppearance()
        );

        addButton(
                layout,
                "Notifications",
                v -> showNotifications()
        );

        addButton(
                layout,
                "Music Parameters",
                v -> showMusicParameters()
        );

        addButton(
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

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        layout.addView(
                createTitle("LANGUAGES")
        );

        addSectionButton(
                layout,
                "English",
                v -> showMessage("English")
        );

        addSectionButton(
                layout,
                "Uzbek",
                v -> showMessage("Uzbek")
        );

        addSectionButton(
                layout,
                "Russian",
                v -> showMessage("Russian")
        );

        addSectionButton(
                layout,
                "Turkish",
                v -> showMessage("Turkish")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // APPEARANCE
    // ============================================================

    private void showAppearance() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        layout.addView(
                createTitle("APPEARANCE")
        );

        addSectionButton(
                layout,
                "Dark Mode",
                v -> showMessage("Dark Mode")
        );

        addSectionButton(
                layout,
                "Neon Interface",
                v -> showMessage("Neon Interface")
        );

        addSectionButton(
                layout,
                "Interface Settings",
                v -> showMessage("Interface Settings")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // NOTIFICATIONS
    // ============================================================

    private void showNotifications() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        layout.addView(
                createTitle("NOTIFICATIONS")
        );

        addSectionButton(
                layout,
                "System Notifications",
                v -> showMessage("System Notifications")
        );

        addSectionButton(
                layout,
                "Security Notifications",
                v -> showMessage("Security Notifications")
        );

        addSectionButton(
                layout,
                "Control Room Notifications",
                v -> showMessage(
                        "Control Room Notifications"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // MUSIC PARAMETERS
    // ============================================================

    private void showMusicParameters() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        layout.addView(
                createTitle("MUSIC PARAMETERS")
        );

        addSectionButton(
                layout,
                "Audio Standards",
                v -> showMessage("Audio Standards")
        );

        addSectionButton(
                layout,
                "Cover Standards",
                v -> showMessage("Cover Standards")
        );

        addSectionButton(
                layout,
                "Release Standards",
                v -> showMessage("Release Standards")
        );

        addSectionButton(
                layout,
                "Metadata Standards",
                v -> showMessage("Metadata Standards")
        );

        addSectionButton(
                layout,
                "Music File Formats",
                v -> showMessage("Music File Formats")
        );

        addSectionButton(
                layout,
                "Music Quality",
                v -> showMessage("Music Quality")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // DISTRIBUTION PARAMETERS
    // ============================================================

    private void showDistributionParameters() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        layout.addView(
                createTitle("DISTRIBUTION PARAMETERS")
        );

        addSectionButton(
                layout,
                "Distribution Standards",
                v -> showMessage(
                        "Distribution Standards"
                )
        );

        addSectionButton(
                layout,
                "Platform Settings",
                v -> showMessage(
                        "Platform Settings"
                )
        );

        addSectionButton(
                layout,
                "Release Delivery Settings",
                v -> showReleaseDeliverySettings()
        );

        addSectionButton(
                layout,
                "Territory Settings",
                v -> showTerritorySettings()
        );

        addSectionButton(
                layout,
                "Distribution Formats",
                v -> showDistributionFormats()
        );

        addSectionButton(
                layout,
                "Delivery Rules",
                v -> showDeliveryRules()
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // RELEASE DELIVERY SETTINGS
    // ============================================================

    private void showReleaseDeliverySettings() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        layout.addView(
                createTitle("RELEASE DELIVERY SETTINGS")
        );

        addSectionButton(
                layout,
                "Automatic Delivery",
                v -> showMessage(
                        "Automatic Delivery"
                )
        );

        addSectionButton(
                layout,
                "Manual Delivery",
                v -> showMessage(
                        "Manual Delivery"
                )
        );

        addSectionButton(
                layout,
                "Delivery Schedule",
                v -> showMessage(
                        "Delivery Schedule"
                )
        );

        addSectionButton(
                layout,
                "Delivery Priority",
                v -> showMessage(
                        "Delivery Priority"
                )
        );

        addSectionButton(
                layout,
                "Delivery Retry",
                v -> showMessage(
                        "Delivery Retry"
                )
        );

        addSectionButton(
                layout,
                "Delivery Status",
                v -> showMessage(
                        "Delivery Status"
                )
        );

        addSectionButton(
                layout,
                "Delivery Confirmation",
                v -> showMessage(
                        "Delivery Confirmation"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // TERRITORY SETTINGS
    // ============================================================

    private void showTerritorySettings() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        layout.addView(
                createTitle("TERRITORY SETTINGS")
        );

        addSectionButton(
                layout,
                "Worldwide",
                v -> showMessage("Worldwide")
        );

        addSectionButton(
                layout,
                "Country Settings",
                v -> showMessage(
                        "Country Settings"
                )
        );

        addSectionButton(
                layout,
                "Regional Settings",
                v -> showMessage(
                        "Regional Settings"
                )
        );

        addSectionButton(
                layout,
                "Restricted Territories",
                v -> showMessage(
                        "Restricted Territories"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // DISTRIBUTION FORMATS
    // ============================================================

    private void showDistributionFormats() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        layout.addView(
                createTitle("DISTRIBUTION FORMATS")
        );

        addSectionButton(
                layout,
                "Audio",
                v -> showMessage("Audio")
        );

        addSectionButton(
                layout,
                "Video",
                v -> showMessage("Video")
        );

        addSectionButton(
                layout,
                "Music Video",
                v -> showMessage("Music Video")
        );

        addSectionButton(
                layout,
                "Short Clips",
                v -> showMessage("Short Clips")
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // DELIVERY RULES
    // ============================================================

    private void showDeliveryRules() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        layout.addView(
                createTitle("DELIVERY RULES")
        );

        addSectionButton(
                layout,
                "Delivery Validation",
                v -> showMessage(
                        "Delivery Validation"
                )
        );

        addSectionButton(
                layout,
                "Delivery Schedule",
                v -> showMessage(
                        "Delivery Schedule"
                )
        );

        addSectionButton(
                layout,
                "Delivery Priority",
                v -> showMessage(
                        "Delivery Priority"
                )
        );

        addSectionButton(
                layout,
                "Delivery Retry",
                v -> showMessage(
                        "Delivery Retry"
                )
        );

        addSectionButton(
                layout,
                "Delivery Status",
                v -> showMessage(
                        "Delivery Status"
                )
        );

        addSectionButton(
                layout,
                "Delivery Confirmation",
                v -> showMessage(
                        "Delivery Confirmation"
                )
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

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        layout.addView(
                createTitle("SECURITY")
        );

        addSectionButton(
                layout,
                "Access Control",
                v -> showAccessControl()
        );

        addSectionButton(
                layout,
                "Permission Management",
                v -> showMessage(
                        "Permission Management"
                )
        );

        addSectionButton(
                layout,
                "Authorized Devices",
                v -> showMessage(
                        "Authorized Devices"
                )
        );

        addSectionButton(
                layout,
                "Login Activity",
                v -> showMessage(
                        "Login Activity"
                )
        );

        addSectionButton(
                layout,
                "Security Protection",
                v -> showMessage(
                        "Security Protection"
                )
        );

        addSectionButton(
                layout,
                "Security Events",
                v -> showMessage(
                        "Security Events"
                )
        );

        addSectionButton(
                layout,
                "Password & Authentication",
                v -> showMessage(
                        "Password & Authentication"
                )
        );

        addSectionButton(
                layout,
                "Network & API Security",
                v -> showMessage(
                        "Network & API Security"
                )
        );

        addSectionButton(
                layout,
                "Security Logs",
                v -> showMessage(
                        "Security Logs"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // ACCESS CONTROL
    // ============================================================

    private void showAccessControl() {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        layout.addView(
                createTitle("ACCESS CONTROL")
        );

        addSectionButton(
                layout,
                "Administrator",
                v -> showMessage("Administrator")
        );

        addSectionButton(
                layout,
                "Manager",
                v -> showMessage("Manager")
        );

        addSectionButton(
                layout,
                "Reviewer",
                v -> showMessage("Reviewer")
        );

        addSectionButton(
                layout,
                "Operator",
                v -> showMessage("Operator")
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

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
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

        addStatusRow(
                layout,
                "System Monitoring",
                "ACTIVE"
        );

        addStatusRow(
                layout,
                "System Operations",
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

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        layout.addView(
                createTitle("AVESON CENTRAL")
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
                "AVESON CENTRAL"
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
                "STATUS",
                "ONLINE"
        );

        layout.addView(card);

        addSectionButton(
                layout,
                "System Architecture",
                v -> showMessage(
                        "System Architecture"
                )
        );

        addSectionButton(
                layout,
                "Platform Scope",
                v -> showMessage(
                        "Platform Scope"
                )
        );

        addSectionButton(
                layout,
                "Security & Control",
                v -> showMessage(
                        "Security & Control"
                )
        );

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // GENERIC MESSAGE SCREEN
    // ============================================================

    private void showMessage(String name) {

        LinearLayout layout = createRoot();

        addButton(
                layout,
                "← BACK",
                v -> showCentralHome()
        );

        layout.addView(
                createTitle(name)
        );

        layout.addView(
                createSubtitle(
                        "AVESON CENTRAL"
                )
        );

        TextView ready = createStatus(
                "●  READY"
        );

        layout.addView(ready);

        LinearLayout card = createCard();

        addInfo(
                card,
                "SECTION",
                name
        );

        addInfo(
                card,
                "STATUS",
                "READY"
        );

        addInfo(
                card,
                "PLATFORM",
                "AVESON CENTRAL"
        );

        layout.addView(card);

        setScreen(
                createScroll(layout)
        );
    }

    // ============================================================
    // UI HELPERS
    // ============================================================

    private LinearLayout createRoot() {

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(30)
        );

        layout.setBackgroundColor(DARK);

        return layout;
    }

    private ScrollView createScroll(
            LinearLayout content
    ) {

        ScrollView scrollView =
                new ScrollView(this);

        scrollView.setFillViewport(true);

        scrollView.setBackgroundColor(DARK);

        scrollView.addView(content);

        return scrollView;
    }

    private void setScreen(View view) {

        mainContainer = new LinearLayout(this);

        mainContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        mainContainer.setBackgroundColor(DARK);

        mainContainer.addView(
                view,
                new LinearLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        setContentView(mainContainer);
    }

    private TextView createTitle(
            String text
    ) {

        TextView title =
                new TextView(this);

        title.setText(text);

        title.setTextColor(TEXT);

        title.setTextSize(25);

        title.setGravity(
                Gravity.CENTER
        );

        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
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

        subtitle.setTextColor(TEXT_GRAY);

        subtitle.setTextSize(13);

        subtitle.setGravity(
                Gravity.CENTER
        );

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

        status.setGravity(
                Gravity.CENTER
        );

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
                android.graphics.Typeface.BOLD
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

        button.setOnClickListener(
                listener
        );

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

    private void addRoomButton(
            LinearLayout layout,
            String title,
            String subtitle,
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

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD_2);

        background.setCornerRadius(
                dp(16)
        );

        background.setStroke(
                dp(1),
                Color.rgb(70, 55, 120)
        );

        card.setBackground(
                background
        );

        TextView titleView =
                new TextView(this);

        titleView.setText(title);

        titleView.setTextColor(TEXT);

        titleView.setTextSize(16);

        titleView.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        TextView subtitleView =
                new TextView(this);

        subtitleView.setText(subtitle);

        subtitleView.setTextColor(TEXT_GRAY);

        subtitleView.setTextSize(12);

        subtitleView.setPadding(
                0,
                dp(5),
                0,
                0
        );

        card.addView(titleView);

        card.addView(subtitleView);

        card.setOnClickListener(
                listener
        );

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

    private void addMenuItem(
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

    private void addButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                createButton(
                        text,
                        BLUE
                );

        button.setOnClickListener(
                listener
        );

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

        button.setTextColor(TEXT);

        button.setTextSize(14);

        button.setGravity(
                Gravity.CENTER
        );

        button.setAllCaps(false);

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);

        background.setCornerRadius(
                dp(14)
        );

        background.setStroke(
                dp(1),
                strokeColor
        );

        button.setBackground(
                background
        );

        button.setPadding(
                dp(12),
                dp(4),
                dp(12),
                dp(4)
        );

        return button;
    }

    // ============================================================
    // CARDS / INFO
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

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);

        background.setCornerRadius(
                dp(16)
        );

        background.setStroke(
                dp(1),
                Color.rgb(55, 45, 90)
        );

        card.setBackground(
                background
        );

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

        text.setTextColor(TEXT_GRAY);

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

        text.setTextColor(TEXT);

        text.setTextSize(14);

        card.addView(text);

        layout.addView(card);
    }

    // ============================================================
    // BACK BUTTON
    // ============================================================

    @Override
    public void onBackPressed() {

        showCentralHome();
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
