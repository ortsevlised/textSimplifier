package ie.gmit.dip;

import java.util.stream.Stream;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * @since 1.8
 *
 * This interface defines the create, addWords and getWordEquivalent methods for the dictionary.
 */
public interface Dictionary {

    /**
     * Creates the base dictionary from the stream passed as argument
     * @param stream the source for the dictionary, keys and single values
     */
    void create(Stream<String> stream);

    /**
     * Adds the words from the stream to the dictionary
     * @param stream the source of words
     */
    void addWords(Stream<String> stream);

    /**
     * Retrieves an equivalent word for the parameter entered
     * @param word the word to use
     * @return an equivalent word
     */
    String getWordEquivalent(String word);
}
