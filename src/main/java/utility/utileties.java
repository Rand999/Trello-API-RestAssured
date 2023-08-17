package utility;

import java.util.Random;

public class utileties {

    public static String generateRandomLabelName() {
        // Create a list of all possible characters for the label name.
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create a random number generator.
        Random random = new Random();

        // Generate a random string of length 8.
        String randomString = "";
        for (int i = 0; i < 8; i++) {
            randomString += characters.charAt(random.nextInt(characters.length()));
        }

        // Return the random string.
        return randomString;
    }
}
