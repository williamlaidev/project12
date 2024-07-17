package entity;

import java.time.LocalDateTime;

/**
 * Represents a text summary.
 *
 * @since 1.8
 */
public class Summary {
    private int id;
    private String originalText;
    private String summarizedText;
    private LocalDateTime timestamp;

    /**
     * Default constructor.
     *
     * @since 1.8
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
     *
     * @since 1.8
     */
    public Summary(int id, String originalText, String summarizedText, LocalDateTime timestamp) {
        this.id = id;
        this.originalText = originalText;
        this.summarizedText = summarizedText;
        this.timestamp = timestamp;
    }

    /**
     * Gets the unique identifier of the summary.
     *
     * @return the unique identifier of the summary
     *
     * @since 1.8
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the summary.
     *
     * @param id the unique identifier of the summary
     *
     * @since 1.8
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the original text to be summarized.
     *
     * @return the original text to be summarized
     *
     * @since 1.8
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Sets the original text to be summarized.
     *
     * @param originalText the original text to be summarized
     *
     * @since 1.8
     */
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * Gets the summarized text.
     *
     * @return the summarized text
     *
     * @since 1.8
     */
    public String getSummarizedText() {
        return summarizedText;
    }

    /**
     * Sets the summarized text.
     *
     * @param summarizedText the summarized text
     *
     * @since 1.8
     */
    public void setSummarizedText(String summarizedText) {
        this.summarizedText = summarizedText;
    }

    /**
     * Gets the timestamp when the summary was created.
     *
     * @return the timestamp when the summary was created
     *
     * @since 1.8
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the summary was created.
     *
     * @param timestamp the timestamp when the summary was created
     *
     * @since 1.8
     */
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
