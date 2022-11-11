package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ex14 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex14.txt"));
        int operationCount = Integer.parseInt(lines.get(0));

        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> {
            int xAbs = Math.abs(x);
            int yAbs = Math.abs(y);
            if (xAbs == yAbs) {
                return x > y ? 1 : -1;
            } else {
                return xAbs - yAbs;
            }
        });

        for (int i = 1; i <= operationCount; i++) {
            int num = Integer.parseInt(lines.get(i));

            if (num != 0) {
                queue.add(num);
            } else {
                if (queue.isEmpty()) {
                    System.out.println(num);
                } else {
                    System.out.println(queue.poll());
                }
            }
        }
    }
}
