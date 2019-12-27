package ie.gmit.dip;

import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import static ie.gmit.dip.GoogleWordConverter.getGoogleEquivalent;
import static org.jnativehook.GlobalScreen.unregisterNativeHook;

public abstract class KeyListener implements NativeKeyListener {
    private static StringBuilder word = new StringBuilder();
    private StringBuilder googleWord = new StringBuilder().append("\r");

    public abstract void addKeyListener();

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_ESCAPE:
                try {
                    System.out.printf("%s%s", ConsoleColour.RESET, "Bye...");
                    unregisterNativeHook();
                } catch (NativeHookException ex) {
                    ex.printStackTrace();
                }
                break;
            case NativeKeyEvent.VC_SPACE:
                System.out.print(googleWord.append(getGoogleEquivalent(word.toString().trim()) + " ").toString());
                word.setLength(0);
                break;
            case NativeKeyEvent.VC_ENTER:
                System.out.print(googleWord.append(getGoogleEquivalent(word.toString().trim().toLowerCase())).toString());
                word.setLength(0);
                googleWord.setLength(0);
                googleWord.append("\r");
                break;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        if (e.getKeyChar() == '\b' && word.length() > 0) {
            word.deleteCharAt(word.length() - 1);
        } else if (e.getKeyChar() != '\b') {
            word.append(e.getKeyChar());
        }

    }
}
