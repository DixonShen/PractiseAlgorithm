package CodeWars;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dixonshen on 2016/10/12.
 */
public class Mod {

    public static Pattern mod4 = Pattern.compile(".*\\[[+-]?([048]|\\d*([02468][048]|[13579][26]))\\]");

    public static void main(String[] args) {
        String[] validTests = {"[+05620]", "[005624]", "[-05628]", "[005632]", "[555636]", "[+05640]", "[005600]",
                "the beginning [-0] the end", "~[4]", "[32]", "the beginning [0] ... [invalid] numb[3]rs ... the end",
                "...may be [+002016] will be."};
        for(String test : validTests) {
            Matcher m = Mod.mod4.matcher(test);
            System.out.println(m.find());
        }

        String[] invalidTests = {"[+05621]", "[-55622]", "[005623]", "[~24]", "[8.04]", "No, [2014] isn't a multiple of 4..."};
        for(String test : invalidTests) {
            Matcher m = Mod.mod4.matcher(test);
            System.out.println(m.find());
        }

        String test = "[555636]";
        Matcher m = mod4.matcher(test);
        System.out.println(m.find());
    }
}
