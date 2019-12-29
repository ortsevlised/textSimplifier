package ie.gmit.dip;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ie.gmit.dip.DictionarySources.getGoogle1000Words;
import static ie.gmit.dip.DictionarySources.getMobyThesourusWords;
import static org.jnativehook.GlobalScreen.unregisterNativeHook;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * <p>
 * Represents the TextSimplifier
 * Initialises the Dictionary and adds a key listener to capture user's input
 */
public class TextSimplifier implements NativeKeyListener {
    private static StringBuilder word = new StringBuilder();
    private StringBuilder swappedWords = new StringBuilder().append("\r");
    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    private static GoogleDictionaryImp googleDictionaryImp = new GoogleDictionaryImp();

    /**
     * Initialises the Text Simplifier:
     * Creates an instance of the Google Dictionary implementation
     * Sets the paths to the google and moby thesourus
     * Adds google and moby words to the dictionary
     * Adds a listener to capture user input
     */
    public static void initialise() {
        GoogleDictionaryImp googleDictionaryImp = new GoogleDictionaryImp();
        DictionarySources.setPathsFromUserInput();
        googleDictionaryImp.create(getGoogle1000Words());
        googleDictionaryImp.addWords(getMobyThesourusWords());
        addListener(new TextSimplifier());
    }

    /**
     * Adds a listener and register the hook
     *
     * @param nativeKeyListener the NativeKeyListener object where the listener will be added
     */
    private static void addListener(NativeKeyListener nativeKeyListener) {
        logger.setLevel(Level.OFF);
        GlobalScreen.addNativeKeyListener(nativeKeyListener);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    /**
     * Defines what happens on key press event
     * On ESCAPE the application will stop
     * On SPACE will convert the word into the google equivalent word
     * On ENTER will convert the word into the google equivalent one and will continue to next line
     * @param e the NativeKeyEvent
     */
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_ESCAPE:
                try {
                    System.out.printf("%n%s%s", ConsoleColour.RESET, "Bye...");
                    unregisterNativeHook();
                } catch (NativeHookException ex) {
                    ex.printStackTrace();
                }
                break;
            case NativeKeyEvent.VC_SPACE:
                System.out.print(swappedWords.append(getWordFromDictionary()).append(" ").toString());
                word.setLength(0);
                break;
            case NativeKeyEvent.VC_ENTER:
                System.out.print(swappedWords.append(getWordFromDictionary()).toString());
                word.setLength(0);
                swappedWords.setLength(0);
                swappedWords.append("\r");
                break;
        }
    }

    private String getWordFromDictionary() {
        return googleDictionaryImp.getWordEquivalent(word.toString().trim().toLowerCase());
    }

    /**
     * Defines what happens on Key Typed event
     * Not necessary for this project
     *
     * @param e the NativeKeyEvent
     */
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    /**
     * Defines what happens on Key Typed event
     * Appends each key typed by the user other than backspace to an String builder
     * if the key typed is BACKSPACE it will remove the last appended char
     *
     * @param e the NativeKeyEvent
     */
    public void nativeKeyTyped(NativeKeyEvent e) {
        if (e.getKeyChar() == '\b' && word.length() > 0) {
            word.deleteCharAt(word.length() - 1);
        } else if (e.getKeyChar() != '\b') {
            word.append(e.getKeyChar());
        }

    }
}
