package com.gridworld;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 12/13/2015.
 */
public class MyUtils {
    public static void printArray(int[] vals) {
        List<Integer> list = new ArrayList<>();
        for (int val: vals) {
            list.add(val);
        }
        System.out.println(list.toString());
    }

//    Locate resources like files anywhere in class structure, very handy
//    Just provide filename and it will return full path or null
    public static URL getResourcePath(String s) {
        return MyUtils.class.getResource(s);
    }
}
