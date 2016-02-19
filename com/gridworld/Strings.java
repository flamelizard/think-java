package com.gridworld;

/**
 * Created by Tom on 10/17/2015.
 */
public class Strings {
    public static void main(String[] args) {
        printStrings("baywatch");
        printStrings(reverseString("diesel"));
        countLetter("galaxy", 'a');
        stringLab();
    }

    public static void printStrings(String s) {
//       print one char per a line
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            System.out.println(c);
        }
    }

    public static String reverseString(String s) {
        String rever = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rever += s.charAt(i);
        }
        return rever;
    }

    public static void countLetter(String s, char ltr) {
        int cnt = 0;
        int idx = 0;
        while (true) {
            idx = s.indexOf(ltr, idx);
//            echo(idx);
            if (idx == -1) {
                break;
            }
            idx++;
            cnt++;
        }
        System.out.println("Letter " + ltr + " found in " + cnt + " times in " + s);
    }

    public static void stringLab() {
        char a = 'a';
        char b = 'b';
        echo(a);
        echo(a + b);
        System.out.println(a + b);  //char concat wil sum their ASCII values

        //double quotes below is legal way how to force compiler to print char instead of its ASCII value
        echo("" + ++a);     //incrementing char will move it on lexically, a->b

//        print integer backwards
        int number = 17;
        int lastDigit = number % 10;
        int firstDigit = number / 10;
        String res = String.valueOf(lastDigit) + String.valueOf(firstDigit);
        System.out.println(res);

//        Ways of converting int to String
        int i = 10;
        String s1, s2, s3;
        s1 = String.valueOf(i);     //.valueOf is overloaded method supporting various var types
        s2 = Integer.toString(i);
        s3 = "" + i;     //not recommended, not a best practice
        echo(s1 + " " + s2 + " " + s3);
    }

    //    Note - overloaded methods with different parameters
    public static void echo(String s) {
        System.out.println(s);
    }

    public static void echo(int i) {
        System.out.println(i);
    }
}


