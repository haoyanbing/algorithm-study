package com.haoyanbing.algorithm.fib;

public class FibTest {
    public static void main(String[] args) {
        System.out.println(fib(29));
    }

    /**
     * 动态规划
     */
    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int n1 = 0;
        int n2 = 1;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum = (n1 + n2);
            n1 = n2;
            n2 = sum;
        }
        return sum;
    }
}
