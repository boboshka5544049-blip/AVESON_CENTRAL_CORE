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

    // =========================================================
    // ROOT / SCREEN HELPERS
    // =========================================================

    private LinearLayout createRoot() {

        LinearLayout layout = new LinearLayout(activity);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                dp(16),
                dp(38),
                dp(16),
                dp(56)
        );

        layout.setBackgroundColor(DARK);

        return layout;
    }

    private ScrollView createScroll(
            LinearLayout content
    ) {

        ScrollView scroll =
                new ScrollView(activity);

        scroll.setFillViewport(true);

        scroll.setBackgroundColor(DARK);

        scroll.addView(content);

        return scroll;
    }

    private void setScreen(
            LinearLayout layout
    ) {

        activity.setContentView(
                createScroll(layout)
        );
    }

    // =========================================================
    // TEXT
    // =========================================================

    private TextView createTitle(
            String text
    ) {

        TextView title =
                new TextView(activity);

        title.setText(text);

        title.setTextColor(TEXT);

        title.setTextSize(24);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setGravity(
                Gravity.CENTER
        );

        title.setPadding(
                dp(8),
                dp(10),
                dp(8),
                dp(8)
        );

        return title;
    }

    private TextView createSubtitle(
            String text
    ) {

        TextView subtitle =
                new TextView(activity);

        subtitle.setText(text);

        subtitle.setTextColor(
                TEXT_GRAY
        );

        subtitle.setTextSize(14);

        subtitle.setGravity(
                Gravity.CENTER
        );

        subtitle.setPadding(
                dp(8),
                dp(0),
                dp(8),
                dp(18)
        );

        return subtitle;
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private Button createButton(
            String text
    ) {

        Button button =
                new Button(activity);

        button.setText(text);

        button.setTextColor(TEXT);

        button.setTextSize(14);

        button.setAllCaps(false);

        button.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(CARD);

        background.setCornerRadius(
                dp(18)
        );

        background.setStroke(
                dp(1),
                PURPLE
        );

        button.setBackground(background);

        button.setPadding(
                dp(12),
                dp(8),
                dp(12),
                dp(8)
        );

        return button;
    }

    private void addButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                createButton(text);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(52)
                );

        params.setMargins(
                0,
                dp(5),
                0,
                dp(5)
        );

        layout.addView(
                button,
                params
        );

        button.setOnClickListener(listener);
    }

    // =========================================================
    // CARD
    // =========================================================

    private LinearLayout createCard(
            String title
    ) {

        LinearLayout card =
                new LinearLayout(activity);

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

        background.setColor(CARD);

        background.setCornerRadius(
                dp(18)
        );

        background.setStroke(
                dp(1),
                Color.rgb(55, 45, 75)
        );

        card.setBackground(background);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        params.setMargins(
                0,
                dp(8),
                0,
                dp(8)
        );

        TextView titleView =
                new TextView(activity);

        titleView.setText(title);

        titleView.setTextColor(PURPLE);

        titleView.setTextSize(16);

        titleView.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

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
            LinearLayout card,
            String name,
            String value
    ) {

        LinearLayout row =
                new LinearLayout(activity);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView nameView =
                new TextView(activity);

        nameView.setText(name);

        nameView.setTextColor(
                TEXT_GRAY
        );

        nameView.setTextSize(13);

        TextView valueView =
                new TextView(activity);

        valueView.setText(value);

        valueView.setTextColor(
                TEXT
        );

        valueView.setTextSize(13);

        valueView.setGravity(
                Gravity.END
        );

        LinearLayout.LayoutParams nameParams =
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                );

        LinearLayout.LayoutParams valueParams =
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                );

        row.addView(
                nameView,
                nameParams
        );

        row.addView(
                valueView,
                valueParams
        );

        row.setPadding(
                0,
                dp(5),
                0,
                dp(5)
        );

        card.addView(row);
    }

    // =========================================================
    // GLOBAL MENU
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
                "Analytics",
                v -> showAnalytics()
        );

        addButton(
                layout,
                "Royalty Control",
                v -> showRoyaltyControl()
        );

        addButton(
                layout,
                "Security",
                v -> showSecurity()
        );

        addButton(
                layout,
                "Parameters",
                v -> showParameters()
        );

        addButton(
                layout,
                "System Status",
                v -> showSystemStatus()
        );

        addButton(
                layout,
                "AVESON Central Info",
                v -> showAvesonCentralInfo()
        );

        addButton(
                layout,
                "← BACK",
                v -> homeAction.run()
        );

        setScreen(layout);
    }

    // =========================================================
    // ANALYTICS
    // =========================================================

    private void showAnalytics() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("ANALYTICS")
        );

        layout.addView(
                createSubtitle(
                        "AVESON Central Analytics"
                )
        );

        LinearLayout overview =
                createCard("📊 OVERVIEW");

        addInfo(
                overview,
                "Total Artists",
                "0"
        );

        addInfo(
                overview,
                "Total Releases",
                "0"
        );

        addInfo(
                overview,
                "Total Submissions",
                "0"
        );

        addInfo(
                overview,
                "Total Revenue",
                "$0.00"
        );

        layout.addView(overview);

        LinearLayout system =
                createCard("🖥️ SYSTEM ANALYTICS");

        addInfo(
                system,
                "System Activity",
                "No data"
        );

        addInfo(
                system,
                "User Activity",
                "No data"
        );

        addInfo(
                system,
                "API Activity",
                "No data"
        );

        addInfo(
                system,
                "Distribution Activity",
                "No data"
        );

        layout.addView(system);

        addButton(
                layout,
                "Artist Analytics",
                v -> showMessage(
                        "Artist Analytics"
                )
        );

        addButton(
                layout,
                "Music Analytics",
                v -> showMessage(
                        "Music Analytics"
                )
        );

        addButton(
                layout,
                "Distribution Analytics",
                v -> showMessage(
                        "Distribution Analytics"
                )
        );

        addButton(
                layout,
                "System Analytics",
                v -> showMessage(
                        "System Analytics"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    // =========================================================
    // ROYALTY CONTROL
    // =========================================================

    private void showRoyaltyControl() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "ROYALTY CONTROL"
                )
        );

        layout.addView(
                createSubtitle(
                        "AVESON Royalty Management"
                )
        );

        LinearLayout overview =
                createCard("💰 ROYALTY OVERVIEW");

        addInfo(
                overview,
                "Total Revenue",
                "$0.00"
        );

        addInfo(
                overview,
                "Artist Royalties",
                "$0.00"
        );

        addInfo(
                overview,
                "Pending Royalties",
                "$0.00"
        );

        addInfo(
                overview,
                "Paid Royalties",
                "$0.00"
        );

        layout.addView(overview);

        addButton(
                layout,
                "Royalty Reports",
                v -> showMessage(
                        "Royalty Reports"
                )
        );

        addButton(
                layout,
                "Artist Royalties",
                v -> showMessage(
                        "Artist Royalties"
                )
        );

        addButton(
                layout,
                "Payment Status",
                v -> showMessage(
                        "Payment Status"
                )
        );

        addButton(
                layout,
                "Royalty Settings",
                v -> showMessage(
                        "Royalty Settings"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    // =========================================================
    // SECURITY
    // =========================================================

    private void showSecurity() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("SECURITY")
        );

        layout.addView(
                createSubtitle(
                        "AVESON Central Security Management"
                )
        );

        addButton(
                layout,
                "🔐 Access Control",
                v -> showAccessControl()
        );

        addButton(
                layout,
                "👤 Role Management",
                v -> showRoleDetails(
                        "Role Management"
                )
        );

        addButton(
                layout,
                "🛡️ Permission Management",
                v -> showPermissionManagement()
        );

        addButton(
                layout,
                "📱 Authorized Devices",
                v -> showAuthorizedDevices()
        );

        addButton(
                layout,
                "🕐 Login Activity",
                v -> showLoginActivity()
        );

        addButton(
                layout,
                "🔒 Security Protection",
                v -> showSecurityProtection()
        );

        addButton(
                layout,
                "⚠️ Security Events",
                v -> showSecurityEvents()
        );

        addButton(
                layout,
                "🔑 Password & Authentication",
                v -> showPasswordAuthentication()
        );

        addButton(
                layout,
                "🌐 Network & API Security",
                v -> showNetworkApiSecurity()
        );

        addButton(
                layout,
                "📋 Security Logs",
                v -> showSecurityLogs()
        );

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    private void showAccessControl() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("ACCESS CONTROL")
        );

        addButton(
                layout,
                "Admin Access",
                v -> showMessage("Admin Access")
        );

        addButton(
                layout,
                "Central Access",
                v -> showMessage("Central Access")
        );

        addButton(
                layout,
                "Room Access",
                v -> showMessage("Room Access")
        );

        addButton(
                layout,
                "Session Control",
                v -> showMessage("Session Control")
        );

        addButton(
                layout,
                "Access Logs",
                v -> showMessage("Access Logs")
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showRoleDetails(
            String role
    ) {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        role.toUpperCase()
                )
        );

        LinearLayout card =
                createCard("👤 ROLE");

        addInfo(
                card,
                "Role",
                role
        );

        addInfo(
                card,
                "Status",
                "AVAILABLE"
        );

        addInfo(
                card,
                "Access",
                "Protected"
        );

        addInfo(
                card,
                "Permissions",
                "Available"
        );

        layout.addView(card);

        addButton(
                layout,
                "Role Permissions",
                v -> showMessage(
                        "Role Permissions"
                )
        );

        addButton(
                layout,
                "Role Access",
                v -> showMessage(
                        "Role Access"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showPermissionManagement() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "PERMISSION MANAGEMENT"
                )
        );

        addButton(
                layout,
                "Central Permissions",
                v -> showMessage(
                        "Central Permissions"
                )
        );

        addButton(
                layout,
                "Room Permissions",
                v -> showMessage(
                        "Room Permissions"
                )
        );

        addButton(
                layout,
                "User Permissions",
                v -> showMessage(
                        "User Permissions"
                )
        );

        addButton(
                layout,
                "Role Permissions",
                v -> showMessage(
                        "Role Permissions"
                )
        );

        addButton(
                layout,
                "Permission Logs",
                v -> showMessage(
                        "Permission Logs"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showAuthorizedDevices() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "AUTHORIZED DEVICES"
                )
        );

        addButton(
                layout,
                "Device List",
                v -> showMessage(
                        "Device List"
                )
        );

        addButton(
                layout,
                "Active Devices",
                v -> showMessage(
                        "Active Devices"
                )
        );

        addButton(
                layout,
                "Blocked Devices",
                v -> showMessage(
                        "Blocked Devices"
                )
        );

        addButton(
                layout,
                "Device Management",
                v -> showMessage(
                        "Device Management"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showLoginActivity() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "LOGIN ACTIVITY"
                )
        );

        addButton(
                layout,
                "Recent Logins",
                v -> showMessage(
                        "Recent Logins"
                )
        );

        addButton(
                layout,
                "Successful Logins",
                v -> showMessage(
                        "Successful Logins"
                )
        );

        addButton(
                layout,
                "Failed Logins",
                v -> showMessage(
                        "Failed Logins"
                )
        );

        addButton(
                layout,
                "Login Locations",
                v -> showMessage(
                        "Login Locations"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showSecurityProtection() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "SECURITY PROTECTION"
                )
        );

        addButton(
                layout,
                "Authentication Protection",
                v -> showMessage(
                        "Authentication Protection"
                )
        );

        addButton(
                layout,
                "Session Protection",
                v -> showMessage(
                        "Session Protection"
                )
        );

        addButton(
                layout,
                "Access Protection",
                v -> showMessage(
                        "Access Protection"
                )
        );

        addButton(
                layout,
                "Data Protection",
                v -> showMessage(
                        "Data Protection"
                )
        );

        addButton(
                layout,
                "System Protection",
                v -> showMessage(
                        "System Protection"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showSecurityEvents() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "SECURITY EVENTS"
                )
        );

        addButton(
                layout,
                "Security Alerts",
                v -> showMessage(
                        "Security Alerts"
                )
        );

        addButton(
                layout,
                "Access Events",
                v -> showMessage(
                        "Access Events"
                )
        );

        addButton(
                layout,
                "Authentication Events",
                v -> showMessage(
                        "Authentication Events"
                )
        );

        addButton(
                layout,
                "System Security Events",
                v -> showMessage(
                        "System Security Events"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showPasswordAuthentication() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "PASSWORD & AUTHENTICATION"
                )
        );

        addButton(
                layout,
                "Password Policy",
                v -> showMessage(
                        "Password Policy"
                )
        );

        addButton(
                layout,
                "Authentication Method",
                v -> showMessage(
                        "Authentication Method"
                )
        );

        addButton(
                layout,
                "Two-Factor Authentication",
                v -> showMessage(
                        "Two-Factor Authentication"
                )
        );

        addButton(
                layout,
                "Session Authentication",
                v -> showMessage(
                        "Session Authentication"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showNetworkApiSecurity() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "NETWORK & API SECURITY"
                )
        );

        addButton(
                layout,
                "Network Protection",
                v -> showMessage(
                        "Network Protection"
                )
        );

        addButton(
                layout,
                "API Authentication",
                v -> showMessage(
                        "API Authentication"
                )
        );

        addButton(
                layout,
                "API Permissions",
                v -> showMessage(
                        "API Permissions"
                )
        );

        addButton(
                layout,
                "API Security Logs",
                v -> showMessage(
                        "API Security Logs"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    private void showSecurityLogs() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "SECURITY LOGS"
                )
        );

        addButton(
                layout,
                "Access Logs",
                v -> showMessage(
                        "Access Logs"
                )
        );

        addButton(
                layout,
                "Authentication Logs",
                v -> showMessage(
                        "Authentication Logs"
                )
        );

        addButton(
                layout,
                "System Security Logs",
                v -> showMessage(
                        "System Security Logs"
                )
        );

        addButton(
                layout,
                "API Security Logs",
                v -> showMessage(
                        "API Security Logs"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showSecurity()
        );

        setScreen(layout);
    }

    // =========================================================
    // PARAMETERS
    // =========================================================

    private void showParameters() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("PARAMETERS")
        );

        layout.addView(
                createSubtitle(
                        "AVESON Central Parameters"
                )
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

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    private void showLanguages() {

        LinearLayout layout =
                createRoot();

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

            addButton(
                    layout,
                    language,
                    v -> showMessage(language)
            );
        }

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        setScreen(layout);
    }

    private void showAppearance() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("APPEARANCE")
        );

        addButton(
                layout,
                "Dark Mode",
                v -> showMessage("Dark Mode")
        );

        addButton(
                layout,
                "Theme",
                v -> showMessage("Theme")
        );

        addButton(
                layout,
                "Colors",
                v -> showMessage("Colors")
        );

        addButton(
                layout,
                "Font",
                v -> showMessage("Font")
        );

        addButton(
                layout,
                "Interface",
                v -> showMessage("Interface")
        );

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        setScreen(layout);
    }

    private void showNotifications() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle("NOTIFICATIONS")
        );

        addButton(
                layout,
                "System Notifications",
                v -> showMessage(
                        "System Notifications"
                )
        );

        addButton(
                layout,
                "Artist Notifications",
                v -> showMessage(
                        "Artist Notifications"
                )
        );

        addButton(
                layout,
                "Release Notifications",
                v -> showMessage(
                        "Release Notifications"
                )
        );

        addButton(
                layout,
                "Distribution Notifications",
                v -> showMessage(
                        "Distribution Notifications"
                )
        );

        addButton(
                layout,
                "Security Notifications",
                v -> showMessage(
                        "Security Notifications"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        setScreen(layout);
    }

    // =========================================================
    // MUSIC PARAMETERS
    // =========================================================

    private void showMusicParameters() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "MUSIC PARAMETERS"
                )
        );

        addButton(
                layout,
                "Audio Standards",
                v -> showAudioStandards()
        );

        addButton(
                layout,
                "Cover Standards",
                v -> showCoverStandards()
        );

        addButton(
                layout,
                "Release Standards",
                v -> showReleaseStandards()
        );

        addButton(
                layout,
                "Metadata Standards",
                v -> showMetadataStandards()
        );

        addButton(
                layout,
                "Music File Formats",
                v -> showMusicFileFormats()
        );

        addButton(
                layout,
                "Music Quality",
                v -> showMusicQuality()
        );

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        setScreen(layout);
    }

    private void showAudioStandards() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "AUDIO STANDARDS"
                )
        );

        addButton(
                layout,
                "WAV",
                v -> showMessage("WAV")
        );

        addButton(
                layout,
                "MP3",
                v -> showMessage("MP3")
        );

        addButton(
                layout,
                "Sample Rate",
                v -> showMessage(
                        "Sample Rate"
                )
        );

        addButton(
                layout,
                "Bit Depth",
                v -> showMessage(
                        "Bit Depth"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    private void showCoverStandards() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "COVER STANDARDS"
                )
        );

        addButton(
                layout,
                "Minimum Resolution",
                v -> showMessage(
                        "Minimum Resolution"
                )
        );

        addButton(
                layout,
                "3000 × 3000",
                v -> showMessage(
                        "3000 × 3000"
                )
        );

        addButton(
                layout,
                "Cover Format",
                v -> showMessage(
                        "Cover Format"
                )
        );

        addButton(
                layout,
                "Cover Quality",
                v -> showMessage(
                        "Cover Quality"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    private void showReleaseStandards() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "RELEASE STANDARDS"
                )
        );

        addButton(
                layout,
                "Release Date",
                v -> showMessage(
                        "Release Date"
                )
        );

        addButton(
                layout,
                "Release Type",
                v -> showMessage(
                        "Release Type"
                )
        );

        addButton(
                layout,
                "Explicit Content",
                v -> showMessage(
                        "Explicit Content"
                )
        );

        addButton(
                layout,
                "Copyright",
                v -> showMessage(
                        "Copyright"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    private void showMetadataStandards() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "METADATA STANDARDS"
                )
        );

        addButton(
                layout,
                "Title",
                v -> showMessage("Title")
        );

        addButton(
                layout,
                "Artist",
                v -> showMessage("Artist")
        );

        addButton(
                layout,
                "Album",
                v -> showMessage("Album")
        );

        addButton(
                layout,
                "Genre",
                v -> showMessage("Genre")
        );

        addButton(
                layout,
                "Language",
                v -> showMessage("Language")
        );

        addButton(
                layout,
                "Copyright",
                v -> showMessage("Copyright")
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    private void showMusicFileFormats() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "MUSIC FILE FORMATS"
                )
        );

        addButton(
                layout,
                "WAV",
                v -> showMessage("WAV")
        );

        addButton(
                layout,
                "MP3",
                v -> showMessage("MP3")
        );

        addButton(
                layout,
                "FLAC",
                v -> showMessage("FLAC")
        );

        addButton(
                layout,
                "AAC",
                v -> showMessage("AAC")
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    private void showMusicQuality() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "MUSIC QUALITY"
                )
        );

        addButton(
                layout,
                "High Quality",
                v -> showMessage(
                        "High Quality"
                )
        );

        addButton(
                layout,
                "Lossless",
                v -> showMessage(
                        "Lossless"
                )
        );

        addButton(
                layout,
                "Master Quality",
                v -> showMessage(
                        "Master Quality"
                )
        );

        addButton(
                layout,
                "Streaming Quality",
                v -> showMessage(
                        "Streaming Quality"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showMusicParameters()
        );

        setScreen(layout);
    }

    // =========================================================
    // DISTRIBUTION PARAMETERS
    // =========================================================

    private void showDistributionParameters() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "DISTRIBUTION PARAMETERS"
                )
        );

        addButton(
                layout,
                "Distribution Standards",
                v -> showDistributionStandards()
        );

        addButton(
                layout,
                "Platform Settings",
                v -> showPlatformSettings()
        );

        addButton(
                layout,
                "Release Delivery Settings",
                v -> showReleaseDeliverySettings()
        );

        addButton(
                layout,
                "Territory Settings",
                v -> showTerritorySettings()
        );

        addButton(
                layout,
                "Distribution Formats",
                v -> showDistributionFormats()
        );

        addButton(
                layout,
                "Delivery Rules",
                v -> showDeliveryRules()
        );

        addButton(
                layout,
                "← BACK",
                v -> showParameters()
        );

        setScreen(layout);
    }

    private void showDistributionStandards() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "DISTRIBUTION STANDARDS"
                )
        );

        addButton(
                layout,
                "Release Requirements",
                v -> showMessage(
                        "Release Requirements"
                )
        );

        addButton(
                layout,
                "Audio Requirements",
                v -> showMessage(
                        "Audio Requirements"
                )
        );

        addButton(
                layout,
                "Cover Requirements",
                v -> showMessage(
                        "Cover Requirements"
                )
        );

        addButton(
                layout,
                "Metadata Requirements",
                v -> showMessage(
                        "Metadata Requirements"
                )
        );

        addButton(
                layout,
                "Copyright Requirements",
                v -> showMessage(
                        "Copyright Requirements"
                )
        );

        addButton(
                layout,
                "Artist Requirements",
                v -> showMessage(
                        "Artist Requirements"
                )
        );

        addButton(
                layout,
                "Content Requirements",
                v -> showMessage(
                        "Content Requirements"
                )
        );

        addButton(
                layout,
                "Validation Rules",
                v -> showMessage(
                        "Validation Rules"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    private void showPlatformSettings() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "PLATFORM SETTINGS"
                )
        );

        addButton(
                layout,
                "Platform List",
                v -> showMessage(
                        "Platform List"
                )
        );

        addButton(
                layout,
                "Platform Connections",
                v -> showMessage(
                        "Platform Connections"
                )
        );

        addButton(
                layout,
                "Platform Status",
                v -> showMessage(
                        "Platform Status"
                )
        );

        addButton(
                layout,
                "Platform Credentials",
                v -> showMessage(
                        "Platform Credentials"
                )
        );

        addButton(
                layout,
                "Platform Rules",
                v -> showMessage(
                        "Platform Rules"
                )
        );

        addButton(
                layout,
                "Platform Mapping",
                v -> showMessage(
                        "Platform Mapping"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    private void showReleaseDeliverySettings() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "RELEASE DELIVERY SETTINGS"
                )
        );

        addButton(
                layout,
                "Automatic Delivery",
                v -> showMessage(
                        "Automatic Delivery"
                )
        );

        addButton(
                layout,
                "Manual Delivery",
                v -> showMessage(
                        "Manual Delivery"
                )
        );

        addButton(
                layout,
                "Delivery Schedule",
                v -> showMessage(
                        "Delivery Schedule"
                )
        );

        addButton(
                layout,
                "Delivery Priority",
                v -> showMessage(
                        "Delivery Priority"
                )
        );

        addButton(
                layout,
                "Delivery Retry",
                v -> showMessage(
                        "Delivery Retry"
                )
        );

        addButton(
                layout,
                "Delivery Status",
                v -> showMessage(
                        "Delivery Status"
                )
        );

        addButton(
                layout,
                "Delivery Confirmation",
                v -> showMessage(
                        "Delivery Confirmation"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    private void showTerritorySettings() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "TERRITORY SETTINGS"
                )
        );

        addButton(
                layout,
                "Worldwide",
                v -> showMessage("Worldwide")
        );

        addButton(
                layout,
                "Country Management",
                v -> showMessage(
                        "Country Management"
                )
        );

        addButton(
                layout,
                "Territory Groups",
                v -> showMessage(
                        "Territory Groups"
                )
        );

        addButton(
                layout,
                "Restricted Territories",
                v -> showMessage(
                        "Restricted Territories"
                )
        );

        addButton(
                layout,
                "Territory Rules",
                v -> showMessage(
                        "Territory Rules"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    private void showDistributionFormats() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "DISTRIBUTION FORMATS"
                )
        );

        addButton(
                layout,
                "Audio Distribution",
                v -> showMessage(
                        "Audio Distribution"
                )
        );

        addButton(
                layout,
                "Video Distribution",
                v -> showMessage(
                        "Video Distribution"
                )
        );

        addButton(
                layout,
                "Streaming Format",
                v -> showMessage(
                        "Streaming Format"
                )
        );

        addButton(
                layout,
                "Download Format",
                v -> showMessage(
                        "Download Format"
                )
        );

        addButton(
                layout,
                "Master Format",
                v -> showMessage(
                        "Master Format"
                )
        );

        addButton(
                layout,
                "Platform Format Mapping",
                v -> showMessage(
                        "Platform Format Mapping"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    private void showDeliveryRules() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "DELIVERY RULES"
                )
        );

        addButton(
                layout,
                "Automatic Approval",
                v -> showMessage(
                        "Automatic Approval"
                )
        );

        addButton(
                layout,
                "Manual Approval",
                v -> showMessage(
                        "Manual Approval"
                )
        );

        addButton(
                layout,
                "Quality Check",
                v -> showMessage(
                        "Quality Check"
                )
        );

        addButton(
                layout,
                "Metadata Check",
                v -> showMessage(
                        "Metadata Check"
                )
        );

        addButton(
                layout,
                "Copyright Check",
                v -> showMessage(
                        "Copyright Check"
                )
        );

        addButton(
                layout,
                "Platform Check",
                v -> showMessage(
                        "Platform Check"
                )
        );

        addButton(
                layout,
                "Delivery Validation",
                v -> showMessage(
                        "Delivery Validation"
                )
        );

        addButton(
                layout,
                "Failure Handling",
                v -> showMessage(
                        "Failure Handling"
                )
        );

        addButton(
                layout,
                "← BACK",
                v -> showDistributionParameters()
        );

        setScreen(layout);
    }

    // =========================================================
    // SYSTEM STATUS
    // =========================================================

    private void showSystemStatus() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "SYSTEM STATUS"
                )
        );

        layout.addView(
                createSubtitle(
                        "AVESON Central System Monitoring"
                )
        );

        LinearLayout central =
                createCard("🖥️ CENTRAL SYSTEM");

        addInfo(
                central,
                "Central System",
                "ONLINE"
        );

        addInfo(
                central,
                "Application",
                "RUNNING"
        );

        addInfo(
                central,
                "Runtime",
                "ACTIVE"
        );

        addInfo(
                central,
                "Version",
                "1.0"
        );

        addInfo(
                central,
                "Uptime",
                "Not available"
        );

        addInfo(
                central,
                "Process Status",
                "RUNNING"
        );

        layout.addView(central);

        LinearLayout database =
                createCard("💾 DATABASE");

        addInfo(
                database,
                "Database Status",
                "NOT CONNECTED"
        );

        addInfo(
                database,
                "Connection Status",
                "NOT CONNECTED"
        );

        addInfo(
                database,
                "Database Type",
                "Not configured"
        );

        addInfo(
                database,
                "Database Version",
                "Not available"
        );

        addInfo(
                database,
                "Connection Pool",
                "Not available"
        );

        addInfo(
                database,
                "Last Check",
                "Not available"
        );

        layout.addView(database);

        LinearLayout backend =
                createCard("🌐 BACKEND");

        addInfo(
                backend,
                "Backend Status",
                "NOT CONNECTED"
        );

        addInfo(
                backend,
                "API Server",
                "NOT CONNECTED"
        );

        addInfo(
                backend,
                "Service Status",
                "NOT CONNECTED"
        );

        addInfo(
                backend,
                "Endpoint Status",
                "NOT CONFIGURED"
        );

        addInfo(
                backend,
                "Server Region",
                "Not configured"
        );

        addInfo(
                backend,
                "Last Response",
                "Not available"
        );

        layout.addView(backend);

        LinearLayout api =
                createCard("🔌 API");

        addInfo(
                api,
                "API Status",
                "NOT CONNECTED"
        );

        addInfo(
                api,
                "API Version",
                "Not configured"
        );

        addInfo(
                api,
                "API Endpoint",
                "Not configured"
        );

        addInfo(
                api,
                "Request Status",
                "Not available"
        );

        addInfo(
                api,
                "Response Status",
                "Not available"
        );

        addInfo(
                api,
                "Rate Limit",
                "Not available"
        );

        addInfo(
                api,
                "API Activity",
                "No data"
        );

        layout.addView(api);

        LinearLayout https =
                createCard("🔐 HTTPS / SSL");

        addInfo(
                https,
                "HTTPS Status",
                "NOT CONFIGURED"
        );

        addInfo(
                https,
                "SSL Certificate",
                "Not configured"
        );

        addInfo(
                https,
                "Certificate Expiry",
                "Not available"
        );

        addInfo(
                https,
                "TLS Version",
                "Not configured"
        );

        addInfo(
                https,
                "Secure Connection",
                "Not configured"
        );

        layout.addView(https);

        LinearLayout services =
                createCard("🛠️ SERVICES");

        addInfo(
                services,
                "Music Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Distribution Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Studio Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Magazine Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Films Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Notification Service",
                "NOT CONNECTED"
        );

        addInfo(
                services,
                "Authentication Service",
                "NOT CONNECTED"
        );

        layout.addView(services);

        LinearLayout storage =
                createCard("📦 STORAGE");

        addInfo(
                storage,
                "Storage Status",
                "NOT CONFIGURED"
        );

        addInfo(
                storage,
                "File Storage",
                "Not connected"
        );

        addInfo(
                storage,
                "Media Storage",
                "Not connected"
        );

        addInfo(
                storage,
                "Database Storage",
                "Not connected"
        );

        addInfo(
                storage,
                "Available Storage",
                "Not available"
        );

        addInfo(
                storage,
                "Storage Usage",
                "0%"
        );

        layout.addView(storage);

        LinearLayout monitoring =
                createCard(
                        "📊 SYSTEM MONITORING"
                );

        addInfo(
                monitoring,
                "CPU Usage",
                "0%"
        );

        addInfo(
                monitoring,
                "Memory Usage",
                "0%"
        );

        addInfo(
                monitoring,
                "Network Status",
                "NOT CONNECTED"
        );

        addInfo(
                monitoring,
                "Request Monitor",
                "No data"
        );

        addInfo(
                monitoring,
                "Error Monitor",
                "No data"
        );

        addInfo(
                monitoring,
                "System Logs",
                "Available"
        );

        addInfo(
                monitoring,
                "Health Check",
                "Not performed"
        );

        layout.addView(monitoring);

        LinearLayout operations =
                createCard(
                        "🔄 SYSTEM OPERATIONS"
                );

        addInfo(
                operations,
                "Refresh Status",
                "Available"
        );

        addInfo(
                operations,
                "Run Health Check",
                "Available"
        );

        addInfo(
                operations,
                "Service Restart",
                "Not connected"
        );

        addInfo(
                operations,
                "Maintenance Mode",
                "Disabled"
        );

        addInfo(
                operations,
                "System Update",
                "Available"
        );

        addInfo(
                operations,
                "System Diagnostics",
                "Available"
        );

        layout.addView(operations);

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    // =========================================================
    // AVESON CENTRAL INFO
    // =========================================================

    private void showAvesonCentralInfo() {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(
                        "AVESON CENTRAL INFO"
                )
        );

        layout.addView(
                createSubtitle(
                        "Information about the AVESON Central management platform"
                )
        );

        LinearLayout central =
                createCard("🏢 AVESON CENTRAL");

        addInfo(
                central,
                "System Name",
                "AVESON CENTRAL"
        );

        addInfo(
                central,
                "Platform Type",
                "Global Management Platform"
        );

        addInfo(
                central,
                "System Version",
                "1.0"
        );

        addInfo(
                central,
                "System Status",
                "ONLINE"
        );

        addInfo(
                central,
                "Platform Status",
                "RUNNING"
        );

        layout.addView(central);

        LinearLayout ecosystem =
                createCard("🎵 AVESON ECOSYSTEM");

        addInfo(
                ecosystem,
                "Artist Management",
                "AVESON ARTIST CONTROL"
        );

        addInfo(
                ecosystem,
                "Music Management",
                "AVESON MUSIC CONTROL"
        );

        addInfo(
                ecosystem,
                "Distribution Management",
                "AVESON DISTRIBUTION CONTROL"
        );

        addInfo(
                ecosystem,
                "Studio Management",
                "AVESON STUDIO CONTROL"
        );

        addInfo(
                ecosystem,
                "Magazine Management",
                "AVESON MAGAZINE CONTROL"
        );

        addInfo(
                ecosystem,
                "Film Management",
                "AVESON FILMS"
        );

        layout.addView(ecosystem);

        LinearLayout architecture =
                createCard(
                        "⚙️ SYSTEM ARCHITECTURE"
                );

        addInfo(
                architecture,
                "Central Management",
                "Unified AVESON control layer"
        );

        addInfo(
                architecture,
                "Room Management",
                "Six Central management rooms"
        );

        addInfo(
                architecture,
                "Global Menu",
                "Central-wide management functions"
        );

        addInfo(
                architecture,
                "Security Layer",
                "Access and security management"
        );

        addInfo(
                architecture,
                "Parameters System",
                "Global platform configuration"
        );

        addInfo(
                architecture,
                "System Status",
                "System health and service monitoring"
        );

        layout.addView(architecture);

        LinearLayout scope =
                createCard("🌍 PLATFORM SCOPE");

        addInfo(
                scope,
                "Music",
                "Global music management"
        );

        addInfo(
                scope,
                "Distribution",
                "Global music distribution"
        );

        addInfo(
                scope,
                "Artists",
                "Artist management and submissions"
        );

        addInfo(
                scope,
                "Studio",
                "Audio production and studio management"
        );

        addInfo(
                scope,
                "Magazine",
                "Editorial and media management"
        );

        addInfo(
                scope,
                "Films",
                "Film and video management"
        );

        layout.addView(scope);

        LinearLayout systemInfo =
                createCard(
                        "📋 SYSTEM INFORMATION"
                );

        addInfo(
                systemInfo,
                "Application",
                "AVESON CENTRAL"
        );

        addInfo(
                systemInfo,
                "Package",
                "com.aveson.central"
        );

        addInfo(
                systemInfo,
                "Build Version",
                "1.0"
        );

        addInfo(
                systemInfo,
                "Development Status",
                "ACTIVE DEVELOPMENT"
        );

        addInfo(
                systemInfo,
                "Backend",
                "NOT CONNECTED"
        );

        addInfo(
                systemInfo,
                "Database",
                "NOT CONNECTED"
        );

        addInfo(
                systemInfo,
                "API",
                "NOT CONNECTED"
        );

        layout.addView(systemInfo);

        LinearLayout security =
                createCard(
                        "🛡️ SECURITY & CONTROL"
                );

        addInfo(
                security,
                "Central Access",
                "Protected"
        );

        addInfo(
                security,
                "Role Management",
                "Available"
        );

        addInfo(
                security,
                "Permission Management",
                "Available"
        );

        addInfo(
                security,
                "Security Monitoring",
                "Available"
        );

        addInfo(
                security,
                "System Logs",
                "Available"
        );

        layout.addView(security);

        LinearLayout aveson =
                createCard("©️ AVESON");

        addInfo(
                aveson,
                "Platform",
                "AVESON"
        );

        addInfo(
                aveson,
                "Product",
                "AVESON CENTRAL"
        );

        addInfo(
                aveson,
                "Purpose",
                "Unified global management platform"
        );

        addInfo(
                aveson,
                "Terms & Policies",
                "Available"
        );

        addInfo(
                aveson,
                "Privacy",
                "Available"
        );

        addInfo(
                aveson,
                "Copyright",
                "AVESON"
        );

        addInfo(
                aveson,
                "Contact",
                "AVESON Platform Administration"
        );

        layout.addView(aveson);

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String name
    ) {

        LinearLayout layout =
                createRoot();

        layout.addView(
                createTitle(name)
        );

        LinearLayout card =
                createCard(
                        "AVESON CENTRAL"
                );

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

        layout.addView(card);

        addButton(
                layout,
                "← BACK",
                v -> showGlobalMenu()
        );

        setScreen(layout);
    }

    // =========================================================
    // DP
    // =========================================================

    private int dp(
            int value
    ) {

        return (int)
                (value *
                        activity
                                .getResources()
                                .getDisplayMetrics()
                                .density
                );
    }
            }
