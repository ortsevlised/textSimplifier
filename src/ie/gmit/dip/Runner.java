package ie.gmit.dip;

/**
 * @author Jorge Desilvestro
 * @version 0.0.1
 * @since 1.8
 *
 * Runner class for the Text Simplifier application
 */
public class Runner {

	public static void main(String[] args) {
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.0.1              *");
		System.out.println("*       (AKA Orwellian Language Compliance)       *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
		TextSimplifier.initialise();
		System.out.println(ConsoleColour.RESET);
	}

}