package com.gridworld;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Tom on 1/13/2016.
 */
public class RegularExpr {
    public static void main(String[] args) {
        String text = "Today is wednesday January 13th";
        StringBuilder res = new StringBuilder();

        if ( Pattern.matches(".*\\d+.*", text) ) {
            System.out.println("quick pattern match on *whole string*");
        }

        Pattern vowels = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);
        Matcher match = vowels.matcher(text);

        System.out.println("Find each match");
        while( match.find() ) {
            System.out.println(match.group());
        }

        System.out.println("Find grouped pattern");
        match = Pattern.compile("(\\w+day)").matcher(text);
        while( match.find() ) {
            res.append(match.group(1));
        }
        System.out.println(res.toString());

//        Matcher has many useful methods

//        String has built-in support for RE in some methods
        System.out.println(text.matches("\\w+day.+"));

    }
}
