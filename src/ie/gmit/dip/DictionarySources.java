package ie.gmit.dip;

import java.util.stream.Stream;

import static ie.gmit.dip.TextUtils.getLines;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * @since 1.8
 *
 * Represents the Source files for the dictionary,
 * asks the user for input and generates Streams with the source content
 */


public class DictionarySources {
    private static Stream<String> mobyThesourusContent;
    private static Stream<String> google1000Content;

    public static Stream<String> getMobyThesourusWords() {
        return mobyThesourusContent;
    }
    public static Stream<String> getGoogle1000Words() {
        return google1000Content;
    }

    /**
     * Asks the user to enter the paths to the google 1000 words and Moby thesaurus files
     */
    public static void setPathsFromUserInput() {
        System.out.println("Enter Path to the Google 1000 words >");
        google1000Content = getLines();
        System.out.println("Enter Path to the Moby Thesaurus >");
        mobyThesourusContent = getLines();
    }
}
