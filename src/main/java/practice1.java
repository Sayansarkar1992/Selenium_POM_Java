import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class practice1 {

//    public static void main(String[] args) {
//        String str = "I have to send report to the team and check the CICD outputs.";
//        char input_char = 0;
//
//        Scanner sc = new Scanner(System.in);
//        String input = sc.next();
//        if(input.length() == 1) {
//            input_char = input.charAt(0);
//        }
//        else {
//            System.out.println("Please Enter only one character");
//            System.exit(0);
//        }
//        sc.close();
//        Map<Character, Integer> str_map = new HashMap<>();
//        for(int i=0 ; i<str.length(); i++) {
//            char c = str.charAt(i);
//            if (c != ' ') {
//                if (!str_map.containsKey(c)) {
//                    str_map.put(c, 1);
//                } else {
//                    int val = str_map.get(str.charAt(i));
//                    str_map.put(c, val + 1);
//                }
//            }
//        }
//
//        System.out.println(str_map.toString());
//
//        // int number_of_occurence = str_map.get(input_char);
//        StringBuilder stringBuilder = new StringBuilder(str);
//        int length = stringBuilder.length();
//        for(int i=stringBuilder.length()-1; i>=0; i--){
//            char c = str.charAt(i);
//            if(c == input_char){
//                stringBuilder.deleteCharAt(i);
//            }
//        }
//        System.out.println(stringBuilder);
//
//    }


    public static void main(String[] args) {
        String s1 = "Selenium";
        String s2 = "Playwright";

        s1 = s1 + s2; // SeleniumPlaywright

        s2 = s1.substring(0, s1.length() - s2.length());
        s1 = s1.substring(s2.length());
        System.out.println(s2);
        System.out.println(s1);
    }
}
