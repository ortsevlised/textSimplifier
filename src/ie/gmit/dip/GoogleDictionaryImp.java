package ie.gmit.dip;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * <p>
 * Implements the Dictionary Interface to provide a GoogleDictionary
 */
public class GoogleDictionaryImp implements Dictionary {

    private static Map<String, String> googleDictionary = new HashMap<>();
    private static Set<String> set = new HashSet<>();

    /**
     * Creates the base Dictionary
     * it's a 1to1 mapping relationship between key and values
     * Creates a set with the list of the map keys
     *
     * @param lines each one of the lines of the dictionary source
     */
    public void create(Stream<String> lines) {
        lines.forEach(line -> {
            googleDictionary.put(line, line);
            set.add(line);
        });
    }

    /**
     * Adds values to the dictionary when one of the words in the line is on the set
     *
     * @param lines each one of the lines to check and add in case of match
     */
    public void addWords(Stream<String> lines) {
        lines.forEach(line -> {
            List<String> words = Arrays.asList((line).split(","));
            Optional<String> googleWord = words.stream().filter(word -> set.contains(word)).findFirst();
            if (googleWord.isPresent()) {
                addAll(words, googleWord);
            }
        });
    }

    /**
     * Gets the Google Word equivalent for the word entered
     *
     * @param word the word to use
     * @return a StringBuilder with a colour and the equivalent google word
     */
    public String getWordEquivalent(String word) {
        StringBuilder sb = new StringBuilder();
        if (googleDictionary.containsKey(word)) {
            return sb.append(ConsoleColour.GREEN_BOLD).append(googleDictionary.get(word)).toString();
        } else {
            return sb.append(ConsoleColour.RED_BOLD).append(word).toString();
        }
    }


    /**
     * Adds all the words passed to the google Dictionary
      * @param words the List of Strings
     * @param googleWord the google equivalent word
     */
    private void addAll(List<String> words, Optional<String> googleWord) {
        for (String word : words) {
            googleDictionary.put(word, googleWord.get());
        }
    }
}
