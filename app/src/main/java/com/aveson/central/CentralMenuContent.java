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

public class CentralMenuContent {

    private final Activity activity;
    private final Runnable menuAction;

    private final int PURPLE = Color.rgb(185, 100, 255);
    private final int BLUE = Color.rgb(80, 150, 255);
    private final int DARK = Color.rgb(8, 8, 18);
    private final int CARD = Color.rgb(15, 15, 30);
    private final int TEXT = Color.WHITE;
    private final int TEXT_GRAY = Color.rgb(165, 165, 185);
    private final int GREEN = Color.rgb(70, 220, 130);
    private final int YELLOW = Color.rgb(240, 190, 70);

    public CentralMenuContent(Activity activity, Runnable menuAction) {
        this.activity = activity;
        this.menuAction = menuAction;
    }

    private int dp(int value) {
        return (int) (value
                * activity.getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout createRoot() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp(18), dp(24), dp(18), dp(56));
        layout.setBackgroundColor(DARK);
        return layout;
    }

    private ScrollView createScroll(LinearLayout layout) {
        ScrollView scroll = new ScrollView(activity);
        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setBackgroundColor(DARK);
        scroll.addView(layout);
        return scroll;
    }

    private void setScreen(ScrollView scroll) {
        activity.setContentView(scroll);
    }

    private TextView createTitle(String text) {
        TextView title = new TextView(activity);
        title.setText(text);
        title.setTextColor(TEXT);
        title.setTextSize(25);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(dp(4), dp(4), dp(4), dp(8));
        return title;
    }

    private TextView createSubtitle(String text) {
        TextView subtitle = new TextView(activity);
        subtitle.setText(text);
        subtitle.setTextColor(TEXT_GRAY);
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(dp(4), 0, dp(4), dp(18));
        return subtitle;
    }

    private Button createButton(String text) {
        Button button = new Button(activity);
        button.setText(text);
        button.setTextColor(TEXT);
        button.setTextSize(15);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        button.setPadding(dp(8), 0, dp(8), 0);

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(18));
        background.setStroke(dp(1), PURPLE);

        button.setBackground(background);
        return button;
    }

    private void addButton(
            LinearLayout layout,
            String text,
            View.OnClickListener listener
    ) {
        Button button = createButton(text);
        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, dp(52));

        params.setMargins(0, dp(6), 0, dp(6));
        layout.addView(button, params);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(activity);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(18));
        background.setStroke(dp(1), Color.rgb(70, 55, 120));

        card.setBackground(background);
        return card;
    }

    private void addInfo(
            LinearLayout card,
            String label,
            String value
    ) {
        TextView info = new TextView(activity);
        info.setText(label + "\n" + value);
        info.setTextColor(TEXT);
        info.setTextSize(14);
        info.setPadding(dp(4), dp(8), dp(4), dp(8));
        card.addView(info);
    }

    private void addSectionButton(
            LinearLayout layout,
            String title,
            String description,
            View.OnClickListener listener
    ) {
        Button button = createButton(title + "\n" + description);
        button.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        button.setTextSize(14);
        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, dp(62));

        params.setMargins(0, dp(5), 0, dp(5));
        layout.addView(button, params);
    }

    private void showGlobalMenu() {
        if (menuAction != null) {
            menuAction.run();
        }
    }

    // =========================================================
    // ANALYTICS
    // =========================================================

    private void showAnalytics() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📊 ANALYTICS"));
        layout.addView(createSubtitle(
                "Global AVESON statistics and insights"
        ));

        LinearLayout card = createCard();

        addInfo(card, "Live Data", "Not connected");
        addInfo(card, "Statistics",
                "Will appear when the relevant data sources are connected");
        addInfo(card, "Reporting",
                "Analytics sections are organized below");

        layout.addView(card);

        layout.addView(createSubtitle("ROOM ANALYTICS"));

        addSectionButton(
                layout,
                "🎵 Artist Analytics",
                "Artists, submissions and releases",
                v -> showAnalyticsDetail("Artist Analytics")
        );

        addSectionButton(
                layout,
                "🎼 Music Analytics",
                "Catalog, users and playlists",
                v -> showAnalyticsDetail("Music Analytics")
        );

        addSectionButton(
                layout,
                "🌍 Distribution Analytics",
                "Platforms and delivery",
                v -> showAnalyticsDetail("Distribution Analytics")
        );

        addSectionButton(
                layout,
                "🎙️ Studio Analytics",
                "Projects and production",
                v -> showAnalyticsDetail("Studio Analytics")
        );

        addSectionButton(
                layout,
                "📰 Magazine Analytics",
                "Editorial and audience",
                v -> showAnalyticsDetail("Magazine Analytics")
        );

        addSectionButton(
                layout,
                "🎬 Film Analytics",
                "Film and video performance",
                v -> showAnalyticsDetail("Film Analytics")
        );

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    private void showAnalyticsDetail(String name) {
        LinearLayout layout = createRoot();

        layout.addView(createTitle(name));
        layout.addView(createSubtitle(
                "AVESON CENTRAL • ANALYTICS"
        ));

        LinearLayout statusCard = createCard();

        addInfo(statusCard, "Section", name);
        addInfo(statusCard, "Interface Status", "READY");
        addInfo(statusCard, "System", "AVESON CENTRAL");
        addInfo(statusCard, "Data Connection", "NOT CONNECTED");

        layout.addView(statusCard);

        layout.addView(createSubtitle("KEY INDICATORS"));

        LinearLayout metricsCard = createCard();

        if (name.equals("Artist Analytics")) {

            addInfo(metricsCard, "Total Artists", "—");
            addInfo(metricsCard, "Active Artists", "—");
            addInfo(metricsCard, "Pending Submissions", "—");
            addInfo(metricsCard, "Total Releases", "—");
            addInfo(metricsCard, "Approved Releases", "—");
            addInfo(metricsCard, "Rejected Releases", "—");
            addInfo(metricsCard, "Streams", "—");
            addInfo(metricsCard, "Listeners", "—");

        } else if (name.equals("Music Analytics")) {

            addInfo(metricsCard, "Total Tracks", "—");
            addInfo(metricsCard, "Total Albums", "—");
            addInfo(metricsCard, "Catalog Growth", "—");
            addInfo(metricsCard, "Active Playlists", "—");
            addInfo(metricsCard, "Listeners", "—");
            addInfo(metricsCard, "Streams", "—");

        } else if (name.equals("Distribution Analytics")) {

            addInfo(metricsCard, "Distribution Releases", "—");
            addInfo(metricsCard, "Pending Deliveries", "—");
            addInfo(metricsCard, "Delivered Releases", "—");
            addInfo(metricsCard, "Failed Deliveries", "—");
            addInfo(metricsCard, "Connected Platforms", "—");
            addInfo(metricsCard, "Territories", "—");

        } else if (name.equals("Studio Analytics")) {

            addInfo(metricsCard, "Total Projects", "—");
            addInfo(metricsCard, "Active Projects", "—");
            addInfo(metricsCard, "Recording Sessions", "—");
            addInfo(metricsCard, "Mixing Projects", "—");
            addInfo(metricsCard, "Mastering Projects", "—");
            addInfo(metricsCard, "Completed Projects", "—");

        } else if (name.equals("Magazine Analytics")) {

            addInfo(metricsCard, "Total Articles", "—");
            addInfo(metricsCard, "Draft Articles", "—");
            addInfo(metricsCard, "Pending Reviews", "—");
            addInfo(metricsCard, "Published Articles", "—");
            addInfo(metricsCard, "Readers", "—");
            addInfo(metricsCard, "Article Views", "—");

        } else if (name.equals("Film Analytics")) {

            addInfo(metricsCard, "Film Projects", "—");
            addInfo(metricsCard, "Active Productions", "—");
            addInfo(metricsCard, "Completed Films", "—");
            addInfo(metricsCard, "Published Videos", "—");
            addInfo(metricsCard, "Total Views", "—");
            addInfo(metricsCard, "Watch Time", "—");

        } else {

            addInfo(metricsCard, "Metrics", "Not configured");
        }

        layout.addView(metricsCard);

        LinearLayout dataCard = createCard();

        addInfo(
                dataCard,
                "Analytics Source",
                "Statistics require a connected AVESON backend."
        );

        addInfo(
                dataCard,
                "Data Integrity",
                "Unavailable values are displayed as —."
        );

        addInfo(
                dataCard,
                "Last Updated",
                "Unavailable until a data source is connected."
        );

        layout.addView(dataCard);

        addButton(
                layout,
                "← BACK TO ANALYTICS",
                v -> showAnalytics()
        );

        setScreen(createScroll(layout));
    }

    // =========================================================
    // ROYALTY CONTROL
    // =========================================================

    private void showRoyaltyControl() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("💰 ROYALTY CONTROL"));
        layout.addView(createSubtitle(
                "Revenue, royalty and payment management"
        ));

        LinearLayout card = createCard();

        addInfo(card, "Revenue Data", "Not connected");
        addInfo(card, "Artist Earnings",
                "Will appear when royalty data is connected");
        addInfo(card, "Payments",
                "Payment records are not connected to this screen yet");

        layout.addView(card);

        addSectionButton(
                layout,
                "📄 Royalty Reports",
                "Statements and financial reports",
                v -> showRoyaltyDetail("Royalty Reports")
        );

        addSectionButton(
                layout,
                "💵 Artist Earnings",
                "Artist-level earnings",
                v -> showRoyaltyDetail("Artist Earnings")
        );

        addSectionButton(
                layout,
                "🌍 Platform Earnings",
                "Revenue by platform",
                v -> showRoyaltyDetail("Platform Earnings")
        );

        addSectionButton(
                layout,
                "📅 Payment History",
                "Previous royalty payments",
                v -> showRoyaltyDetail("Payment History")
        );

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    private void showRoyaltyDetail(String name) {
        LinearLayout layout = createRoot();

        layout.addView(createTitle(name));
        layout.addView(createSubtitle(
                "AVESON CENTRAL • ROYALTY CONTROL"
        ));

        LinearLayout card = createCard();

        if (name.equals("Royalty Reports")) {

            addInfo(card, "Statements", "Report section prepared");
            addInfo(card, "Reporting Period",
                    "To be selected when royalty data is connected");
            addInfo(card, "Export",
                    "Available after report data is connected");

        } else if (name.equals("Artist Earnings")) {

            addInfo(card, "Earnings by Artist",
                    "Requires connected royalty records");
            addInfo(card, "Available Balance", "Not available yet");
            addInfo(card, "Pending Balance", "Not available yet");

        } else if (name.equals("Platform Earnings")) {

            addInfo(card, "Revenue by Platform",
                    "Requires connected platform reports");
            addInfo(card, "Reporting Currency", "To be configured");
            addInfo(card, "Reconciliation",
                    "Requires platform financial data");

        } else if (name.equals("Payment History")) {

            addInfo(card, "Payment Records",
                    "Requires connected payment records");
            addInfo(card, "Payment Status", "Not available yet");
            addInfo(card, "Payout Statements",
                    "Available after payment data is connected");
        }

        addInfo(
                card,
                "Connection Status",
                "No live royalty or payment source is connected yet"
        );

        layout.addView(card);

        addButton(
                layout,
                "← BACK TO ROYALTY CONTROL",
                v -> showRoyaltyControl()
        );

        setScreen(createScroll(layout));
    }

    // =========================================================
    // SECURITY
    // =========================================================

    private void showSecurity() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🔐 SECURITY"));
        layout.addView(createSubtitle(
                "AVESON Central security management"
        ));

        addSectionButton(
                layout,
                "👥 Access Control",
                "Users and access levels",
                v -> showAccessControl()
        );

        addSectionButton(
                layout,
                "🛡️ Security Protection",
                "System protection controls",
                v -> showSecurityProtection()
        );

        addSectionButton(
                layout,
                "📱 Authorized Devices",
                "Connected authorized devices",
                v -> showAuthorizedDevices()
        );

        addSectionButton(
                layout,
                "📋 Login Activity",
                "Recent access activity",
                v -> showLoginActivity()
        );

        addSectionButton(
                layout,
                "⚠️ Security Events",
                "Security event monitoring",
                v -> showSecurityEvents()
        );

        addSectionButton(
                layout,
                "🔑 Password & Authentication",
                "Authentication settings",
                v -> showPasswordAuthentication()
        );

        addSectionButton(
                layout,
                "🌐 Network & API Security",
                "Network and API protection",
                v -> showNetworkApiSecurity()
        );

        addSectionButton(
                layout,
                "📜 Security Logs",
                "Security audit logs",
                v -> showSecurityLogs()
        );

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    private void showAccessControl() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("👥 ACCESS CONTROL"));

        addSectionButton(
                layout,
                "🔑 Role Details",
                "Central access roles",
                v -> showRoleDetails()
        );

        addSectionButton(
                layout,
                "🛡️ Permission Management",
                "Permissions by role",
                v -> showPermissionManagement()
        );

        addInfoToLayout(
                layout,
                "Access Status",
                "Private Central Management"
        );

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showRoleDetails() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🔑 ROLE DETAILS"));

        LinearLayout card = createCard();

        addInfo(card, "Central Administrator", "Full Central control");
        addInfo(card, "Artist Control", "Artist and release management");
        addInfo(card, "Music Control", "Music system management");
        addInfo(card, "Distribution Control", "Distribution management");
        addInfo(card, "Studio Control", "Studio management");
        addInfo(card, "Magazine Control", "Editorial management");
        addInfo(card, "Films", "Film and video management");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showAccessControl());

        setScreen(createScroll(layout));
    }

    private void showPermissionManagement() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🛡️ PERMISSION MANAGEMENT"));

        LinearLayout card = createCard();

        addInfo(card, "Artist Control", "Management permissions");
        addInfo(card, "Music Control", "Catalog permissions");
        addInfo(card, "Distribution Control", "Delivery permissions");
        addInfo(card, "Studio Control", "Production permissions");
        addInfo(card, "Magazine Control", "Editorial permissions");
        addInfo(card, "Films", "Content permissions");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showAccessControl());

        setScreen(createScroll(layout));
    }

    private void showAuthorizedDevices() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📱 AUTHORIZED DEVICES"));

        LinearLayout card = createCard();

        addInfo(card, "Device Management", "Not connected");
        addInfo(card, "Current Device", "Verification not implemented");
        addInfo(card, "Device Verification", "Not configured");
        addInfo(card, "Unknown Devices", "Blocking not implemented");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showLoginActivity() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📋 LOGIN ACTIVITY"));

        LinearLayout card = createCard();

        addInfo(card, "Last Login", "No login records connected");
        addInfo(card, "Activity Status", "Awaiting audit integration");
        addInfo(card, "Failed Attempts", "Unavailable");
        addInfo(card, "Audit", "Not connected");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showSecurityProtection() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🛡️ SECURITY PROTECTION"));

        LinearLayout card = createCard();

        addInfo(card, "Access Protection", "Not configured");
        addInfo(card, "Session Protection", "Not configured");
        addInfo(card, "Device Protection", "Not configured");
        addInfo(card, "API Protection", "Requires backend configuration");
        addInfo(card, "Audit Monitoring", "Not connected");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showSecurityEvents() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("⚠️ SECURITY EVENTS"));

        LinearLayout card = createCard();

        addInfo(card, "System Events", "Not connected");
        addInfo(card, "Access Events", "Not connected");
        addInfo(card, "Permission Changes", "Not connected");
        addInfo(card, "Suspicious Activity", "Unavailable");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showPasswordAuthentication() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🔑 PASSWORD & AUTHENTICATION"));

        LinearLayout card = createCard();

        addInfo(card, "Authentication", "Not configured");
        addInfo(card, "Password Protection", "Requires authentication setup");
        addInfo(card, "Session Verification", "Not configured");
        addInfo(card, "Multi-Factor Authentication", "Not configured");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showNetworkApiSecurity() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🌐 NETWORK & API SECURITY"));

        LinearLayout card = createCard();

        addInfo(card, "HTTPS", "Must be enforced by the backend");
        addInfo(card, "API Authentication", "Not connected");
        addInfo(card, "API Access", "Not configured");
        addInfo(card, "Network Monitoring", "Not connected");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    private void showSecurityLogs() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📜 SECURITY LOGS"));

        LinearLayout card = createCard();

        addInfo(card, "Audit Logging", "Not connected");
        addInfo(card, "Access Logs", "Not connected");
        addInfo(card, "Security Logs", "Not connected");
        addInfo(card, "Retention", "Not configured");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // PARAMETERS
    // =========================================================

    private void showParameters() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("⚙️ PARAMETERS"));
        layout.addView(createSubtitle(
                "AVESON Central configuration"
        ));

        addSectionButton(
                layout,
                "🌐 Languages",
                "System language settings",
                v -> showLanguages()
        );

        addSectionButton(
                layout,
                "🎨 Appearance",
                "Interface appearance",
                v -> showAppearance()
        );

        addSectionButton(
                layout,
                "🔔 Notifications",
                "System notifications",
                v -> showNotifications()
        );

        addSectionButton(
                layout,
                "🎵 Music Parameters",
                "Music standards and settings",
                v -> showMusicParameters()
        );

        addSectionButton(
                layout,
                "🌍 Distribution Parameters",
                "Distribution standards and settings",
                v -> showDistributionParameters()
        );

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    private void showLanguages() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🌐 LANGUAGES"));

        LinearLayout card = createCard();

        addInfo(card, "Primary Language", "English");
        addInfo(card, "Available Languages",
                "English / O'zbek / Русский / Español / Français / "
                        + "Deutsch / Türkçe / العربية / 中文 / 한국어");
        addInfo(card, "Language System",
                "Language selection is not implemented yet");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showParameters());

        setScreen(createScroll(layout));
    }

    private void showAppearance() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🎨 APPEARANCE"));

        LinearLayout card = createCard();

        addInfo(card, "Theme", "Dark");
        addInfo(card, "Style", "Futuristic Neon");
        addInfo(card, "Primary Accent", "Purple / Blue");
        addInfo(card, "Interface", "AVESON Central");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showParameters());

        setScreen(createScroll(layout));
    }

    private void showNotifications() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🔔 NOTIFICATIONS"));

        LinearLayout card = createCard();

        addInfo(card, "System Notifications", "Configuration pending");
        addInfo(card, "Security Alerts", "Configuration pending");
        addInfo(card, "Release Alerts", "Configuration pending");
        addInfo(card, "Distribution Alerts", "Configuration pending");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showParameters());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // MUSIC PARAMETERS
    // =========================================================

    private void showMusicParameters() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🎵 MUSIC PARAMETERS"));

        addSectionButton(
                layout,
                "🎧 Audio Standards",
                "Audio file requirements",
                v -> showAudioStandards()
        );

        addSectionButton(
                layout,
                "🖼️ Cover Standards",
                "Artwork requirements",
                v -> showCoverStandards()
        );

        addSectionButton(
                layout,
                "📅 Release Standards",
                "Release requirements",
                v -> showReleaseStandards()
        );

        addSectionButton(
                layout,
                "📝 Metadata Standards",
                "Metadata requirements",
                v -> showMetadataStandards()
        );

        addSectionButton(
                layout,
                "📁 Music File Formats",
                "Supported music formats",
                v -> showMusicFileFormats()
        );

        addSectionButton(
                layout,
                "🎚️ Music Quality",
                "Quality standards",
                v -> showMusicQuality()
        );

        addButton(layout, "← BACK", v -> showParameters());

        setScreen(createScroll(layout));
    }

    private void showAudioStandards() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🎧 AUDIO STANDARDS"));

        LinearLayout card = createCard();

        addInfo(card, "Preferred", "WAV");
        addInfo(card, "Accepted", "WAV / FLAC");
        addInfo(card, "Bit Depth", "24-bit preferred");
        addInfo(card, "Sample Rate", "44.1 / 48 kHz");
        addInfo(card, "Channels", "Stereo");
        addInfo(card, "Quality", "No clipping or distortion");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    private void showCoverStandards() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🖼️ COVER STANDARDS"));

        LinearLayout card = createCard();

        addInfo(card, "Ratio", "1:1");
        addInfo(card, "Recommended", "3000 × 3000 px or larger");
        addInfo(card, "Format", "JPG / PNG");
        addInfo(card, "Color", "RGB");
        addInfo(card, "Quality", "High resolution");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    private void showReleaseStandards() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📅 RELEASE STANDARDS"));

        LinearLayout card = createCard();

        addInfo(card, "Release Type", "Single / EP / Album");
        addInfo(card, "Release Date", "Calendar support planned");
        addInfo(card, "Metadata", "Required");
        addInfo(card, "Audio", "Required");
        addInfo(card, "Cover", "Required");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    private void showMetadataStandards() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📝 METADATA STANDARDS"));

        LinearLayout card = createCard();

        addInfo(card, "Title", "Required");
        addInfo(card, "Artist", "Required");
        addInfo(card, "Genre", "Required");
        addInfo(card, "Language", "Required");
        addInfo(card, "Release Date", "Required");
        addInfo(card, "Explicit Status", "When applicable");
        addInfo(card, "ISRC", "When available");
        addInfo(card, "UPC", "When available");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    private void showMusicFileFormats() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📁 MUSIC FILE FORMATS"));

        LinearLayout card = createCard();

        addInfo(card, "Primary Master", "WAV");
        addInfo(card, "Lossless", "FLAC");
        addInfo(card, "Preview", "MP3");
        addInfo(card, "Preferred Master", "WAV");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    private void showMusicQuality() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🎚️ MUSIC QUALITY"));

        LinearLayout card = createCard();

        addInfo(card, "Master Quality", "High quality");
        addInfo(card, "Clipping", "Not allowed");
        addInfo(card, "Distortion", "Not allowed");
        addInfo(card, "Corrupt Files", "Not allowed");
        addInfo(card, "Stereo", "Recommended");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showMusicParameters());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // DISTRIBUTION PARAMETERS
    // =========================================================

    private void showDistributionParameters() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🌍 DISTRIBUTION PARAMETERS"));

        addSectionButton(
                layout,
                "📋 Distribution Standards",
                "Global distribution rules",
                v -> showDistributionStandards()
        );

        addSectionButton(
                layout,
                "🌐 Platform Settings",
                "Platform configuration",
                v -> showPlatformSettings()
        );

        addSectionButton(
                layout,
                "🚚 Release Delivery Settings",
                "Delivery configuration",
                v -> showReleaseDeliverySettings()
        );

        addSectionButton(
                layout,
                "🗺️ Territory Settings",
                "Territory configuration",
                v -> showTerritorySettings()
        );

        addSectionButton(
                layout,
                "🎵 Distribution Formats",
                "Delivery formats",
                v -> showDistributionFormats()
        );

        addSectionButton(
                layout,
                "📦 Delivery Rules",
                "Distribution delivery rules",
                v -> showDeliveryRules()
        );

        addButton(layout, "← BACK", v -> showParameters());

        setScreen(createScroll(layout));
    }

    private void showDistributionStandards() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📋 DISTRIBUTION STANDARDS"));

        LinearLayout card = createCard();

        addInfo(card, "Master", "High quality WAV / FLAC");
        addInfo(card, "Metadata", "Complete");
        addInfo(card, "Artwork", "Square high resolution");
        addInfo(card, "Rights", "Must be authorized");
        addInfo(card, "Delivery", "Platform compatible");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    private void showPlatformSettings() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🌐 PLATFORM SETTINGS"));

        LinearLayout card = createCard();

        addInfo(card, "Platform Connections", "Not connected");
        addInfo(card, "Delivery Status", "Monitoring not connected");
        addInfo(card, "Platform Mapping", "Configuration pending");
        addInfo(card, "Reporting", "Data source not connected");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    private void showReleaseDeliverySettings() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🚚 RELEASE DELIVERY SETTINGS"));

        LinearLayout card = createCard();

        addInfo(card, "Automatic Delivery", "Not configured");
        addInfo(card, "Delivery Tracking", "Not connected");
        addInfo(card, "Error Monitoring", "Not connected");
        addInfo(card, "Status Updates", "Not connected");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    private void showTerritorySettings() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🗺️ TERRITORY SETTINGS"));

        LinearLayout card = createCard();

        addInfo(card, "Global Territories", "Configuration pending");
        addInfo(card, "Country Restrictions", "Configurable");
        addInfo(card, "Regional Delivery", "Requires platform integration");
        addInfo(card, "Territory Rights", "Requires rights data");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    private void showDistributionFormats() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🎵 DISTRIBUTION FORMATS"));

        LinearLayout card = createCard();

        addInfo(card, "Audio Master", "WAV / FLAC");
        addInfo(card, "Artwork", "JPG / PNG");
        addInfo(card, "Metadata", "Structured");
        addInfo(card, "Delivery Package", "Platform-specific");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    private void showDeliveryRules() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("📦 DELIVERY RULES"));

        LinearLayout card = createCard();

        addInfo(card, "Validation", "Required");
        addInfo(card, "Rights Check", "Required");
        addInfo(card, "Metadata Check", "Required");
        addInfo(card, "Audio Check", "Required");
        addInfo(card, "Artwork Check", "Required");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // SYSTEM STATUS
    // =========================================================

    private void showSystemStatus() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("🛡️ SYSTEM STATUS"));
        layout.addView(createSubtitle(
                "AVESON Central system overview"
        ));

        LinearLayout card = createCard();

        addInfo(card, "Central Interface", "AVAILABLE");
        addInfo(card, "Backend Connection", "NOT CONNECTED");
        addInfo(card, "Artist Control", "Interface preparation");
        addInfo(card, "Music Control", "Interface preparation");
        addInfo(card, "Distribution Control", "Interface preparation");
        addInfo(card, "Studio Control", "Interface preparation");
        addInfo(card, "Magazine Control", "Interface preparation");
        addInfo(card, "AVESON Films", "Interface preparation");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // AVESON CENTRAL INFORMATION
    // =========================================================

    private void showAvesonCentralInfo() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("ℹ️ AVESON CENTRAL"));
        layout.addView(createSubtitle(
                "Global Management & Control Platform"
        ));

        LinearLayout card = createCard();

        addInfo(card, "System", "AVESON CENTRAL");
        addInfo(card, "Purpose", "Unified global management system");
        addInfo(card, "Artist", "AVESON ARTIST CONTROL");
        addInfo(card, "Music", "AVESON MUSIC CONTROL");
        addInfo(card, "Distribution", "AVESON DISTRIBUTION CONTROL");
        addInfo(card, "Studio", "AVESON STUDIO CONTROL");
        addInfo(card, "Magazine", "AVESON MAGAZINE CONTROL");
        addInfo(card, "Films", "AVESON FILMS");
        addInfo(card, "DJ", "AVESON DJ CONTROL");
        addInfo(card, "DAW", "AVESON DAW CONTROL");
        addInfo(card, "Music Player", "AVESON MUSIC PLAYER CONTROL");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showGlobalMenu());

        setScreen(createScroll(layout));
    }

    // =========================================================
    // COMMON MESSAGE SCREEN
    // =========================================================

    private void showMessage(String name) {
        if (name.contains("Analytics")) {
            showAnalyticsDetail(name);

        } else if (
                name.equals("Royalty Reports")
                        || name.equals("Artist Earnings")
                        || name.equals("Platform Earnings")
                        || name.equals("Payment History")
        ) {
            showRoyaltyDetail(name);

        } else {
            LinearLayout layout = createRoot();

            layout.addView(createTitle(name));
            layout.addView(createSubtitle("AVESON CENTRAL"));

            LinearLayout card = createCard();

            addInfo(card, "Section", name);
            addInfo(card, "Purpose", "Central management section");
            addInfo(card, "Data Status",
                    "No live data source is connected to this section yet");

            layout.addView(card);

            addButton(layout, "← BACK", v -> showGlobalMenu());

            setScreen(createScroll(layout));
        }
    }

    private void addInfoToLayout(
            LinearLayout layout,
            String label,
            String value
    ) {
        LinearLayout card = createCard();
        addInfo(card, label, value);
        layout.addView(card);
    }

    // =========================================================
    // PUBLIC METHODS FOR OTHER AVESON CENTRAL CLASSES
    // =========================================================

    public void openAnalytics() {
        showAnalytics();
    }

    public void openRoyaltyControl() {
        showRoyaltyControl();
    }

    public void openSecurity() {
        showSecurity();
    }

    public void openParameters() {
        showParameters();
    }

    public void openSystemStatus() {
        showSystemStatus();
    }

    public void openAvesonCentralInfo() {
        showAvesonCentralInfo();
    }
}
