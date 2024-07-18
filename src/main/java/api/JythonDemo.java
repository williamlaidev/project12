package api;

import org.python.util.PythonInterpreter;

/**
 * This class demonstrates how to integrate Jython to execute Python code within a Java application.
 */
public class JythonDemo {

    public static void main(String[] args) {
        try {
            // Initialize PythonInterpreter
            PythonInterpreter pythonInterpreter = new PythonInterpreter();

            // Execute a Python script
            pythonInterpreter.execfile("src/main/java/api/PythonDemo.py"); // Replace with the path to your Python script

            // Close the interpreter
            pythonInterpreter.close();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
