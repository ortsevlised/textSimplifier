package ie.gmit.dip;

import java.io.*;
import java.util.*;

public class basicFunctionality {

    private static Map<String, String> map = new HashMap<>();
    private static Set<String> set = new HashSet<>();


    //mapp of the google 100 words to a thersarus map
    //if the word does exist ignore/
    //add key listener to swap words
    // add
    private static final String googleWordFile = "C:\\Users\\Jorge\\Desktop\\gmit\\Text Simplifier API\\google-1000\\google-1000.txt";//ui ask the user for the file
    private static final String thesaurusFile = "C:\\Users\\Jorge\\Desktop\\gmit\\Text Simplifier API\\MobyThesaurus2\\MobyThesaurus2.txt";

    //get line
    // split by comma
    // check in the google 100 list and map or ignore it


    public static void main(String[] args) throws Exception {
        addGoogleWordsToMap();
        addMobyThesaurus();
        System.out.println("akak");
    }
    public static void addGoogleWordsToMap() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordFile))));
        //String line;
       // while ((line = br.readLine()) != null) {
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            map.put(line, line);
            set.add(line);
        }
    }

    public static void addMobyThesaurus() throws IOException {
        BufferedReader brThesaurus = new BufferedReader(new InputStreamReader(new FileInputStream(new File(thesaurusFile))));
        String line;
        while ((line = brThesaurus.readLine()) != null) {
            String[] words = line.split(",");
            String googleWord = null;

            for (String word : words) {
                if (set.contains(word)) {
                    googleWord = word;
                    break;
                }
            }
            if (googleWord != null) {
                addAll(words, googleWord);
            }
        }
    }


    private static void addAll(String[] words, String googleWord) {
        for (String word : words) {
            map.put(word, googleWord);
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
