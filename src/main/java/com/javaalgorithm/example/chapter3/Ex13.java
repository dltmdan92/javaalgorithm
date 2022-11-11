package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ex13 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex13.txt"));
        int n = Integer.parseInt(lines.get(0));

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        int lastNum = 0;

        if (queue.size() == 1) {
            System.out.println(1);
            return;
        }

        while (queue.size() >= 2) {
            queue.poll();
            Integer poll2 = queue.poll();
            queue.add(poll2);
            lastNum = poll2;
        }

        System.out.println(lastNum);
    }
}
