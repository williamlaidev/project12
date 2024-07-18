package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Java application to run a Python script using ProcessBuilder.
 * The Python script loads environment variables from a .env file using dotenv.
 * It prints the API key value if found.
 * Before running, ensure to update the API key in the .env file.
 */

public class PythonDotenvDemo {
    public static void main(String[] args) {
        try {
            String pythonScriptPath = "src/main/java/api/PythonDotenvDemo.py"; // Replace with your python script

            String pythonCommand = "/home/user/java101/project12/myvenv/bin/python"; // Replace with the path of python interpreter

            // Create ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder(Arrays.asList(pythonCommand, pythonScriptPath));

            // Start the process
            Process process = pb.start();

            // Capture output from Python script
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check the exit code
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
                System.out.println("Python script output:");
                System.out.println(output.toString()); // Print the captured output
            } else {
                System.err.println("Error executing Python script. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
