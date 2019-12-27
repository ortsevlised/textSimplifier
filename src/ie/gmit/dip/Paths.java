package ie.gmit.dip;

import java.util.HashMap;
import java.util.Scanner;

public class Paths {
    private static final Scanner in = new Scanner(System.in);
    public static final String GOOGLE_1000_WORDS = "Google 1000 words";
    public static final String MOBY_THESAURUS = "Moby Thesaurus";

    public static String getGooglePath() {
        return map.get(GOOGLE_1000_WORDS);
    }

    public static String getMobyThesourusPath() {
        return map.get(MOBY_THESAURUS);
    }

    private static final HashMap<String, String> map = new HashMap<>();

    public static void setPaths() {
        System.out.println("Enter Path to the Google 1000 words >");
        map.put(GOOGLE_1000_WORDS, checkIsNotEmpty(in));
        System.out.println("Enter Path to the Moby Thesaurus >");
        map.put(MOBY_THESAURUS, checkIsNotEmpty(in));
    }

    public static String checkIsNotEmpty(Scanner in) {
        String text = in.nextLine();
        while (text.length() < 1) {
            System.out.println("Path cannot be empty, please enter a valid path >>>");
            text = in.nextLine();
        }
        return text;
    }
}
