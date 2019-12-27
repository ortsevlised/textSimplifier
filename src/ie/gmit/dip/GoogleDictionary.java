package ie.gmit.dip;

import java.io.*;
import java.util.*;

import static ie.gmit.dip.Paths.getGooglePath;
import static ie.gmit.dip.Paths.getMobyThesourusPath;

public class GoogleDictionary implements Dictionary {

    private static Map<String, String> googleDictionary = new HashMap<>();
    private static Set<String> set = new HashSet<>();
// /Users/ortsevlised/IdeaProjects/textSimplifier/src/ie/gmit/dip/google-1000.txt
    // /Users/ortsevlised/IdeaProjects/textSimplifier/src/ie/gmit/dip/MobyThesaurus2.txt


    public static Map<String, String> getGoogleDictionary() {
        return googleDictionary;
    }

    @Override
    public void createDictionary() {
        try {
      new BufferedReader(new InputStreamReader(new FileInputStream(new File(getGooglePath())))).lines()
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
            new BufferedReader(new InputStreamReader(new FileInputStream(new File(getMobyThesourusPath())))).lines()
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
