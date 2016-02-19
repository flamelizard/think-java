package com.gridworld;

/**
 * Created by Tom on 10/16/2015.
 */
public class SimpleMath {
    public static void main(String[] args) {
        int a = 25;
        int b = 5;
        if (isDivisible(a, b)) {
            System.out.println(a + " is divisible by " + b);
        }

        double res = multiAdd(2, 8, 3);
        System.out.println(res);

//        complex math expression: sinPi/4 + (cosPi/4)/2
        double z = Math.sin(Math.PI/4);
        double x = Math.cos(Math.PI/4);
        double y = 1/2;
        System.out.println(multiAdd(x, y, z));

        multiTable();

        power_iterative(2, 256);
        System.out.println(power_recur(2, 0));

        mathLab();

    }

    public static boolean isDivisible(int n, int m) {
        return n%m == 0;
    }

    public static double multiAdd(double a, double b, double c) {
        return a * b + c;
    }

    public static void multiTable() {
        System.out.println("Multiplication table for number 2");
        int i = 1;
        while (i <= 50) {
            System.out.printf("%-2d ", 2 * i);  // left-justified
            if ( i%5 == 0 && i != 0) {
                System.out.println();
            }
            i++;
        }
    }

    public static int power_iterative(int x, int n) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += x * x;
        }
        System.out.println(x + " to the power of " + n + " is " + res);
        return res;
    }

    public static int power_recur(int x, int n) {
//        nice, works like a charm
        if (n == 0) {
            return 1;
        }
        return x * power_recur(x, n-1);
    }

    public static void mathLab() {
        System.out.println("Meth lab ;-)");
        int a = 60;
        System.out.println(((int) 0.6 * 60));
    }



}
