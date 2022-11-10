package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ex11 {
    public static void main(String[] args) throws IOException {
        int[] nums = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex11.txt")).stream()
                .mapToInt(Integer::valueOf)
                .toArray();

        int n = nums[0];

        int numToPush = 1;

        Stack<Integer> stack = new Stack<>();

        Queue<String> result = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            int currentTargetNum = nums[i];
            while (currentTargetNum >= numToPush) {
                stack.add(numToPush);
                numToPush++;
                result.add("+");
            }
            Integer pop = stack.pop();
            result.add("-");
            if (currentTargetNum < pop) {
                result.clear();
                break;
            }
        }
        for (String str:result) {
            System.out.println(str);
        }
        if (result.isEmpty()) {
            System.out.println("NO");
        }
    }

}
