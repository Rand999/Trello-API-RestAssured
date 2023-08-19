package utility;

import java.util.Random;

public class utileties {

    public static String generateRandomLabelName(int length) {
        // Create a random number generator.
        Random random = new Random();

        // Create a StringBuilder to store the random string.
        StringBuilder sb = new StringBuilder();

        // Generate a random character from the alphabet for each character in the string.
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }

        // Return the random string.
        return sb.toString();
    }
}
