package ie.gmit.dip;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class basicFunctionality {

    private Map<String, String> map = new TreeMap<>();
    private Set<String> set = new TreeSet<>();



    //mapp of the google 100 words to a thersarus map
    //if the word does exist ignore/
    //add key listener to swap words

    private static final String googleWordFile = null;//ui ask the user for the file
    private static final String thesaurusFile = null;

    public void getLinesFromThesauros() throws IOException {
        //get line
        // split by comma
        // check in the google 100 list and map or ignore it
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordFile))));

        String line = null;

        while ((line = br.readLine()) != null) {
            map.put(line, line);
        }

        BufferedReader brThesaurus = new BufferedReader(new InputStreamReader(new FileInputStream(new File(thesaurusFile))));

        while ((line = br.readLine()) != null) {
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
            map.put(line, line);
        }
    }

    private void addAll(String[] words, String googleWord) {
        for (String word : words) {
            map.put(word, googleWord);
        }
    }

    public String getGoogleEquivalent(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        } else {
            return word;
        }

    }
}
