package com.javaalgorithm.example.chapter5.binarysearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex29 {
    private static int n = 0;
    private static int[] alist;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/binarysearch/params/ex29.txt"));

        n = Integer.parseInt(lines.get(0));
        alist = Arrays.stream(lines.get(1).split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();
        int mCount = Integer.parseInt(lines.get(2));
        int[] mlist = Arrays.stream(lines.get(3).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        for (int m : mlist) {
            int midIndex = n / 2;

            binarySearch(m, midIndex);
        }
    }

    private static void binarySearch(int m, int midIndex) {
        if (m > alist[midIndex]) {
            // 오른쪽 탐색
            if (midIndex < n - 1) {
                binarySearch(m, (n + midIndex) / 2);
            } else {
                System.out.println(0);
            }
        } else if (m < alist[midIndex]) {
            // 왼쪽 탐색
            if (midIndex > 0) {
                binarySearch(m, (midIndex) / 2);
            } else {
                System.out.println(0);
            }
        } else {
            System.out.println(1);
        }
    }

}
