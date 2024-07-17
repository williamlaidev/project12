package api;

import java.io.*;

/**
 * This class demonstrates how to execute a Python script that accesses Gemini API using ProcessBuilder in Java.
 */
public class PythonAPIDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Define the path to your Python script
        String pythonScriptPath = "src/main/java/api/PythonAPIDemo.py"; // Replace with your Python script path

        // Define the path to your Python interpreter
        String pythonCommand = "/home/user/java101/project12/myvenv/bin/python"; // Replace with your Python interpreter path

        // Set up the ProcessBuilder with the Python command and script path
        ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, pythonScriptPath);

        // Redirect error stream to output stream
        processBuilder.redirectErrorStream(true);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read output from Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the script to finish and get the exit code
            int exitCode = process.waitFor();
            System.out.println("Python script execution completed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
