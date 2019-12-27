package ie.gmit.dip;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ie.gmit.dip.GoogleDictionary.getGoogleDictionary;

public class GoogleWordConverter extends KeyListener {
    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    public void initialize() {
        GoogleDictionary googleDictionary = new GoogleDictionary();
        googleDictionary.createDictionary();
        googleDictionary.addWordsToDictionary();
        addKeyListener();
    }

    public static String getGoogleEquivalent(String word) {
        if (getGoogleDictionary().containsKey(word)) {
            return ConsoleColour.GREEN_BOLD + getGoogleDictionary().get(word);
        } else {
            return ConsoleColour.RED_BOLD + word;
        }
    }

    @Override
    public void addKeyListener() {
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
}
