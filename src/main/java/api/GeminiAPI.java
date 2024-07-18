package api;

public interface GeminiAPI {
    String summarizeText(String inputString) throws InterruptedException;
}
