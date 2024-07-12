package entity;

import java.time.LocalDateTime;

/**
 * Represents a text summary.
 */
public class Summary {
    private int id;
    private String originalText;
    private String summarizedText;
    private LocalDateTime timestamp;

    /**
     * Default constructor.
     */
    public Summary() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id             the unique identifier of the summary
     * @param originalText   the original text to be summarized
     * @param summarizedText the summarized text
     * @param timestamp      the time when the summary was created
     */
    public Summary(int id, String originalText, String summarizedText, LocalDateTime timestamp) {
        this.id = id;
        this.originalText = originalText;
        this.summarizedText = summarizedText;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getSummarizedText() {
        return summarizedText;
    }

    public void setSummarizedText(String summarizedText) {
        this.summarizedText = summarizedText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", originalText='" + originalText + '\'' +
                ", summarizedText='" + summarizedText + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
