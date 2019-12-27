package ie.gmit.dip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Paths {
    private static final Scanner in = new Scanner(System.in);
    private static final HashMap map = new HashMap<>();

    public static Map<String, String> getPaths() {
        System.out.println("Enter Path to the Google 1000 words >");
        map.put("Google 1000 words",  in.nextLine());
        System.out.println("Enter Path to the Moby Thesaurus >");
        map.put("Moby Thesaurus", in.nextLine());
        return map;
    }

}
