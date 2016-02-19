package com.gridworld;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Tom on 2/17/2016.
 */
public class Cookbook {
    //    primitive type int will overflow at 15!, replace with BigInteger
    public static int factorial(int num) {
        if (num == 1 || num == 0) {
            return 1;
        }
        return num * factorial(num-1);
    }

    //    BigInteger does not support primitive math operators, use class methods like .add()
    public static BigInteger factorialBig(int num) {
        if (num == 0 || num == 1) {
            return BigInteger.valueOf(1);
        }
        return BigInteger.valueOf(num).multiply(factorialBig(num - 1));
    }

    public static String numberFormatter(BigInteger num) {
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    public static void fact_table(int high) {
        BigInteger res;
        for (int i = 1; i <= high; i++) {
//            res = factorial(i);
            res = factorialBig(i);
            System.out.printf("%2d! = %s", i, numberFormatter(res));
            System.out.println();
        }
    }

    //    Greatest Common Divider, used Euclidean algorithm
    public static int GCD(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        while (b != 0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    /*
Selection sort, inefficient algorithm, linear growth of order O(n2)

Find smallest item, swap it with the left most item and repeat on array
subset, skipping already sorted items
 */
    public static int[] selectionSort(int[] nums) {
        int leftmost, iMin;

//        loop for swaps
        for (int j = 0; j < nums.length - 1; j++) {
            iMin = j;
//            loop to find current smallest
            for (int i = j; i < nums.length; i++) {
                if (nums[iMin] > nums[i]) {
                    iMin = i;
                }
            }
//        swap current smallest with the left most item
            leftmost = nums[j];
            nums[j] = nums[iMin];
            nums[iMin] = leftmost;
        }
        return nums;
    }

    //    better alg with O(n log n), however needs two ordered subset
    public static int[] mergeSort(int[] n1, int[] n2) {
        int final_len = n1.length + n2.length;
        int[] res = new int[final_len];
        int j1 = 0;
        int j2 = 0;

        for (int i = 0; i < final_len; i++) {
//            System.out.println("[idx]" + j1 + j2);
            if (j1 > n1.length-1) {
                res[i] = n2[j2];
                j2++;
                continue;
            }

            if (j2 > n2.length-1) {
                res[i] = n1[j1];
                j1++;
                continue;
            }

            if (n1[j1] < n2[j2]) {
                res[i] = n1[j1];
                j1++;
            } else if (n1[j1] > n2[j2]) {
                res[i] = n2[j2];
                j2++;
            } else {
//                grab any item when equal
                res[i] = n1[j1];
                j1++;
            }
        }
        return res;
    }

    public static int[] mergeSortV2(int[] nums) {
        int[][] split = splitAndSort(nums);
//        MyUtils.printArray(split[0]);
//        MyUtils.printArray(split[1]);
        return mergeSort(split[0], split[1]);
    }

    public static int[][] splitAndSort(int[] nums) {

        int[] n1 = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] n2 = Arrays.copyOfRange(nums, nums.length/2+1, nums.length);

        int [][] res = new int[2][];
        res[0] = selectionSort(n1);
        res[1] = selectionSort(n2);

        return res;
    }

    public static int[] mergeSortRecur(int[] nums) {
        System.out.println("[arg] " + Arrays.toString(nums));
        if (nums.length == 0 || nums.length == 1) {
            System.out.println("[base] "+ Arrays.toString(nums));
            return nums;
        }
        int mid_point = nums.length/2;
//        System.out.println(mid_point);
        int[] first = Arrays.copyOfRange(nums, 0, mid_point);
        int[] second = Arrays.copyOfRange(nums, mid_point, nums.length);

        System.out.println("[halves] " + Arrays.toString(first) + Arrays
                .toString(second));
        return merge(mergeSortRecur(first), mergeSortRecur(second));
    }

    /**
     Merge for primitive type
     @param a sorted primitive int arrays
     */
    public static int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length+b.length];
        int i = 0;
        int j = 0;
        char pick = 'a';

        for (int z = 0; z < res.length; z++) {
            if (i >= a.length) {
                pick = 'b';
            } else if (j >= b.length) {
                pick = 'a';
            } else {
                if (a[i] > b[j] || a[i] == b[j]) {
                    pick = 'b';
                } else if (a[i] < b[j]) {
                    pick = 'a';
                }
            }
            switch (pick) {
                case 'a':
                    res[z] = a[i];
                    i++;
                    break;
                case 'b':
                    res[z] = b[j];
                    j++;
            }
        }
        System.out.println("[merged] " + Arrays.toString(res));
        return res;
    }
}
