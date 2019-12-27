package ie.gmit.dip;

public class Runner {

	public static void main(String[] args) {
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.1                *");
		System.out.println("*       (AKA Orwellian Language Compliance)       *");		
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
		Paths.setPaths();
		System.out.println("Enter Text to simplify or press ESC to exit>");
		GoogleWordConverter.initialize();
		System.out.println(ConsoleColour.RESET);
	}
}