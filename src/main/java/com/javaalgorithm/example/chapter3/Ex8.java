package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex8 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex8.txt"));
        int n = Integer.parseInt(lines.get(0)); // 수의 갯수
        int[] nums = Arrays.stream(lines.get(1).split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();
        int count = 0;

        for (int pivotIdx = nums.length - 1; pivotIdx > 0; pivotIdx--) {

            int pivotNum = nums[pivotIdx];
            int startIdx = 0;
            int endIdx = pivotIdx - 1;

            while (startIdx < endIdx) {

                int num1 = nums[startIdx];
                int num2 = nums[endIdx];
                int sumOfNum = num1 + num2;

                if (sumOfNum == pivotNum) {
                    count++;
                    break;
                } else if (sumOfNum < pivotNum) {
                    startIdx++;
                } else if (sumOfNum > pivotNum) {
                    endIdx--;
                }
            }
        }
        System.out.println(count);
    }
}
