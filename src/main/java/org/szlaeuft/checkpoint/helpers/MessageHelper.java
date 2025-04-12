package org.szlaeuft.checkpoint.helpers;

public class MessageHelper {
    private String title;

    private String sub1Label;
    private String sub1Value;

    private String sub2Label;
    private String sub2Value;

    private String sub3Label;
    private String sub3Value;

    public MessageHelper(
            String title,
            String sub1Label, String sub1Value,
            String sub2Label, String sub2Value,
            String sub3Label, String sub3Value) {
        this.title = safe(title);
        this.sub1Label = safe(sub1Label);
        this.sub1Value = safe(sub1Value);
        this.sub2Label = safe(sub2Label);
        this.sub2Value = safe(sub2Value);
        this.sub3Label = safe(sub3Label);
        this.sub3Value = safe(sub3Value);
    }

    public MessageHelper(String title, String sub1Label, String sub1Value) {
        this(title, sub1Label, sub1Value, null, null, null, null);
    }

    public MessageHelper(String title) {
        this(title, null, null, null, null, null, null);
    }

    private String safe(String input) {
        return input != null ? input : "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");

        if (!sub1Label.isEmpty() || !sub1Value.isEmpty()) {
            sb.append(sub1Label).append(": ").append(sub1Value).append("\n");
        }
        if (!sub2Label.isEmpty() || !sub2Value.isEmpty()) {
            sb.append(sub2Label).append(": ").append(sub2Value).append("\n");
        }
        if (!sub3Label.isEmpty() || !sub3Value.isEmpty()) {
            sb.append(sub3Label).append(": ").append(sub3Value).append("\n");
        }

        return sb.toString().trim(); // removes final newline
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getSub1Label() {
        return sub1Label;
    }

    public String getSub1Value() {
        return sub1Value;
    }

    public String getSub2Label() {
        return sub2Label;
    }

    public String getSub2Value() {
        return sub2Value;
    }

    public String getSub3Label() {
        return sub3Label;
    }

    public String getSub3Value() {
        return sub3Value;
    }
}
