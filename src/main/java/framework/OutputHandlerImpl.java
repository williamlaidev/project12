package framework;

import java.util.ArrayList;
import java.util.List;

public class OutputHandlerImpl implements OutputHandler {
    private final List<String> filteredOutput = new ArrayList<>();

    @Override
    public void handleOutput(String output) {
        // Filter out unwanted lines
        if (!output.startsWith("WARNING:") && !output.startsWith("I")) {
            filteredOutput.add(output); // Store the filtered output
        }
    }

    @Override
    public void handleError(String error) {
        System.err.println(error); // Print errors to the console
    }

    // Method to print the filtered output
    public void printFilteredOutput() {
        for (String line : filteredOutput) {
            System.out.println(line);
        }
    }
}
