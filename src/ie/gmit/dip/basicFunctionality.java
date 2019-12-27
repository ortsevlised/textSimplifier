package ie.gmit.dip;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jnativehook.GlobalScreen.unregisterNativeHook;

public class basicFunctionality implements NativeKeyListener {

    private static Map<String, String> map = new HashMap<>();
    private static Set<String> set = new HashSet<>();
    private static StringBuilder word = new StringBuilder();
    StringBuilder googlWord = new StringBuilder().append("\r");

    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

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
        createTextOut();

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

    public static void createTextOut() {
        logger.setLevel(Level.OFF);

        GlobalScreen.addNativeKeyListener(new basicFunctionality());
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        System.out.println("Enter username");

    }


    public void nativeKeyPressed(NativeKeyEvent e) {
        // System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                unregisterNativeHook();
            } catch (NativeHookException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_SPACE || e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            System.out.print(googlWord.append(getGoogleEquivalent(word.toString().trim()) + " ").toString() + "\r");
            word.setLength(0);
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE && googlWord.length() > 0) {
            word.deleteCharAt(word.length() - 1);
            googlWord.deleteCharAt(googlWord.append(word).length() - 1);
            System.out.print(googlWord + "\r");
            word.setLength(0);
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
            word.append(e.getKeyChar());
        }

}
