package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ex10 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex10.txt"));
        int[] line1 = Arrays.stream(lines.get(0).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int l = line1[1];

        Deque<Node> queue = new LinkedList<>();
        queue.add(new Node(0, Integer.MAX_VALUE));

        String[] line2 = lines.get(1).split(" ");
        for (int i = 1; i <= line2.length; i++) {
            int num = Integer.parseInt(line2[i - 1]);
            int startIdx = i - l + 1;

            Node first = queue.getFirst();
            if (startIdx > first.getIdx()) {
                queue.removeFirst();
            }

            while (!queue.isEmpty()) {
                Node lastNode = queue.getLast();
                if (lastNode.getValue() > num) {
                    queue.removeLast();
                } else {
                    break;
                }
            }
            queue.add(new Node(i, num));
            System.out.println(queue.getFirst().getValue());
        }
    }

    private static class Node {
        private final int idx;
        private final int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        public int getIdx() {
            return idx;
        }

        public int getValue() {
            return value;
        }
    }
}
