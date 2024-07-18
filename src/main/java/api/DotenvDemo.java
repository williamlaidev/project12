package api;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Demonstrates loading and using environment variables in a Java application with Dotenv.
 * Ensure 'MY_API_KEY' is defined in the .env file before running this class.
 */
public class DotenvDemo {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("MY_API_KEY");
        System.out.println(apiKey);
    }
}
