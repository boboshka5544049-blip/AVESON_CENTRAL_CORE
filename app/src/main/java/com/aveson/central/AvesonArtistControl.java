package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.provider.OpenableColumns;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvesonArtistControl {

    private final Activity activity;

    private final int BACKGROUND = Color.rgb(8, 8, 18);
    private final int CARD = Color.rgb(17, 17, 32);
    private final int PURPLE = Color.rgb(185, 100, 255);
    private final int BLUE = Color.rgb(80, 150, 255);
    private final int WHITE = Color.WHITE;
    private final int GRAY = Color.rgb(165, 165, 185);
    private final int GREEN = Color.rgb(70, 220, 130);
    private final int RED = Color.rgb(255, 90, 100);

    private final ArrayList<Artist> artists = new ArrayList<>();
    private final ArrayList<Release> releases = new ArrayList<>();

    private final SharedPreferences preferences;

    private static final int REQUEST_AUDIO_FILE = 7412;
    private static final int REQUEST_COVER_FILE = 7413;
    private static final int REQUEST_BACKUP_EXPORT = 7414;
    private static final int REQUEST_BACKUP_IMPORT = 7415;
    private Uri pendingCoverUri;
    private String pendingCoverName = "";
    private TextView pendingCoverLabel;
    private String pendingReleaseArtist = "";
    private EditText pendingReleaseTitleInput;
    private TextView pendingAudioLabel;
    private Uri pendingAudioUri;
    private String pendingAudioName = "";

    public AvesonArtistControl(Activity activity) {
        this.activity = activity;
        this.preferences = activity.getSharedPreferences(
                "aveson_artist_control_data",
                Activity.MODE_PRIVATE
        );
        loadData();
    }

    private void loadData() {
        artists.clear();
        releases.clear();

        try {
            JSONArray artistArray = new JSONArray(
                    preferences.getString("artists", "[]")
            );
            for (int i = 0; i < artistArray.length(); i++) {
                JSONObject item = artistArray.getJSONObject(i);
                Artist artist = new Artist(
                        item.optString("name", ""),
                        item.optString("genre", ""),
                        item.optString("email", "")
                );
                // Existing saved artists remain approved for backward compatibility.
                artist.status = item.optString("status", "APPROVED");
                artist.reviewNotes = item.optString("reviewNotes", "");
                artists.add(artist);
            }

            JSONArray releaseArray = new JSONArray(
                    preferences.getString("releases", "[]")
            );
            for (int i = 0; i < releaseArray.length(); i++) {
                JSONObject item = releaseArray.getJSONObject(i);
                Release release = new Release(
                        item.optString("title", ""),
                        item.optString("artist", "")
                );
                release.status = item.optString("status", "PENDING");
                release.reason = item.optString("reason", "");
                release.audioUri = item.optString("audioUri", "");
                release.audioName = item.optString("audioName", "");
                release.coverUri = item.optString("coverUri", "");
                release.coverName = item.optString("coverName", "");
                release.rightsDeclaration = item.optString("rightsDeclaration", "");
                release.beatLicense = item.optString("beatLicense", "");
                release.masterRights = item.optString("masterRights", "");
                release.compositionRights = item.optString("compositionRights", "");
                release.sampleDeclaration = item.optString("sampleDeclaration", "");
                release.featureConsent = item.optString("featureConsent", "");
                release.rightsDocumentUri = item.optString("rightsDocumentUri", "");
                release.rightsDocumentName = item.optString("rightsDocumentName", "");
                release.rightsReviewStatus = item.optString("rightsReviewStatus", "NOT_REVIEWED");
                release.rightsReviewNotes = item.optString("rightsReviewNotes", "");
                releases.add(release);
            }
        } catch (Exception e) {
            Toast.makeText(
                    activity,
                    "Saved Artist Control data could not be read.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void saveData() {
        try {
            JSONArray artistArray = new JSONArray();
            for (Artist artist : artists) {
                JSONObject item = new JSONObject();
                item.put("name", artist.name);
                item.put("genre", artist.genre);
                item.put("email", artist.email);
                item.put("status", artist.status);
                item.put("reviewNotes", artist.reviewNotes);
                artistArray.put(item);
            }

            JSONArray releaseArray = new JSONArray();
            for (Release release : releases) {
                JSONObject item = new JSONObject();
                item.put("title", release.title);
                item.put("artist", release.artist);
                item.put("status", release.status);
                item.put("reason", release.reason);
                item.put("audioUri", release.audioUri);
                item.put("audioName", release.audioName);
                item.put("coverUri", release.coverUri);
                item.put("coverName", release.coverName);
                item.put("rightsDeclaration", release.rightsDeclaration);
                item.put("beatLicense", release.beatLicense);
                item.put("masterRights", release.masterRights);
                item.put("compositionRights", release.compositionRights);
                item.put("sampleDeclaration", release.sampleDeclaration);
                item.put("featureConsent", release.featureConsent);
                item.put("rightsDocumentUri", release.rightsDocumentUri);
                item.put("rightsDocumentName", release.rightsDocumentName);
                item.put("rightsReviewStatus", release.rightsReviewStatus);
                item.put("rightsReviewNotes", release.rightsReviewNotes);
                releaseArray.put(item);
            }

            preferences.edit()
                    .putString("artists", artistArray.toString())
                    .putString("releases", releaseArray.toString())
                    .apply();
        } catch (Exception e) {
            Toast.makeText(
                    activity,
                    "Could not save Artist Control data.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private int dp(int value) {
        return (int) (
                value * activity.getResources()
                        .getDisplayMetrics().density + 0.5f
        );
    }

    private LinearLayout createRoot() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp(18), dp(24), dp(18), dp(64));
        layout.setBackgroundColor(BACKGROUND);
        return layout;
    }

    private ScrollView createScroll(LinearLayout layout) {
        ScrollView scroll = new ScrollView(activity);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BACKGROUND);
        scroll.addView(layout);
        return scroll;
    }

    private void showScreen(LinearLayout layout) {
        activity.setContentView(createScroll(layout));
    }

    private TextView title(String text) {
        TextView view = new TextView(activity);
        view.setText(text);
        view.setTextColor(WHITE);
        view.setTextSize(24);
        view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        view.setGravity(Gravity.CENTER);
        view.setPadding(dp(4), dp(8), dp(4), dp(8));
        return view;
    }

    private TextView subtitle(String text) {
        TextView view = new TextView(activity);
        view.setText(text);
        view.setTextColor(GRAY);
        view.setTextSize(14);
        view.setGravity(Gravity.CENTER);
        view.setPadding(dp(4), dp(4), dp(4), dp(20));
        return view;
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(activity);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(14), dp(14), dp(14), dp(14));

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(18));
        background.setStroke(dp(1), PURPLE);

        card.setBackground(background);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, dp(6), 0, dp(12));
        card.setLayoutParams(params);

        return card;
    }

    private void addInfo(
            LinearLayout card,
            String label,
            String value
    ) {
        TextView text = new TextView(activity);
        text.setText(label + "\n" + value);
        text.setTextColor(WHITE);
        text.setTextSize(15);
        text.setPadding(dp(4), dp(8), dp(4), dp(8));
        card.addView(text);
    }

    private Button createButton(String text) {
        Button button = new Button(activity);
        button.setText(text);
        button.setTextColor(WHITE);
        button.setTextSize(15);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setPadding(dp(8), dp(4), dp(8), dp(4));

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(dp(16));
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
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(54)
                );

        params.setMargins(0, dp(5), 0, dp(5));
        layout.addView(button, params);
    }

    private void addSection(
            LinearLayout layout,
            String heading,
            String description,
            View.OnClickListener listener
    ) {
        Button button = createButton(
                heading + "\n" + description
        );

        button.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        button.setTextSize(14);
        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(66)
                );

        params.setMargins(0, dp(5), 0, dp(5));
        layout.addView(button, params);
    }

    // =========================================
    // ARTIST DATA
    // =========================================

    private static class Artist {
        String name;
        String genre;
        String email;
        String status = "PENDING";
        String reviewNotes = "";

        Artist(String name, String genre, String email) {
            this.name = name;
            this.genre = genre;
            this.email = email;
        }
    }

    private static class Release {
        String title;
        String artist;
        String status;
        String reason;
        String audioUri = "";
        String audioName = "";
        String coverUri = "";
        String coverName = "";
        String rightsDeclaration = "";
        String beatLicense = "";
        String masterRights = "";
        String compositionRights = "";
        String sampleDeclaration = "";
        String featureConsent = "";
        String rightsDocumentUri = "";
        String rightsDocumentName = "";
        String rightsReviewStatus = "NOT_REVIEWED";
        String rightsReviewNotes = "";

        Release(String title, String artist) {
            this.title = title;
            this.artist = artist;
            this.status = "PENDING";
            this.reason = "";
        }
    }

    // =========================================
    // HOME
    // =========================================

    public void show() {
        showDashboard();
    }

    private void showDashboard() {
        LinearLayout layout = createRoot();

        layout.addView(title("🎵 AVESON"));
        layout.addView(title("ARTIST CONTROL"));
        layout.addView(subtitle(
                "Artist & Release Management"
        ));

        LinearLayout overview = createCard();

        addInfo(
                overview,
                "Approved Artist Profiles",
                String.valueOf(countArtistStatus("APPROVED"))
        );

        addInfo(
                overview,
                "Total Submissions",
                String.valueOf(releases.size())
        );

        addInfo(
                overview,
                "Pending Reviews",
                String.valueOf(countStatus("PENDING"))
        );

        addInfo(
                overview,
                "Approved Releases",
                String.valueOf(countStatus("APPROVED"))
        );

        addInfo(
                overview,
                "Rejected Releases",
                String.valueOf(countStatus("REJECTED"))
        );

        layout.addView(overview);

        layout.addView(subtitle("MANAGEMENT"));

        addSection(
                layout,
                "👥 Artist Management",
                "Manage registered artists",
                v -> showArtistManagement()
        );

        addSection(
                layout,
                "🎤 Artist Profiles",
                "View artist information",
                v -> showArtistProfiles()
        );

        addSection(
                layout,
                "🎵 Music Submissions",
                "View submitted releases",
                v -> showSubmissions()
        );

        addSection(
                layout,
                "🔍 Release Review",
                "Review pending releases",
                v -> showReleaseReview()
        );

        addSection(
                layout,
                "✅ Approved Releases",
                "Approved music releases",
                v -> showReleaseList("APPROVED")
        );

        addSection(
                layout,
                "❌ Rejected Releases",
                "Rejected releases and reasons",
                v -> showReleaseList("REJECTED")
        );

        addSection(
                layout,
                "📊 Artist Analytics",
                "Artist and release statistics",
                v -> showAnalytics()
        );

        addSection(
                layout,
                "⚖️ Copyright & Cover Standards",
                "Rights checks, beat ownership and cover quality rules",
                v -> showReleaseStandards()
        );

        addSection(
                layout,
                "⚙️ Settings",
                "Control information",
                v -> showSettings()
        );

        showScreen(layout);
    }

    // =========================================
    // ARTIST MANAGEMENT
    // =========================================

    private void showArtistManagement() {
        LinearLayout layout = createRoot();
        layout.addView(title("ARTIST MANAGEMENT"));
        layout.addView(subtitle("Profile applications • staff review"));
        addButton(layout, "+ ADD PROFILE APPLICATION", v -> showAddArtistDialog());

        int pending = 0;
        int approved = 0;
        for (Artist artist : artists) {
            if ("PENDING".equals(artist.status)) pending++;
            if ("APPROVED".equals(artist.status)) approved++;
        }
        LinearLayout summary = createCard();
        addInfo(summary, "Pending profile applications", String.valueOf(pending));
        addInfo(summary, "Approved artist profiles", String.valueOf(approved));
        layout.addView(summary);

        if (artists.isEmpty()) {
            LinearLayout card = createCard();
            addInfo(card, "Artist Applications", "No profile applications have been added.");
            layout.addView(card);
        }

        for (int i = 0; i < artists.size(); i++) {
            final int index = i;
            Artist artist = artists.get(i);
            LinearLayout card = createCard();
            addInfo(card, "Artist", artist.name);
            addInfo(card, "Genre", artist.genre);
            addInfo(card, "Email", artist.email);
            addInfo(card, "Profile status", artist.status);
            if (artist.reviewNotes != null && !artist.reviewNotes.trim().isEmpty()) {
                addInfo(card, "Staff notes", artist.reviewNotes);
            }
            addButton(card, "VIEW PROFILE", v -> showArtistProfile(index));
            if ("PENDING".equals(artist.status)) {
                addButton(card, "APPROVE PROFILE", v -> updateArtistProfileStatus(index, "APPROVED"));
                addButton(card, "REJECT PROFILE", v -> showRejectArtistDialog(index));
            }
            addButton(card, "DELETE ARTIST", v -> confirmDeleteArtist(index));
            layout.addView(card);
        }
        addButton(layout, "← BACK", v -> showDashboard());
        showScreen(layout);
    }

    private void updateArtistProfileStatus(int index, String status) {
        if (index < 0 || index >= artists.size()) return;
        Artist artist = artists.get(index);
        artist.status = status;
        if ("APPROVED".equals(status)) artist.reviewNotes = "";
        saveData();
        Toast.makeText(activity, "Artist profile status: " + status, Toast.LENGTH_SHORT).show();
        showArtistManagement();
    }

    private void showRejectArtistDialog(int index) {
        if (index < 0 || index >= artists.size()) return;
        EditText reasonInput = new EditText(activity);
        reasonInput.setHint("Reason for rejection / required corrections");
        reasonInput.setMinLines(2);
        new AlertDialog.Builder(activity)
                .setTitle("Reject Artist Profile")
                .setView(reasonInput)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("REJECT", (dialog, which) -> {
                    String reason = reasonInput.getText().toString().trim();
                    if (reason.isEmpty()) {
                        Toast.makeText(activity, "Enter a reason for rejection.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Artist artist = artists.get(index);
                    artist.status = "REJECTED";
                    artist.reviewNotes = reason;
                    saveData();
                    showArtistManagement();
                })
                .show();
    }

    private void showAddArtistDialog() {
        LinearLayout form = new LinearLayout(activity);
        form.setOrientation(LinearLayout.VERTICAL);
        form.setPadding(dp(20), dp(8), dp(20), dp(8));

        EditText nameInput = new EditText(activity);
        nameInput.setHint("Artist name");
        nameInput.setSingleLine(true);

        EditText genreInput = new EditText(activity);
        genreInput.setHint("Music genre");
        genreInput.setSingleLine(true);

        EditText emailInput = new EditText(activity);
        emailInput.setHint("Email address");
        emailInput.setSingleLine(true);

        form.addView(nameInput);
        form.addView(genreInput);
        form.addView(emailInput);

        new AlertDialog.Builder(activity)
                .setTitle("Add Artist")
                .setView(form)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("SAVE", (dialog, which) -> {
                    String name = nameInput.getText()
                            .toString().trim();

                    String genre = genreInput.getText()
                            .toString().trim();

                    String email = emailInput.getText()
                            .toString().trim();

                    if (name.isEmpty()) {
                        Toast.makeText(
                                activity,
                                "Artist name is required",
                                Toast.LENGTH_SHORT
                        ).show();
                        return;
                    }

                    Artist applicant = new Artist(name, genre, email);
                    applicant.status = "PENDING";
                    artists.add(applicant);
                    saveData();

                    Toast.makeText(
                            activity,
                            "Artist added",
                            Toast.LENGTH_SHORT
                    ).show();

                    showArtistManagement();
                })
                .show();
    }

    private void confirmDeleteArtist(int index) {
        new AlertDialog.Builder(activity)
                .setTitle("Delete Artist")
                .setMessage(
                        "Delete " + artists.get(index).name + "?"
                )
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("DELETE", (dialog, which) -> {
                    artists.remove(index);
                    saveData();
                    showArtistManagement();
                })
                .show();
    }

    // =========================================
    // ARTIST PROFILES
    // =========================================

    private void showArtistProfiles() {
        LinearLayout layout = createRoot();
        layout.addView(title("ARTIST PROFILES"));
        layout.addView(subtitle("Approved artist directory"));
        int approvedCount = 0;
        for (Artist artist : artists) if ("APPROVED".equals(artist.status)) approvedCount++;
        if (approvedCount == 0) {
            LinearLayout card = createCard();
            addInfo(card, "Approved Profiles", "No approved artist profiles yet. Review applications in Artist Management.");
            layout.addView(card);
        }
        for (int i = 0; i < artists.size(); i++) {
            final int index = i;
            Artist artist = artists.get(i);
            if (!"APPROVED".equals(artist.status)) continue;
            addSection(layout, artist.name,
                    artist.genre.isEmpty() ? "Genre not specified" : artist.genre,
                    v -> showArtistProfile(index));
        }
        addButton(layout, "← BACK", v -> showDashboard());
        showScreen(layout);
    }

    private void showArtistProfile(int index) {
        if (index < 0 || index >= artists.size()) {
            showArtistManagement();
            return;
        }

        Artist artist = artists.get(index);

        LinearLayout layout = createRoot();

        layout.addView(title("ARTIST PROFILE"));

        LinearLayout card = createCard();

        addInfo(card, "Artist Name", artist.name);
        addInfo(card, "Genre", artist.genre);
        addInfo(card, "Email", artist.email);

        int artistReleases = 0;

        for (Release release : releases) {
            if (release.artist.equals(artist.name)) {
                artistReleases++;
            }
        }

        addInfo(
                card,
                "Submitted Releases",
                String.valueOf(artistReleases)
        );

        layout.addView(card);

        if ("APPROVED".equals(artist.status)) {
            addButton(layout, "+ SUBMIT RELEASE", v -> showAddReleaseDialog(artist.name));
        } else {
            addInfo(card, "Submission access", "Only approved artist profiles can submit releases.");
        }

        addButton(layout, "← BACK", v -> showArtistProfiles());

        showScreen(layout);
    }

    // =========================================
    // MUSIC SUBMISSIONS
    // =========================================

    private void showSubmissions() {
        LinearLayout layout = createRoot();

        layout.addView(title("MUSIC SUBMISSIONS"));
        layout.addView(subtitle("All submitted releases"));

        addButton(
                layout,
                "+ NEW SUBMISSION",
                v -> showChooseArtistDialog()
        );

        if (releases.isEmpty()) {
            LinearLayout card = createCard();

            addInfo(
                    card,
                    "Submissions",
                    "No releases submitted yet."
            );

            layout.addView(card);
        }

        for (int i = 0; i < releases.size(); i++) {
            Release release = releases.get(i);

            LinearLayout card = createCard();

            addInfo(card, "Release", release.title);
            addInfo(card, "Artist", release.artist);
            addInfo(card, "Status", release.status);
            addInfo(card, "Rights & Cover Review", release.rightsReviewStatus);
            addInfo(card, "Required checks", "WAV audio and playback integrity; beat/instrument license; master and composition rights; samples; featuring consent; cover dimensions and quality; cover content and image rights");
            if (release.reason != null && !release.reason.trim().isEmpty()) {
                addInfo(card, "Review / Rejection Reason", release.reason);
            }
            if (release.coverName != null && !release.coverName.isEmpty()) {
                addInfo(card, "Cover Art", release.coverName);
            }
            if (release.rightsReviewStatus != null && !"NOT_REVIEWED".equals(release.rightsReviewStatus)) {
                addInfo(card, "Rights Review", release.rightsReviewStatus);
            }
            if (release.audioName != null && !release.audioName.isEmpty()) {
                addInfo(card, "Audio File", release.audioName);
            }
            if (release.rightsDeclaration != null && !release.rightsDeclaration.isEmpty()) {
                addInfo(card, "Artist Rights Declaration", release.rightsDeclaration);
            }
            if (release.beatLicense != null && !release.beatLicense.isEmpty()) {
                addInfo(card, "Beat / Instrumental License", release.beatLicense);
            }
            if (release.sampleDeclaration != null && !release.sampleDeclaration.isEmpty()) {
                addInfo(card, "Samples / Featuring", release.sampleDeclaration);
            }

            layout.addView(card);
        }

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }

    private void showChooseArtistDialog() {
        ArrayList<String> approvedNames = new ArrayList<>();
        for (Artist artist : artists) {
            if ("APPROVED".equals(artist.status)) approvedNames.add(artist.name);
        }
        if (approvedNames.isEmpty()) {
            new AlertDialog.Builder(activity)
                    .setTitle("No Approved Artists")
                    .setMessage("Approve an artist profile in Artist Management before submitting music.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }
        String[] names = approvedNames.toArray(new String[0]);
        new AlertDialog.Builder(activity)
                .setTitle("Choose Approved Artist")
                .setItems(names, (dialog, which) -> showAddReleaseDialog(names[which]))
                .show();
    }

    private void showAddReleaseDialog(String artistName) {
        LinearLayout form = new LinearLayout(activity);
        form.setOrientation(LinearLayout.VERTICAL);
        form.setPadding(dp(20), dp(8), dp(20), dp(8));

        EditText input = new EditText(activity);
        input.setHint("Release or song title");
        input.setSingleLine(true);
        form.addView(input);

        TextView audioLabel = new TextView(activity);
        audioLabel.setText("No audio file selected");
        audioLabel.setTextColor(GRAY);
        audioLabel.setPadding(0, dp(12), 0, dp(8));
        form.addView(audioLabel);

        Button chooseAudio = createButton("CHOOSE AUDIO FILE");
        chooseAudio.setOnClickListener(v -> {
            pendingReleaseArtist = artistName;
            pendingReleaseTitleInput = input;
            pendingAudioLabel = audioLabel;
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            try {
                activity.startActivityForResult(intent, REQUEST_AUDIO_FILE);
            } catch (Exception e) {
                Toast.makeText(activity, "Unable to open audio picker.", Toast.LENGTH_LONG).show();
            }
        });
        form.addView(chooseAudio);

        TextView coverLabel = new TextView(activity);
        coverLabel.setText("No cover art selected");
        coverLabel.setTextColor(GRAY);
        coverLabel.setPadding(0, dp(12), 0, dp(8));
        form.addView(coverLabel);

        Button chooseCover = createButton("CHOOSE COVER ART");
        chooseCover.setOnClickListener(v -> {
            pendingReleaseArtist = artistName;
            pendingReleaseTitleInput = input;
            pendingAudioLabel = audioLabel;
            pendingCoverLabel = coverLabel;
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            try {
                activity.startActivityForResult(intent, REQUEST_COVER_FILE);
            } catch (Exception e) {
                Toast.makeText(activity, "Unable to open cover picker.", Toast.LENGTH_LONG).show();
            }
        });
        form.addView(chooseCover);

        EditText rightsDeclarationInput = new EditText(activity);
        rightsDeclarationInput.setHint("Rights declaration (ownership / permissions)");
        rightsDeclarationInput.setMinLines(2);
        form.addView(rightsDeclarationInput);

        EditText beatLicenseInput = new EditText(activity);
        beatLicenseInput.setHint("Beat / instrumental license details (if used)");
        form.addView(beatLicenseInput);

        EditText sampleInput = new EditText(activity);
        sampleInput.setHint("Samples and featuring permissions");
        form.addView(sampleInput);

        new AlertDialog.Builder(activity)
                .setTitle("New Music Submission")
                .setMessage("Artist: " + artistName)
                .setView(form)
                .setNegativeButton("CANCEL", (dialog, which) -> clearPendingAudioForm())
                .setPositiveButton("SUBMIT", (dialog, which) -> {
                    String releaseTitle = input.getText().toString().trim();
                    if (releaseTitle.isEmpty()) {
                        Toast.makeText(activity, "Release title is required", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Release release = new Release(releaseTitle, artistName);
                    if (pendingReleaseArtist.equals(artistName)
                            && pendingReleaseTitleInput == input
                            && pendingAudioUri != null) {
                        release.audioUri = pendingAudioUri.toString();
                        release.audioName = pendingAudioName;
                    }
                    if (pendingReleaseArtist.equals(artistName)
                            && pendingReleaseTitleInput == input
                            && pendingCoverUri != null) {
                        release.coverUri = pendingCoverUri.toString();
                        release.coverName = pendingCoverName;
                    }
                    release.rightsDeclaration = rightsDeclarationInput.getText().toString().trim();
                    release.beatLicense = beatLicenseInput.getText().toString().trim();
                    release.sampleDeclaration = sampleInput.getText().toString().trim();
                    releases.add(release);
                    saveData();
                    clearPendingAudioForm();
                    Toast.makeText(activity, "Submission added for review", Toast.LENGTH_SHORT).show();
                    showSubmissions();
                })
                .setOnCancelListener(dialog -> clearPendingAudioForm())
                .show();

        pendingReleaseArtist = artistName;
        pendingReleaseTitleInput = input;
        pendingAudioLabel = audioLabel;
        pendingAudioUri = null;
        pendingAudioName = "";
        pendingCoverUri = null;
        pendingCoverName = "";
        pendingCoverLabel = null;
    }

    private void clearPendingAudioForm() {
        pendingReleaseArtist = "";
        pendingReleaseTitleInput = null;
        pendingAudioLabel = null;
        pendingAudioUri = null;
        pendingAudioName = "";
        pendingCoverUri = null;
        pendingCoverName = "";
        pendingCoverLabel = null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null || data.getData() == null) {
            return;
        }

        if (requestCode == REQUEST_BACKUP_EXPORT) {
            writeBackupToUri(data.getData());
            return;
        }
        if (requestCode == REQUEST_BACKUP_IMPORT) {
            readBackupFromUri(data.getData());
            return;
        }
        if (requestCode != REQUEST_AUDIO_FILE && requestCode != REQUEST_COVER_FILE) {
            return;
        }

        Uri uri = data.getData();
        try {
            int flags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
            activity.getContentResolver().takePersistableUriPermission(uri, flags);
        } catch (Exception ignored) {
            // Some document providers do not support persistable permissions.
        }

        String displayName = getDisplayName(uri);
        if (displayName == null || displayName.trim().isEmpty()) {
            displayName = "Selected file";
        }

        if (requestCode == REQUEST_AUDIO_FILE) {
            pendingAudioUri = uri;
            pendingAudioName = displayName;
            if (pendingAudioLabel != null) {
                pendingAudioLabel.setText("Selected audio: " + pendingAudioName);
            }
            Toast.makeText(activity, "Audio file selected", Toast.LENGTH_SHORT).show();
        } else {
            pendingCoverUri = uri;
            pendingCoverName = displayName;
            if (pendingCoverLabel != null) {
                pendingCoverLabel.setText("Selected cover: " + pendingCoverName);
            }
            Toast.makeText(activity, "Cover art selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDisplayName(Uri uri) {
        Cursor cursor = null;
        try {
            cursor = activity.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex >= 0) {
                    return cursor.getString(nameIndex);
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) cursor.close();
        }
        return uri.getLastPathSegment();
    }

    private void showReleaseStandards() {
        LinearLayout layout = createRoot();
        layout.addView(title("COPYRIGHT & COVER STANDARDS"));
        layout.addView(subtitle("AVESON ARTIST CONTROL • Staff review policy"));

        LinearLayout rightsCard = createCard();
        addInfo(rightsCard, "1. Third-party beat / instrumental", 
                "If the artist uses another person's beat or instrumental, the artist must provide valid permission or a license covering the intended use. If rights cannot be confirmed, reject the release or return it for correction.");
        addInfo(rightsCard, "2. Beat already released online", 
                "If the beat appears online under another creator's name, staff must check ownership, license terms and any rights conflict. If the artist cannot demonstrate authorized use, or a rights conflict is confirmed, reject the release.");
        addInfo(rightsCard, "3. Rights and permissions", 
                "Check master recording rights, composition/lyrics rights, beat license, samples and required featuring/performer consent. Uploaded documents are evidence for review; their presence alone does not prove ownership.");
        addInfo(rightsCard, "4. Cover dimensions", 
                "Cover art must be at least 2900 × 2900 pixels. Artwork below either minimum dimension does not meet AVESON's cover standard and must be rejected or returned for correction.");
        addInfo(rightsCard, "5. Cover image quality", 
                "Reject or return artwork that is extremely blurry, pixelated, distorted, damaged, unreadable or otherwise clearly below acceptable visual quality.");
        addInfo(rightsCard, "6. Audio format and integrity",
                "Audio must be supplied in WAV format. Reject or return a file that is not WAV, is corrupted, cannot be opened or played correctly, is incomplete, or has obvious technical damage. Staff must verify the actual file and playback; do not rely only on its filename.");
        addInfo(rightsCard, "7. Copyright in music",
                "Do not approve music that unlawfully uses another person's composition, recording, beat, instrumental, sample or other protected material. Ask for applicable permission or license and record the evidence reviewed. If unauthorized use or an unresolved rights conflict is established, reject the release.");
        addInfo(rightsCard, "8. AI-generated cover art",
                "Under this AVESON policy, cover art created using AI must be rejected. Staff should record this as the rejection reason when confirmed.");
        addInfo(rightsCard, "9. Cover photo and image rights",
                "Reject cover art that uses another person's photograph, artwork, logo or other protected image without the required authorization. A photo being available on the internet does not itself grant permission to use it.");
        addInfo(rightsCard, "10. Prohibited cover content",
                "Cover art must not contain advertisements, promotional phone numbers or other contact numbers, concert dates, or +18/adult content. Political words, slogans or political messaging are also not allowed under this AVESON cover policy. Reject or return artwork containing these items.");
        addInfo(rightsCard, "11. Required cover text",
                "The cover must clearly display the artist's stage name or artist name and the track/song title. Check that both are readable and spelled correctly.");
        addInfo(rightsCard, "12. Parental Advisory",
                "If the song contains explicit lyrics or content unsuitable for younger listeners, the cover must include a clearly visible Parental Advisory label in a separate area along the bottom of the cover. Staff should check that the label is present when required.");
        addInfo(rightsCard, "13. Staff decision",
                "Record a specific reason for rejection or correction. Approve only after rights, audio format/integrity, metadata and cover checks are complete.");
        layout.addView(rightsCard);

        LinearLayout outcomeCard = createCard();
        addInfo(outcomeCard, "Possible outcomes", "APPROVED • REJECTED • NEEDS CORRECTION • RIGHTS HOLD");
        addInfo(outcomeCard, "Approval lock", "A release cannot be approved unless its attached audio filename ends in .wav and staff has marked Rights & Cover Review VERIFIED. Staff must still listen to the audio and inspect cover and rights evidence; filename alone does not prove WAV integrity.");
        addInfo(outcomeCard, "Important", "An online match is a reason to investigate, not automatic proof of infringement. Reject when unauthorized use or a rights conflict is established, or when required rights cannot be demonstrated. Apply AVESON's stated cover-content and AI-art rules consistently, and write the exact reason in the staff decision.");
        layout.addView(outcomeCard);

        addButton(layout, "← BACK", v -> showDashboard());
        showScreen(layout);
    }

    private void showReleaseReview() {
        LinearLayout layout = createRoot();

        layout.addView(title("RELEASE REVIEW"));
        layout.addView(subtitle("Pending review queue"));

        int pending = 0;

        for (int i = 0; i < releases.size(); i++) {
            final int index = i;
            Release release = releases.get(i);

            if (!release.status.equals("PENDING")) {
                continue;
            }

            pending++;

            LinearLayout card = createCard();

            addInfo(card, "Release", release.title);
            addInfo(card, "Artist", release.artist);
            addInfo(card, "Status", release.status);
            if (release.audioName != null && !release.audioName.isEmpty()) {
                addInfo(card, "Audio File", release.audioName);
            }

            addButton(
                    card,
                    "RIGHTS & COVER REVIEW",
                    v -> showRightsReview(index)
            );

            addButton(
                    card,
                    "APPROVE RELEASE",
                    v -> updateReleaseStatus(index, "APPROVED", "")
            );

            addButton(
                    card,
                    "REJECT RELEASE",
                    v -> showRejectDialog(index)
            );

            layout.addView(card);
        }

        if (pending == 0) {
            LinearLayout card = createCard();

            addInfo(
                    card,
                    "Review Queue",
                    "No pending releases."
            );

            layout.addView(card);
        }

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }

    private String emptyAsNotProvided(String value) {
        return value == null || value.trim().isEmpty() ? "NOT PROVIDED" : value;
    }

    private void showRightsReview(int index) {
        if (index < 0 || index >= releases.size()) {
            showReleaseReview();
            return;
        }
        Release release = releases.get(index);
        LinearLayout layout = createRoot();
        layout.addView(title("RIGHTS & COVER REVIEW"));
        layout.addView(subtitle("Staff checklist • " + release.title));

        LinearLayout card = createCard();
        addInfo(card, "Artist", release.artist);
        addInfo(card, "Audio file", release.audioName == null || release.audioName.isEmpty()
                ? "NOT PROVIDED" : release.audioName);
        addInfo(card, "Cover art", release.coverName == null || release.coverName.isEmpty()
                ? "NOT PROVIDED" : release.coverName);
        addInfo(card, "Rights review status", release.rightsReviewStatus);
        addInfo(card, "Artist rights declaration", emptyAsNotProvided(release.rightsDeclaration));
        addInfo(card, "Beat / instrumental license", emptyAsNotProvided(release.beatLicense));
        addInfo(card, "Samples / featuring declaration", emptyAsNotProvided(release.sampleDeclaration));
        addInfo(card, "Audio checks",
                "Confirm the actual file is WAV, opens and plays correctly, is complete and has no obvious corruption. Filename alone is not proof of format or integrity.");
        addInfo(card, "Copyright checks",
                "Check beat/instrumental license, master recording rights, composition/lyrics rights, samples, featuring consent and any third-party material.");
        addInfo(card, "Cover checks",
                "Confirm at least 2900 × 2900 pixels, acceptable visual quality, no AI-generated art under AVESON policy, no unauthorized photos/artwork, ads, contact numbers, concert dates, +18 or political text. Artist name and song title must be visible. Add Parental Advisory at the bottom if explicit content requires it.");
        addInfo(card, "Decision notes", release.rightsReviewNotes == null || release.rightsReviewNotes.isEmpty()
                ? "No staff notes yet." : release.rightsReviewNotes);
        layout.addView(card);

        addButton(layout, "MARK CHECKS VERIFIED",
                v -> showRightsReviewNotesDialog(index, "VERIFIED"));
        addButton(layout, "RETURN FOR CORRECTION",
                v -> showRightsReviewNotesDialog(index, "NEEDS_CORRECTION"));
        addButton(layout, "REJECT FOR RIGHTS / QUALITY",
                v -> showRightsReviewNotesDialog(index, "REJECTED"));
        addButton(layout, "← BACK", v -> showReleaseReview());
        showScreen(layout);
    }

    private void showRightsReviewNotesDialog(int index, String decision) {
        if (index < 0 || index >= releases.size()) return;
        EditText notesInput = new EditText(activity);
        notesInput.setHint("Staff notes / exact reason");
        notesInput.setMinLines(3);
        notesInput.setText(releases.get(index).rightsReviewNotes == null
                ? "" : releases.get(index).rightsReviewNotes);
        String dialogTitle = "VERIFIED".equals(decision) ? "Confirm checks"
                : ("NEEDS_CORRECTION".equals(decision) ? "Return for correction" : "Reject release");

        new AlertDialog.Builder(activity)
                .setTitle(dialogTitle)
                .setMessage("Select VERIFIED only after actually checking audio, rights and cover.")
                .setView(notesInput)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("SAVE", (dialog, which) -> {
                    String notes = notesInput.getText().toString().trim();
                    if (!"VERIFIED".equals(decision) && notes.isEmpty()) {
                        Toast.makeText(activity, "Enter the reason or required corrections.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Release selected = releases.get(index);
                    selected.rightsReviewStatus = decision;
                    selected.rightsReviewNotes = notes;
                    if ("REJECTED".equals(decision)) {
                        selected.status = "REJECTED";
                        selected.reason = notes;
                    } else if ("NEEDS_CORRECTION".equals(decision)) {
                        selected.status = "NEEDS_CORRECTION";
                        selected.reason = notes;
                    }
                    saveData();
                    if ("REJECTED".equals(decision) || "NEEDS_CORRECTION".equals(decision)) {
                        showReleaseReview();
                    } else {
                        showRightsReview(index);
                    }
                })
                .show();
    }

    private void showRejectDialog(int index) {
        EditText reasonInput = new EditText(activity);
        reasonInput.setHint("Reason for rejection");

        new AlertDialog.Builder(activity)
                .setTitle("Reject Release")
                .setView(reasonInput)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("REJECT", (dialog, which) -> {
                    String reason = reasonInput.getText()
                            .toString().trim();

                    if (reason.isEmpty()) {
                        reason = "No reason provided";
                    }

                    updateReleaseStatus(
                            index,
                            "REJECTED",
                            reason
                    );
                })
                .show();
    }

    private boolean hasValidWavHeader(String uriString) {
        if (uriString == null || uriString.trim().isEmpty()) return false;
        InputStream input = null;
        try {
            input = activity.getContentResolver().openInputStream(Uri.parse(uriString));
            if (input == null) return false;
            byte[] header = new byte[12];
            int offset = 0;
            while (offset < header.length) {
                int read = input.read(header, offset, header.length - offset);
                if (read < 0) return false;
                offset += read;
            }
            return header[0] == 'R' && header[1] == 'I'
                    && header[2] == 'F' && header[3] == 'F'
                    && header[8] == 'W' && header[9] == 'A'
                    && header[10] == 'V' && header[11] == 'E';
        } catch (Exception e) {
            return false;
        } finally {
            try { if (input != null) input.close(); } catch (Exception ignored) {}
        }
    }

    private boolean isCoverAtLeast2900(String uriString) {
        if (uriString == null || uriString.trim().isEmpty()) return false;
        InputStream input = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            input = activity.getContentResolver().openInputStream(Uri.parse(uriString));
            if (input == null) return false;
            BitmapFactory.decodeStream(input, null, options);
            input.close();
            input = null;
            return options.outWidth >= 2900 && options.outHeight >= 2900;
        } catch (Exception e) {
            return false;
        } finally {
            try { if (input != null) input.close(); } catch (Exception ignored) {}
        }
    }

    private void updateReleaseStatus(
            int index,
            String status,
            String reason
    ) {
        if (index < 0 || index >= releases.size()) {
            return;
        }

        Release selected = releases.get(index);
        if ("APPROVED".equals(status)) {
            String audioName = selected.audioName == null ? "" : selected.audioName.trim();
            if (audioName.isEmpty() || !audioName.toLowerCase(java.util.Locale.ROOT).endsWith(".wav")) {
                Toast.makeText(activity,
                        "Cannot approve: a WAV audio file must be attached first.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (!hasValidWavHeader(selected.audioUri)) {
                Toast.makeText(activity,
                        "Cannot approve: the selected audio does not have a valid WAV RIFF/WAVE header.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (selected.coverUri == null || selected.coverUri.trim().isEmpty()) {
                Toast.makeText(activity,
                        "Cannot approve: cover art must be attached and reviewed first.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (!isCoverAtLeast2900(selected.coverUri)) {
                Toast.makeText(activity,
                        "Cannot approve: cover art must be at least 2900 x 2900 pixels and readable.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (!"VERIFIED".equals(selected.rightsReviewStatus)) {
                Toast.makeText(activity,
                        "Cannot approve: complete RIGHTS & COVER REVIEW and mark checks VERIFIED first.",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        selected.status = status;
        selected.reason = reason;
        saveData();

        Toast.makeText(
                activity,
                "Release status: " + status,
                Toast.LENGTH_SHORT
        ).show();

        showReleaseReview();
    }

    // =========================================
    // APPROVED / REJECTED RELEASES
    // =========================================

    private void showReleaseList(String status) {
        LinearLayout layout = createRoot();
        boolean approved = "APPROVED".equals(status);

        layout.addView(title(
                approved ? "APPROVED RELEASES" : "REJECTED RELEASES"
        ));
        layout.addView(subtitle(
                approved
                        ? "Releases approved by Artist Control"
                        : "Rejected releases and review reasons"
        ));

        int matchingReleases = 0;
        for (Release release : releases) {
            if (!status.equals(release.status)) {
                continue;
            }

            matchingReleases++;
            LinearLayout card = createCard();
            addInfo(card, "Release", release.title);
            addInfo(card, "Artist", release.artist);
            addInfo(card, "Status", release.status);
            if (release.audioName != null && !release.audioName.isEmpty()) {
                addInfo(card, "Audio File", release.audioName);
            }

            if (!approved) {
                String reason = release.reason == null
                        || release.reason.trim().isEmpty()
                        ? "No reason provided"
                        : release.reason;
                addInfo(card, "Rejection Reason", reason);
            }

            layout.addView(card);
        }

        if (matchingReleases == 0) {
            LinearLayout emptyCard = createCard();
            addInfo(
                    emptyCard,
                    approved ? "Approved Releases" : "Rejected Releases",
                    approved
                            ? "No approved releases yet. Approved submissions will appear here."
                            : "No rejected releases yet. Rejected submissions and their reasons will appear here."
            );
            layout.addView(emptyCard);
        }

        addButton(layout, "← BACK", v -> showDashboard());
        showScreen(layout);
    }

    // =========================================
    // ANALYTICS
    // =========================================

    private int countArtistStatus(String status) {
        int count = 0;
        for (Artist artist : artists) if (status.equals(artist.status)) count++;
        return count;
    }

    private int countStatus(String status) {
        int count = 0;

        for (Release release : releases) {
            if (release.status.equals(status)) {
                count++;
            }
        }

        return count;
    }

    private void showAnalytics() {
        LinearLayout layout = createRoot();

        layout.addView(title("ARTIST ANALYTICS"));
        layout.addView(subtitle("Local application statistics"));

        LinearLayout card = createCard();

        addInfo(
                card,
                "Approved Artist Profiles",
                String.valueOf(countArtistStatus("APPROVED"))
        );

        addInfo(
                card,
                "Total Submissions",
                String.valueOf(releases.size())
        );

        addInfo(
                card,
                "Pending",
                String.valueOf(countStatus("PENDING"))
        );

        addInfo(
                card,
                "Approved",
                String.valueOf(countStatus("APPROVED"))
        );

        addInfo(
                card,
                "Rejected",
                String.valueOf(countStatus("REJECTED"))
        );

        layout.addView(card);

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }

    // =========================================
    // LOCAL BACKUP / RESTORE
    // =========================================

    private void exportBackup() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/json");
        intent.putExtra(Intent.EXTRA_TITLE, "aveson_artist_control_backup.json");
        try {
            activity.startActivityForResult(intent, REQUEST_BACKUP_EXPORT);
        } catch (Exception e) {
            Toast.makeText(activity, "Unable to open backup save dialog.", Toast.LENGTH_LONG).show();
        }
    }

    private void writeBackupToUri(Uri uri) {
        OutputStream output = null;
        try {
            JSONObject backup = new JSONObject();
            backup.put("backupType", "AVESON_ARTIST_CONTROL_BACKUP");
            backup.put("backupVersion", 1);
            backup.put("createdAt", System.currentTimeMillis());
            backup.put("artists", preferences.getString("artists", "[]"));
            backup.put("releases", preferences.getString("releases", "[]"));

            output = activity.getContentResolver().openOutputStream(uri, "wt");
            if (output == null) throw new Exception("Could not open backup file.");
            output.write(backup.toString(2).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            output.flush();

            Toast.makeText(activity, "Backup saved. Keep this file in a safe place.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(activity, "Backup failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try { if (output != null) output.close(); } catch (Exception ignored) {}
        }
    }

    private void confirmImportBackup() {
        new AlertDialog.Builder(activity)
                .setTitle("Restore from backup")
                .setMessage("Restoring replaces the artist and release records currently stored on this device. Export a backup of the current data first. Audio and cover files themselves are not included.")
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("CHOOSE BACKUP", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("application/json");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    try {
                        activity.startActivityForResult(intent, REQUEST_BACKUP_IMPORT);
                    } catch (Exception e) {
                        Toast.makeText(activity, "Unable to open backup picker.", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void readBackupFromUri(Uri uri) {
        InputStream input = null;
        try {
            input = activity.getContentResolver().openInputStream(uri);
            if (input == null) throw new Exception("Could not read selected backup.");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] chunk = new byte[4096];
            int count;
            while ((count = input.read(chunk)) != -1) buffer.write(chunk, 0, count);

            JSONObject backup = new JSONObject(
                    new String(buffer.toByteArray(), java.nio.charset.StandardCharsets.UTF_8));
            if (!"AVESON_ARTIST_CONTROL_BACKUP".equals(backup.optString("backupType", ""))) {
                throw new Exception("This is not an AVESON Artist Control backup.");
            }

            String artistsJson = backup.optString("artists", "[]");
            String releasesJson = backup.optString("releases", "[]");
            // Validate both arrays before changing saved data.
            new JSONArray(artistsJson);
            new JSONArray(releasesJson);

            preferences.edit()
                    .putString("artists", artistsJson)
                    .putString("releases", releasesJson)
                    .commit();
            loadData();
            Toast.makeText(activity, "Backup restored.", Toast.LENGTH_LONG).show();
            showDashboard();
        } catch (Exception e) {
            Toast.makeText(activity, "Restore failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try { if (input != null) input.close(); } catch (Exception ignored) {}
        }
    }

    // =========================================
    // SETTINGS
    // =========================================

    private void showSettings() {
        LinearLayout layout = createRoot();

        layout.addView(title("ARTIST CONTROL SETTINGS"));

        LinearLayout card = createCard();

        addInfo(
                card,
                "Application",
                "AVESON ARTIST CONTROL"
        );

        addInfo(
                card,
                "Mode",
                "Employee Management Interface"
        );

        addInfo(
                card,
                "Data Storage",
                "Saved locally on this device (SharedPreferences)"
        );

        addInfo(
                card,
                "Central Connection",
                "Not connected"
        );

        addInfo(
                card,
                "Authentication",
                "Not implemented yet"
        );

        layout.addView(card);

        addButton(layout, "BACK UP DATA", v -> exportBackup());
        addButton(layout, "RESTORE DATA FROM BACKUP", v -> confirmImportBackup());
        addInfo(card, "Backup reminder",
                "Export a backup before uninstalling the app. Backups include artist/release records and file references, not the audio or cover files themselves.");

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }
}
