package com.javaalgorithm.example.chapter5.dfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class Ex24 {
    private static int minNum = 0;
    private static LinkedList<Integer> results = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Integer n = Files.lines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/dfs/params/ex24.txt")).findFirst()
                .map(Integer::valueOf)
                .orElse(0);

        minNum = (int) Math.pow(10, n - 1);

        if (minNum < 10) {
            System.out.println(2);
            System.out.println(3);
            System.out.println(5);
            System.out.println(7);
        }

        for (int j = 0; j < 10; j++) {
            dfs(2, j);
        }
        for (int j = 0; j < 10; j++) {
            dfs(3, j);
        }
        for (int j = 0; j < 10; j++) {
            dfs(5, j);
        }
        for (int j = 0; j < 10; j++) {
            dfs(7, j);
        }

        results.forEach(System.out::println);
    }

    private static void dfs(int num1, int num2) {
        int targetNum = (num1 * 10) + num2;

        if (!isPrime(targetNum)) {
            return;
        }
        if (isPrime(targetNum) && targetNum > minNum) {
            results.add(targetNum);
            return;
        }
        for (int j = 0; j < 10; j++) {
            dfs(targetNum, j);
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
               return false;
            }
        }
        return true;
    }
}
