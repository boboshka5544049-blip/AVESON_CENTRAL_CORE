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

    private int dp(int value) {
        return (int) (value * activity.getResources().getDisplayMetrics().density + 0.5f);
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

    private void addButton(LinearLayout layout, String text, View.OnClickListener listener) {
        Button button = createButton(text);
        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(52)
        );
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

    private void addInfo(LinearLayout card, String label, String value) {
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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(62)
        );
        params.setMargins(0, dp(5), 0, dp(5));
        layout.addView(button, params);
    }
    private void showGlobalMenu() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("☰ AVESON CENTRAL MENU"));
        layout.addView(createSubtitle("Global Central Management"));

        addSectionButton(layout, "📊 Analytics",
                "Global statistics and insights",
                v -> showAnalytics());

        addSectionButton(layout, "💰 Royalty Control",
                "Revenue, royalties and reports",
                v -> showRoyaltyControl());

        addSectionButton(layout, "🔐 Security",
                "Access, protection and security",
                v -> showSecurity());

        addSectionButton(layout, "⚙️ Parameters",
                "Global system parameters",
                v -> showParameters());

        addSectionButton(layout, "🛡️ System Status",
                "AVESON Central system status",
                v -> showSystemStatus());

        addSectionButton(layout, "ℹ️ AVESON Central Info",
                "Information about AVESON Central",
                v -> showAvesonCentralInfo());

        addButton(layout, "← BACK TO AVESON CENTRAL",
                v -> homeAction.run());

        setScreen(createScroll(layout));
    }

    private void showAnalytics() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("📊 ANALYTICS"));
        layout.addView(createSubtitle("Global AVESON statistics and insights"));

        LinearLayout card = createCard();
        addInfo(card, "Active Artists", "1,248");
        addInfo(card, "Music Releases", "3,842");
        addInfo(card, "Distribution Platforms", "18");
        addInfo(card, "Studio Projects", "486");
        addInfo(card, "Magazine Articles", "274");
        addInfo(card, "Film Projects", "52");
        layout.addView(card);

        layout.addView(createSubtitle("ROOM ANALYTICS"));

        addSectionButton(layout, "🎵 Artist Analytics",
                "Artists, submissions and releases",
                v -> showMessage("Artist Analytics"));

        addSectionButton(layout, "🎼 Music Analytics",
                "Catalog, users and playlists",
                v -> showMessage("Music Analytics"));

        addSectionButton(layout, "🌍 Distribution Analytics",
                "Platforms and delivery",
                v -> showMessage("Distribution Analytics"));

        addSectionButton(layout, "🎙️ Studio Analytics",
                "Projects and production",
                v -> showMessage("Studio Analytics"));

        addSectionButton(layout, "📰 Magazine Analytics",
                "Editorial and audience",
                v -> showMessage("Magazine Analytics"));

        addSectionButton(layout, "🎬 Film Analytics",
                "Film and video performance",
                v -> showMessage("Film Analytics"));

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    private void showRoyaltyControl() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("💰 ROYALTY CONTROL"));
        layout.addView(createSubtitle("Revenue, royalty and payment management"));

        LinearLayout card = createCard();
        addInfo(card, "Total Revenue", "$48,230");
        addInfo(card, "Available", "$31,840");
        addInfo(card, "Pending", "$16,390");
        addInfo(card, "Artists With Earnings", "1,024");
        layout.addView(card);

        addSectionButton(layout, "📄 Royalty Reports",
                "Statements and financial reports",
                v -> showMessage("Royalty Reports"));

        addSectionButton(layout, "💵 Artist Earnings",
                "Artist-level earnings",
                v -> showMessage("Artist Earnings"));

        addSectionButton(layout, "🌍 Platform Earnings",
                "Revenue by platform",
                v -> showMessage("Platform Earnings"));

        addSectionButton(layout, "📅 Payment History",
                "Previous royalty payments",
                v -> showMessage("Payment History"));

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    private void showSecurity() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🔐 SECURITY"));
        layout.addView(createSubtitle("AVESON Central security management"));

        addSectionButton(layout, "👥 Access Control",
                "Users and access levels",
                v -> showAccessControl());

        addSectionButton(layout, "🛡️ Security Protection",
                "System protection controls",
                v -> showSecurityProtection());

        addSectionButton(layout, "📱 Authorized Devices",
                "Connected authorized devices",
                v -> showAuthorizedDevices());

        addSectionButton(layout, "📋 Login Activity",
                "Recent access activity",
                v -> showLoginActivity());

        addSectionButton(layout, "⚠️ Security Events",
                "Security event monitoring",
                v -> showSecurityEvents());

        addSectionButton(layout, "🔑 Password & Authentication",
                "Authentication settings",
                v -> showPasswordAuthentication());

        addSectionButton(layout, "🌐 Network & API Security",
                "Network and API protection",
                v -> showNetworkApiSecurity());

        addSectionButton(layout, "📜 Security Logs",
                "Security audit logs",
                v -> showSecurityLogs());

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    private void showAccessControl() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("👥 ACCESS CONTROL"));

        addSectionButton(layout, "🔑 Role Details",
                "Central access roles",
                v -> showRoleDetails());

        addSectionButton(layout, "🛡️ Permission Management",
                "Permissions by role",
                v -> showPermissionManagement());

        addInfoToLayout(layout, "Access Status", "Private Central Management");

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
        addInfo(card, "Device Management", "Enabled");
        addInfo(card, "Current Device", "Authorized");
        addInfo(card, "Device Verification", "Enabled");
        addInfo(card, "Unknown Devices", "Blocked");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showLoginActivity() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("📋 LOGIN ACTIVITY"));

        LinearLayout card = createCard();
        addInfo(card, "Last Login", "Central administrator");
        addInfo(card, "Activity Status", "MONITORED");
        addInfo(card, "Failed Attempts", "0");
        addInfo(card, "Audit", "Enabled");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showSecurityProtection() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🛡️ SECURITY PROTECTION"));

        LinearLayout card = createCard();
        addInfo(card, "Access Protection", "ACTIVE");
        addInfo(card, "Session Protection", "ACTIVE");
        addInfo(card, "Device Protection", "ACTIVE");
        addInfo(card, "API Protection", "ACTIVE");
        addInfo(card, "Audit Monitoring", "ACTIVE");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showSecurityEvents() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("⚠️ SECURITY EVENTS"));

        LinearLayout card = createCard();
        addInfo(card, "System Events", "MONITORED");
        addInfo(card, "Access Events", "MONITORED");
        addInfo(card, "Permission Changes", "MONITORED");
        addInfo(card, "Suspicious Activity", "0");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showPasswordAuthentication() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🔑 PASSWORD & AUTHENTICATION"));

        LinearLayout card = createCard();
        addInfo(card, "Authentication", "ACTIVE");
        addInfo(card, "Password Protection", "ACTIVE");
        addInfo(card, "Session Verification", "ACTIVE");
        addInfo(card, "Multi-Factor Authentication", "READY");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showNetworkApiSecurity() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🌐 NETWORK & API SECURITY"));

        LinearLayout card = createCard();
        addInfo(card, "HTTPS", "Required");
        addInfo(card, "API Authentication", "Protected");
        addInfo(card, "API Access", "Controlled");
        addInfo(card, "Network Monitoring", "ACTIVE");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showSecurityLogs() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("📜 SECURITY LOGS"));

        LinearLayout card = createCard();
        addInfo(card, "Audit Logging", "ACTIVE");
        addInfo(card, "Access Logs", "ACTIVE");
        addInfo(card, "Security Logs", "ACTIVE");
        addInfo(card, "Retention", "Configured");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showSecurity());
        setScreen(createScroll(layout));
    }

    private void showParameters() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("⚙️ PARAMETERS"));
        layout.addView(createSubtitle("AVESON Central configuration"));

        addSectionButton(layout, "🌐 Languages",
                "System language settings",
                v -> showLanguages());

        addSectionButton(layout, "🎨 Appearance",
                "Interface appearance",
                v -> showAppearance());

        addSectionButton(layout, "🔔 Notifications",
                "System notifications",
                v -> showNotifications());

        addSectionButton(layout, "🎵 Music Parameters",
                "Music standards and settings",
                v -> showMusicParameters());

        addSectionButton(layout, "🌍 Distribution Parameters",
                "Distribution standards and settings",
                v -> showDistributionParameters());

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    private void showLanguages() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🌐 LANGUAGES"));

        LinearLayout card = createCard();
        addInfo(card, "Primary Language", "English");
        addInfo(card, "Available", "English / Uzbek / Russian");
        addInfo(card, "Language System", "READY");
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
        addInfo(card, "System Notifications", "Enabled");
        addInfo(card, "Security Alerts", "Enabled");
        addInfo(card, "Release Alerts", "Enabled");
        addInfo(card, "Distribution Alerts", "Enabled");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showParameters());
        setScreen(createScroll(layout));
    }

    private void showMusicParameters() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🎵 MUSIC PARAMETERS"));

        addSectionButton(layout, "🎧 Audio Standards",
                "Audio file requirements",
                v -> showAudioStandards());

        addSectionButton(layout, "🖼️ Cover Standards",
                "Artwork requirements",
                v -> showCoverStandards());

        addSectionButton(layout, "📅 Release Standards",
                "Release requirements",
                v -> showReleaseStandards());

        addSectionButton(layout, "📝 Metadata Standards",
                "Metadata requirements",
                v -> showMetadataStandards());

        addSectionButton(layout, "📁 Music File Formats",
                "Supported music formats",
                v -> showMusicFileFormats());

        addSectionButton(layout, "🎚️ Music Quality",
                "Quality standards",
                v -> showMusicQuality());

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
        addInfo(card, "Release Date", "Calendar supported");
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

    private void showDistributionParameters() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🌍 DISTRIBUTION PARAMETERS"));

        addSectionButton(layout, "📋 Distribution Standards",
                "Global distribution rules",
                v -> showDistributionStandards());

        addSectionButton(layout, "🌐 Platform Settings",
                "Platform configuration",
                v -> showPlatformSettings());

        addSectionButton(layout, "🚚 Release Delivery Settings",
                "Delivery configuration",
                v -> showReleaseDeliverySettings());

        addSectionButton(layout, "🗺️ Territory Settings",
                "Territory configuration",
                v -> showTerritorySettings());

        addSectionButton(layout, "🎵 Distribution Formats",
                "Delivery formats",
                v -> showDistributionFormats());

        addSectionButton(layout, "📦 Delivery Rules",
                "Distribution delivery rules",
                v -> showDeliveryRules());

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
        addInfo(card, "Platform Connections", "Configured");
        addInfo(card, "Delivery Status", "Monitored");
        addInfo(card, "Platform Mapping", "Enabled");
        addInfo(card, "Reporting", "Enabled");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());
        setScreen(createScroll(layout));
    }

    private void showReleaseDeliverySettings() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🚚 RELEASE DELIVERY SETTINGS"));

        LinearLayout card = createCard();
        addInfo(card, "Automatic Delivery", "READY");
        addInfo(card, "Delivery Tracking", "ACTIVE");
        addInfo(card, "Error Monitoring", "ACTIVE");
        addInfo(card, "Status Updates", "ACTIVE");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showDistributionParameters());
        setScreen(createScroll(layout));
    }

    private void showTerritorySettings() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🗺️ TERRITORY SETTINGS"));

        LinearLayout card = createCard();
        addInfo(card, "Global Territories", "Enabled");
        addInfo(card, "Country Restrictions", "Configurable");
        addInfo(card, "Regional Delivery", "Supported");
        addInfo(card, "Territory Rights", "Controlled");
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

    private void showSystemStatus() {
        LinearLayout layout = createRoot();
        layout.addView(createTitle("🛡️ SYSTEM STATUS"));
        layout.addView(createSubtitle("AVESON Central system overview"));

        LinearLayout card = createCard();
        addInfo(card, "Central System", "ONLINE");
        addInfo(card, "Artist Control", "READY");
        addInfo(card, "Music Control", "READY");
        addInfo(card, "Distribution Control", "READY");
        addInfo(card, "Studio Control", "READY");
        addInfo(card, "Magazine Control", "READY");
        addInfo(card, "AVESON Films", "READY");
        layout.addView(card);

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    private void showAvesonCentralInfo() {
        LinearLayout layout = createRoot();

        layout.addView(createTitle("ℹ️ AVESON CENTRAL"));
        layout.addView(createSubtitle("Global Management & Control Platform"));

        LinearLayout card = createCard();

        addInfo(card, "System", "AVESON CENTRAL");
        addInfo(card, "Purpose", "Unified global management system");
        addInfo(card, "Artist", "AVESON ARTIST CONTROL");
        addInfo(card, "Music", "AVESON MUSIC CONTROL");
        addInfo(card, "Distribution", "AVESON DISTRIBUTION CONTROL");
        addInfo(card, "Studio", "AVESON STUDIO CONTROL");
        addInfo(card, "Magazine", "AVESON MAGAZINE CONTROL");
        addInfo(card, "Films", "AVESON FILMS");

        layout.addView(card);

        addButton(layout, "← BACK", v -> showGlobalMenu());
        setScreen(createScroll(layout));
    }

    // =========================================================
    // ROOMS
    // =========================================================

    private void showMessage(String name) {

        LinearLayout layout = createRoot();

        layout.addView(createTitle(name));
        layout.addView(createSubtitle("AVESON CENTRAL"));

        LinearLayout card = createCard();

        addInfo(card, "Section", name);
        addInfo(card, "Status", "AVAILABLE");
        addInfo(card, "System", "AVESON CENTRAL");
        addInfo(card, "Data", "Not connected");

        layout.addView(card);

        addButton(layout, "← BACK",
                v -> showGlobalMenu());

        setScreen(createScroll(layout));
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

}
