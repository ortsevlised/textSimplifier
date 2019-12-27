package ie.gmit.dip;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ie.gmit.dip.GoogleDictionary.getGoogleDictionary;
import static org.jnativehook.GlobalScreen.unregisterNativeHook;

public class GoogleWordConverter implements NativeKeyListener {
    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    private static StringBuilder word = new StringBuilder();
    private StringBuilder googleWord = new StringBuilder().append("\r");

    public static void initialize() {
        GoogleDictionary googleDictionary = new GoogleDictionary();
        googleDictionary.createDictionary();
        googleDictionary.addWordsToDictionary();
        addKeyListener();
    }

    private static void addKeyListener() {
        logger.setLevel(Level.OFF);
        GlobalScreen.addNativeKeyListener(new GoogleWordConverter());
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }

    private static String getGoogleEquivalent(String word) {
        if (getGoogleDictionary().containsKey(word)) {
            return ConsoleColour.GREEN_BOLD + getGoogleDictionary().get(word);
        } else {
            return ConsoleColour.RED_BOLD + word;
        }

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_ESCAPE:
                try {
                    unregisterNativeHook();
                    System.out.println(ConsoleColour.RESET);
                } catch (NativeHookException ex) {
                    ex.printStackTrace();
                }
                break;
            case NativeKeyEvent.VC_SPACE:
                System.out.print(googleWord.append(getGoogleEquivalent(word.toString().trim()) + " ").toString());
                word.setLength(0);
                break;
            case NativeKeyEvent.VC_ENTER:
                System.out.println(googleWord.append(getGoogleEquivalent(word.toString().trim().toLowerCase())).toString());
                word.setLength(0);
                googleWord.setLength(0);
                googleWord.append("\r");
                break;
            case NativeKeyEvent.VC_BACKSPACE:
                if (googleWord.length() > 0 && word.length() > 0) {
                    word.deleteCharAt(word.length() - 1);
                } else {
                    if (googleWord.length() > 7) {
                        // googleWord.setLength(googleWord.length() + word.length());
                        googleWord.deleteCharAt(googleWord.append(word).length() - 1);
                        if (googleWord.length() > 7) {
                            System.out.print(googleWord);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        if (e.getKeyChar() != '\b') {
            word.append(e.getKeyChar());
        }
    }
}
