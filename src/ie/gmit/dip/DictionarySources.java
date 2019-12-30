package ie.gmit.dip;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 *
 * Represents the Source files for the dictionary,
 * asks the user for input and generates Streams with the source content
 */


public class DictionarySources {
    private static final Scanner in = new Scanner(System.in);
    private static Stream<String> mobyThesourusContent;
    private static Stream<String> google1000Content;

    public static Stream<String> getMobyThesourusWords() {
        return mobyThesourusContent;
    }
    public static Stream<String> getGoogle1000Words() {
        return google1000Content;
    }

    /**
     * Asks the user to enter the paths to the google 1000 and Moby thesaurus files
     */
    public static void setPathsFromUserInput() {
        System.out.println("Enter Path to the Google 1000 words >");
        google1000Content = getLines();
        System.out.println("Enter Path to the Moby Thesaurus >");
        mobyThesourusContent = getLines();
        // /Users/ortsevlised/IdeaProjects/textSimplifier/src/ie/gmit/dip/MobyThesaurus2.txt
    }

    /**
     * Converts the file into a stream
     *
     * @return a Stream of String
     * or RuntimeException if there's no a valid path
     */
    private static Stream<String> getLines() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(new File(checkIsNotEmpty(in))))).lines();
        } catch (FileNotFoundException e) {
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
}
