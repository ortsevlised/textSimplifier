package ie.gmit.dip;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextUtils {
    /**
     * @author Jorge Desilvestro
     * @version 0.0.1
     * @since 1.8
     *
     * Helper class for input handling
     */
    private static Scanner in = new Scanner(System.in);

    /**
     * Converts the file into a stream
     *
     * @return a Stream of String
     * or RuntimeException if there's no a valid path
     */
    public static Stream<String> getLines() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(new File(checkIsNotEmpty(in))))).lines();
        } catch (FileNotFoundException e) {
            System.out.printf("%n%s", ConsoleColour.RESET);
            throw new RuntimeException(e.getMessage() + " Cannot continue without a valid path");
        }
    }

    /**
     * Makes sure the input is not empty
     *
     * @param in a scanner
     * @return a string to the path
     */
    private static String checkIsNotEmpty(Scanner in) {
        String text = in.nextLine();
        while (text.length() < 1) {
            System.out.println("Path cannot be empty, please enter a valid path >>>");
            text = in.nextLine();
        }
        return text;
    }

    /**
     * Acts as a wrapper for a known issue in the the listener library so it won't print to the users keys on background
     *
     */
    public static void redirectCapturedInput() {
        System.out.println("Enter Text to simplify or press ESC to exit>");
        Scanner scanner = new Scanner(System.in);
        String readString = scanner.next();
        while (readString != null) {
            if (scanner.hasNextLine()) {
                readString = scanner.nextLine();
            } else {
                readString = null;
            }
        }
    }
}
