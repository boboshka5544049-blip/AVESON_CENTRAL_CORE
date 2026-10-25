package com.aveson.central;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
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
                artists.add(new Artist(
                        item.optString("name", ""),
                        item.optString("genre", ""),
                        item.optString("email", "")
                ));
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
                "Registered Artists",
                String.valueOf(artists.size())
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
                "⚖️ Copyright & Cover Standards",
                "Rights checks, beat ownership and cover quality rules",
                v -> showReleaseStandards()
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
        layout.addView(subtitle("Manage artist records"));

        addButton(
                layout,
                "+ ADD ARTIST",
                v -> showAddArtistDialog()
        );

        if (artists.isEmpty()) {
            LinearLayout card = createCard();

            addInfo(
                    card,
                    "Artist List",
                    "No artists have been added yet."
            );

            layout.addView(card);
        }

        for (int i = 0; i < artists.size(); i++) {
            final int index = i;
            Artist artist = artists.get(i);

            LinearLayout card = createCard();

            addInfo(card, "Artist", artist.name);
            addInfo(card, "Genre", artist.genre);
            addInfo(card, "Email", artist.email);

            addButton(
                    card,
                    "VIEW PROFILE",
                    v -> showArtistProfile(index)
            );

            addButton(
                    card,
                    "DELETE ARTIST",
                    v -> confirmDeleteArtist(index)
            );

            layout.addView(card);
        }

        addButton(
                layout,
                "← BACK",
                v -> showDashboard()
        );

        showScreen(layout);
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

                    artists.add(
                            new Artist(name, genre, email)
                    );
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
        layout.addView(subtitle("Artist directory"));

        if (artists.isEmpty()) {
            LinearLayout card = createCard();

            addInfo(
                    card,
                    "Profiles",
                    "Add artists in Artist Management first."
            );

            layout.addView(card);
        }

        for (int i = 0; i < artists.size(); i++) {
            final int index = i;
            Artist artist = artists.get(i);

            addSection(
                    layout,
                    artist.name,
                    artist.genre.isEmpty()
                            ? "Genre not specified"
                            : artist.genre,
                    v -> showArtistProfile(index)
            );
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

        addButton(
                layout,
                "+ SUBMIT RELEASE",
                v -> showAddReleaseDialog(artist.name)
        );

        addButton(
                layout,
                "← BACK",
                v -> showArtistManagement()
        );

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
            addInfo(card, "Required checks", "Beat/instrument ownership, license, master and composition rights, samples, featuring consent, cover size and visual quality");
            if (release.coverName != null && !release.coverName.isEmpty()) {
                addInfo(card, "Cover Art", release.coverName);
            }
            if (release.rightsReviewStatus != null && !"NOT_REVIEWED".equals(release.rightsReviewStatus)) {
                addInfo(card, "Rights Review", release.rightsReviewStatus);
            }
            if (release.audioName != null && !release.audioName.isEmpty()) {
                addInfo(card, "Audio File", release.audioName);
            }

            layout.addView(card);
        }

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }

    private void showChooseArtistDialog() {
        if (artists.isEmpty()) {
            new AlertDialog.Builder(activity)
                    .setTitle("No Artists")
                    .setMessage(
                            "Add an artist before submitting music."
                    )
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        String[] names = new String[artists.size()];

        for (int i = 0; i < artists.size(); i++) {
            names[i] = artists.get(i).name;
        }

        new AlertDialog.Builder(activity)
                .setTitle("Choose Artist")
                .setItems(names, (dialog, which) ->
                        showAddReleaseDialog(names[which])
                )
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
    }

    private void clearPendingAudioForm() {
        pendingReleaseArtist = "";
        pendingReleaseTitleInput = null;
        pendingAudioLabel = null;
        pendingAudioUri = null;
        pendingAudioName = "";
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_AUDIO_FILE || resultCode != Activity.RESULT_OK
                || data == null || data.getData() == null) {
            return;
        }

        Uri uri = data.getData();
        try {
            int flags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
            activity.getContentResolver().takePersistableUriPermission(uri, flags);
        } catch (Exception ignored) {
            // Some document providers do not support persistable permissions.
        }

        pendingAudioUri = uri;
        pendingAudioName = getDisplayName(uri);
        if (pendingAudioName == null || pendingAudioName.trim().isEmpty()) {
            pendingAudioName = "Selected audio file";
        }

        if (pendingAudioLabel != null) {
            pendingAudioLabel.setText("Selected audio: " + pendingAudioName);
        }
        Toast.makeText(activity, "Audio file selected", Toast.LENGTH_SHORT).show();
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

    private void updateReleaseStatus(
            int index,
            String status,
            String reason
    ) {
        if (index < 0 || index >= releases.size()) {
            return;
        }

        releases.get(index).status = status;
        releases.get(index).reason = reason;
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
                "Registered Artists",
                String.valueOf(artists.size())
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

        addButton(layout, "← BACK", v -> showDashboard());

        showScreen(layout);
    }
}
