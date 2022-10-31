package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex7 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex7.txt"));
        int n = Integer.parseInt(lines.get(0)); // 재로의 갯수
        int m = Integer.parseInt(lines.get(1)); // 재료의 합
        int[] sortedNums = Arrays.stream(lines.get(2).split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();

        int startIdx = 0;
        int endIdx = sortedNums.length - 1;
        int count = 0;

        while (startIdx < endIdx) {
            int n1 = sortedNums[startIdx];
            int n2 = sortedNums[endIdx];

            int sum = n1 + n2;

            if (sum < m) {
                startIdx++;
            } else if (sum > m) {
                endIdx--;
            } else {
                endIdx--;
                count++;
            }
        }
        System.out.println(count);
    }
}
