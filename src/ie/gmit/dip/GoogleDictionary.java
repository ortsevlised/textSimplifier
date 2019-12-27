package ie.gmit.dip;

import java.io.*;
import java.util.*;

public class GoogleDictionary extends Paths implements Parser {

    private static Map<String, String> googleDictionary = new HashMap<>();
    private static Set<String> set = new HashSet<>();
    private static final String googleWordFile = "/Users/ortsevlised/IdeaProjects/textSimplifier/src/ie/gmit/dip/resources/google-1000.txt";//ui ask the user for the file
    private static final String thesaurusFile = "/Users/ortsevlised/IdeaProjects/textSimplifier/src/ie/gmit/dip/resources/MobyThesaurus2.txt";


    public static Map<String, String> getGoogleDictionary() {
        return googleDictionary;
    }

    @Override
    public void createDictionary() {
        try {
            new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordFile)))).lines()
                    .forEach(line -> {
                        googleDictionary.put(line, line);
                        set.add(line);
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addWordsToDictionary() {
        try {
            new BufferedReader(new InputStreamReader(new FileInputStream(new File(thesaurusFile)))).lines()
                    .forEach(line -> {
                        List<String> words = Arrays.asList((line).split(","));
                        Optional<String> googleWord = words.stream().filter(word -> set.contains(word)).findFirst();

                        if (googleWord.isPresent()) {
                            addAll(words, googleWord);
                        }
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void addAll(List<String> words, Optional<String> googleWord) {
        for (String word : words) {
            googleDictionary.put(word, googleWord.get());
        }
    }
}
