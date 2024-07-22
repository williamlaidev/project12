package framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the SummarizationOutputHandler interface.
 * This class filters and stores output lines.
 * It also handles errors by printing them to the standard error stream.
 */
public class SummarizationOutputHandlerImpl implements SummarizationOutputHandler {
    private final List<String> filteredOutput = new ArrayList<>();

    /**
     * Filters and stores the output string.
     *
     * @param output the output string to be handled
     */
    @Override
    public void handleOutput(String output) {
        if (!output.startsWith("WARNING:") && !output.startsWith("I")) {
            filteredOutput.add(output);
        }
    }

    /**
     * Prints the provided error string to the standard error stream.
     *
     * @param error the error string to be printed
     */
    @Override
    public void handleError(String error) {
        System.err.println(error);
    }

    /**
     * Prints all stored filtered output lines to the standard output stream.
     */
    public void printFilteredOutput() {
        for (String line : filteredOutput) {
            System.out.println(line);
        }
    }
}