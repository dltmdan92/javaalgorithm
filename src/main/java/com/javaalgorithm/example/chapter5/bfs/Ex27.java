package com.javaalgorithm.example.chapter5.bfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Ex27 {
    private static int n;
    private static int m;
    private static List<List<Integer>> map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/bfs/params/ex27.txt"));
        int[] nm = Arrays.stream(lines.get(0).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        n = nm[0];
        m = nm[1];

        visited = new boolean[n][m];

        map = lines.stream()
                .skip(1L)
                .map(line -> Arrays.stream(line.split("")).map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());

        Node startNode = new Node(0, 0, 1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            if (pollNode.isDestination()) {
                System.out.println(pollNode.getCount());
                break;
            }
            if (pollNode.getAbove().isPresent()) {
                Node above = pollNode.getAbove().get();
                queue.add(above);
                visited[above.getRow()][above.getCol()] = true;
            }
            if (pollNode.getBelow().isPresent()) {
                Node below = pollNode.getBelow().get();
                queue.add(below);
                visited[below.getRow()][below.getCol()] = true;
            }
            if (pollNode.getLeft().isPresent()) {
                Node left = pollNode.getLeft().get();
                queue.add(left);
                visited[left.getRow()][left.getCol()] = true;
            }
            if (pollNode.getRight().isPresent()) {
                Node right = pollNode.getRight().get();
                queue.add(right);
                visited[right.getRow()][right.getCol()] = true;
            }
        }
    }

    private static class Node {
        private int row;
        private int col;
        private int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getCount() {
            return count;
        }

        public Optional<Node> getRight() {
            int row = this.row;
            int col = this.col + 1;
            if (col >= m) {
                return Optional.empty();
            }
            if (map.get(row).get(col) != 1) {
                return Optional.empty();
            }
            if (visited[row][col]) {
                return Optional.empty();
            }
            return Optional.of(new Node(row, col, count + 1));
        }

        public Optional<Node> getLeft() {
            int row = this.row;
            int col = this.col - 1;
            if (col < 0) {
                return Optional.empty();
            }
            if (map.get(row).get(col) != 1) {
                return Optional.empty();
            }
            if (visited[row][col]) {
                return Optional.empty();
            }
            return Optional.of(new Node(row, col, count + 1));
        }

        public Optional<Node> getAbove() {
            int row = this.row - 1;
            int col = this.col;
            if (row < 0) {
                return Optional.empty();
            }
            if (map.get(row).get(col) != 1) {
                return Optional.empty();
            }
            if (visited[row][col]) {
                return Optional.empty();
            }
            return Optional.of(new Node(row, col, count + 1));
        }

        public Optional<Node> getBelow() {
            int row = this.row + 1;
            int col = this.col;
            if (row >= n) {
                return Optional.empty();
            }
            if (map.get(row).get(col) != 1) {
                return Optional.empty();
            }
            if (visited[row][col]) {
                return Optional.empty();
            }
            return Optional.of(new Node(row, col, count + 1));
        }

        public boolean isDestination() {
            return this.row == n - 1 && this.col == m - 1;
        }
    }
}
