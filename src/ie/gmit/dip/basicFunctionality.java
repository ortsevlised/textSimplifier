package ie.gmit.dip;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class basicFunctionality {

    private static Map<String, String> map = new HashMap<>();
    private static Set<String> set = new HashSet<>();


    //mapp of the google 100 words to a thersarus map
    //if the word does exist ignore/
    //add key listener to swap words
    // add
    private static final String googleWordFile = "/Users/ortsevlised/IdeaProjects/textSimplifier/src/google-1000.txt";//ui ask the user for the file
    private static final String thesaurusFile = "/Users/ortsevlised/IdeaProjects/textSimplifier/src/MobyThesaurus2.txt";

    //get line
    // split by comma
    // check in the google 100 list and map or ignore it


    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();
        addGoogleWordsToMap();
        addMobyThesaurus();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println(timeElapsed);

    }
    public static void addGoogleWordsToMap() throws IOException {
        new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordFile)))).lines()
                .forEach(line -> {
                    map.put(line, line);
                    set.add(line);
                });
    }

    public static void addMobyThesaurus() throws IOException {
        new BufferedReader(new InputStreamReader(new FileInputStream(new File(thesaurusFile)))).lines()
                .forEach(line -> {
                    List<String> words = Arrays.asList((line).split(","));
                    Optional<String> googleWord = words.stream().filter(word -> set.contains(word)).findFirst();

                    if (googleWord.isPresent()) {
                        addAll(words, googleWord);
                    }
                });
    }


    private static void addAll(List<String> words, Optional<String> googleWord) {
        for (String word : words) {
            map.put(word, googleWord.get());
        }
    }

    public static String getGoogleEquivalent(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        } else {
            return word;
        }

    }
}
