package ie.gmit.dip;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * <p>
 *
 * Colours for the console
 */
public enum ConsoleColour {
    RESET("\033[0m"),
    CLEAR( "\033[H\033[2J"),
    BLACK_BOLD("\033[1;30m"),
    RED_BOLD("\033[1;31m"),
    BLUE_BRIGHT("\033[0;94m");

	private final String colour;
	ConsoleColour(String colour) {
		this.colour = colour;
	}

    public String toString() {
        return colour;
    }
}
