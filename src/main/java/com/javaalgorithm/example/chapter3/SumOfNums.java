package com.javaalgorithm.example.chapter3;

import java.util.Arrays;

public class SumOfNums {
    public static void main(String[] args) {
        String param1 = "11";
        String param2 = "10987654321";
        int numCount = Integer.parseInt(param1);
        Integer sum = Arrays.stream(param2.split(""))
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(sum);
    }
}
