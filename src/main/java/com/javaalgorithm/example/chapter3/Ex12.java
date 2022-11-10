package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Ex12 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex12.txt"));
        int count = Integer.parseInt(lines.get(0));

        int[] nums = Arrays.stream(lines.get(1).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        Stack<Integer> stack = new Stack<>();
        int[] results = new int[count];

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    Integer popIndex = stack.pop();
                    results[popIndex] = nums[i];
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            Integer emptyIndex = stack.pop();
            results[emptyIndex] = -1;
        }

        System.out.println(Arrays.stream(results)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static class Node {
        private final int index;
        private final int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }
}
