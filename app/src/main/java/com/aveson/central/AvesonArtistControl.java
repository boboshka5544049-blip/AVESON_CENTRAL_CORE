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

    public AvesonArtistControl(Activity activity) {
        this.activity = activity;
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
        layout.setPadding(dp(18), dp(24), dp(18), dp(32));
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

        layout.addView(title("рџЋµ AVESON"));
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
                "рџ‘Ґ Artist Management",
                "Manage registered artists",
                v -> showArtistManagement()
        );

        addSection(
                layout,
                "рџЋ¤ Artist Profiles",
                "View artist information",
                v -> showArtistProfiles()
        );

        addSection(
                layout,
                "рџЋµ Music Submissions",
                "View submitted releases",
                v -> showSubmissions()
        );

        addSection(
                layout,
                "рџ”Ќ Release Review",
                "Review pending releases",
                v -> showReleaseReview()
        );

        addSection(
                layout,
                "вњ… Approved Releases",
                "Approved music releases",
                v -> showReleaseList("APPROVED")
        );

        addSection(
                layout,
                "вќЊ Rejected Releases",
                "Rejected releases and reasons",
                v -> showReleaseList("REJECTED")
        );

        addSection(
                layout,
                "рџ“Љ Artist Analytics",
                "Artist and release statistics",
                v -> showAnalytics()
        );

        addSection(
                layout,
                "вљ™пёЏ Settings",
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
                "в†ђ BACK",
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

        addButton(layout, "в†ђ BACK", v -> showDashboard());

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
                "в†ђ BACK",
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

            layout.addView(card);
        }

        addButton(layout, "в†ђ BACK", v -> showDashboard());

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
        EditText input = new EditText(activity);
        input.setHint("Release or song title");
        input.setSingleLine(true);

        new AlertDialog.Builder(activity)
                .setTitle("New Music Submission")
                .setMessage("Artist: " + artistName)
                .setView(input)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("SUBMIT", (dialog, which) -> {
                    String releaseTitle = input.getText()
                            .toString().trim();

                    if (releaseTitle.isEmpty()) {
                        Toast.makeText(
                                activity,
                                "Release title is required",
                                Toast.LENGTH_SHORT
                        ).show();
                        return;
                    }

                    releases.add(
                            new Release(releaseTitle, artistName)
                    );

                    Toast.makeText(
                            activity,
                            "Submission added for review",
                            Toast.LENGTH_SHORT
                    ).show();

                    showSubmissions();
                })
                .show();
    }

    // =========================================
    // RELEASE REVIEW
    // =========================================

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

        addButton(layout, "в†ђ BACK", v -> showDashboard());

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

        layout.addView(
                title(
                        status.equals("APPROVED")
                                ? "APPROVED RELEASES"
                                : "REJECTED RELEASES"
                )
        );

        for (Release release : releases) {
            if (!release.status.equals(status)) {
                continue;
            }

            LinearLayout card = createCard();

            addInfo(card, "Release", release.title);
            addInfo(card, "Artist", release.artist);
            addInfo(card, "Status", release.status);

            if (status.equals("REJECTED")) {
                addInfo(
                        card,
                        "Rejection Reason",
                        release.reason
                );
            }

            layout.addView(card);
        }

        addButton(layout, "в†ђ BACK", v -> showDashboard());

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

        addButton(layout, "в†ђ BACK", v -> showDashboard());

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
                "Temporary in-memory storage"
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

        addButton(layout, "в†ђ BACK", v -> showDashboard());

        showScreen(layout);
    }
}
