package com.gridworld;

//import java.util.Random;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by Tom on 10/19/2015.
 */

public class ArraysLab {
    public static void main(String[] args) {
        double[] res;

        res = randomNumbers(5);
        printArray(res);

        System.out.println("Random Array");
        printArray(randomArray(10));

        System.out.println("Max in range");
        int[] numbers = {8, 25, 11, 56, 26,78, 2, 29};
        int max = maxInRange(numbers, 45, 77);
        System.out.println("Max = " + max);

//        System.out.println("List Lab");
//        listLab();

//        Populate array from list of items
        ArrayList<Integer> nums = new ArrayList<>(asList(-20, -8, 0, 4, 45, 12, 4, 99, 2));
        System.out.println("Max in Range with Arraylist/List");
        Integer max2 = maxInRange(nums, -8, 5);
        System.out.println(max2);

        System.out.println(max(numbers));

        int[] hist = letterHist("Jamaica");
        MyUtils.printArray(hist);
    }

    public static double[] randomNumbers(int size) {
        double[] nums;
//        init new array to size
        nums = new double[size];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.random() * 100;
        }
        return nums;
    }

    public static void printArray(double[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//        Java for-each loop construct - preferred
        for (double item: arr) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

//    Overloaded method with different "parameter"
//    parameter is part of method signature or declaration
//    argument is actual value of this variable passed in to the function
    public static void printArray(int[] arr) {
        for (int item: arr) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    public static int randomInt(int low, int high) {
        if (low >= high) {
            System.out.println("Invalid bounds");
            return 0;
        }
        double random = Math.random();
        int diff = high - low;
        return (int) (low + (diff * random));
    }

    public static int[] randomArray(int size) {
        int[] nums = new int[size];
        for(int i = 0; i < nums.length; i++) {
//            nums[i] = randomInt(0, 100);
            nums[i] = betterRandom(0, 100);
        }
        return nums;
    }

    public static int betterRandom(int low, int high) {
//        Using java.util.Random is preferred way over Math.random
        Random rand = new Random();
//        Common way of moving random number to given range
        int range = (high - low) + 1;
        return rand.nextInt(range) + low;
    }

//    maxInRange(numbers, 20, 50)
//    recursive version of finding max number in given range
/*
    Very simple algorithm
    For each recursion, increment low range border and check which values are in.
    Repeat until low equals high. Yep, this is flawed and needs to handle some
    specific cases
    - better version way below
 */

    public static int maxInRange(int[] nums, int low, int high) {
        int[] _nums = new int[nums.length];
        int i = 0;
        if (low == high) {
            return nums[0];
        }
        for (int num: nums) {
            if (num >= low && num <= high) {
                _nums[i] = num;
                i++;
            }
        }
//        printArray(_nums);
        return maxInRange(_nums, low+1, high);
    }

    public static void listLab() {
        List<Integer> lst = java.util.Arrays.asList(1, 2, 3);
//        Adding to List will trigger Unsupported Exception since List is immutable
//        lst.add(4);
        System.out.println(lst);
    }

    /*
    Find max value in given range
    - recursive func
    - returns null for no value found
    - can handle negatives, duplicates, not continuous range
    - no optimization for very wide or very small range, step set to 1
     */
    public static Integer maxInRange(ArrayList<Integer> nums, int low, int high) {
        ArrayList<Integer> nums_in_range = new ArrayList<>();
        HashSet<Integer> nums_set = new HashSet<>(nums);    // remove duplicates

        int step = 1;
//        early exit
        if (nums.isEmpty()) {
            return null;
        }
        for (Integer num: nums_set) {
            if (num >= low && num <= high) {
                nums_in_range.add(num);
            }
        }
        Collections.sort(nums_in_range);

        if (low >= high || nums_in_range.size() == 1) {
            if (nums_in_range.isEmpty()) {
                return null;
            }
//          more numbers in range for a big step
            return nums_in_range.get(0);
        }
//        System.out.println("debug: " + nums_in_range + "," + low + "-" + high);
        return maxInRange(nums_in_range, low+step, high);
    }

    public static Integer max(int[] nums) {
//        Wrapper class to maxInRange
        /*
        Convert to generics (Integer, Float, ArrayList etc.)
        Autocast by compiler called autoboxing
        https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html
        */
        ArrayList<Integer> _nums = new ArrayList<>();
        for (int num: nums) {
            _nums.add(num);
        }
        Collections.sort(_nums);

        System.out.println(_nums.toString());
        return maxInRange(_nums, _nums.get(0), _nums.get(_nums.size()-1));
    }

    public static int[] letterHist(String word) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        int[] hist = new int[letters.length()];
        char c;
        int c_idx;

        for (int i = 0; i < word.length(); i++) {
            c = word.toLowerCase().charAt(i);
            c_idx = letters.indexOf(c);
            if (c_idx == -1) {
                throw new IndexOutOfBoundsException("Character not found in alphabet");
            }
            hist[c_idx]++;
        }
        return hist;
    }

    // split primitive array type
    //        int[] _n1 = Arrays.copyOfRange(nums, 0, nums.length/2);
    //        int[] _n2 = Arrays.copyOfRange(nums, nums.length/2+1, nums.length);
}

