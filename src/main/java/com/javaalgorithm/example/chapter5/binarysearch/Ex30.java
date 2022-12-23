package com.javaalgorithm.example.chapter5.binarysearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex30 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/binarysearch/params/ex30.txt"));
        int[] firstLine = Arrays.stream(lines.get(0).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        int lessonCount = firstLine[0];
        int bluerayCount = firstLine[1];

        int[] secondLine = Arrays.stream(lines.get(1).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int sum = Arrays.stream(secondLine).sum();

        int result = binarySearch(bluerayCount, secondLine, sum, secondLine[lessonCount - 1], sum);
        System.out.println(result);
    }

    private static int binarySearch(int bluerayCount, int[] secondLine, int sum, int start, int end) {
        if (start > end) {
            return 0;
        }

        int currentBluelay = 0;
        int[] bluelays = new int[bluerayCount];
        int mid = (start + end) / 2;
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("mid: " + mid);

        for (int i = 0; i < secondLine.length; i++) {
            if (bluelays[currentBluelay] + secondLine[i] <= mid) {
                bluelays[currentBluelay] += secondLine[i];
            } else {
                currentBluelay++;
                if (currentBluelay >= bluerayCount) {
                    break;
                }
                bluelays[currentBluelay] += secondLine[i];
            }
        }
        System.out.println(currentBluelay);
        System.out.println(Arrays.toString(bluelays));

        int bluelaySum = Arrays.stream(bluelays).sum();

        if (currentBluelay != bluerayCount - 1) {
            return binarySearch(bluerayCount, secondLine, sum, start, mid - 1);
        } else {
            if (bluelaySum == sum) {
                return Arrays.stream(bluelays).max().orElse(0);
            } else {
                return binarySearch(bluerayCount, secondLine, sum, mid + 1, end);
            }
        }
    }
}
