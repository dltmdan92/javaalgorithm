package com.javaalgorithm.example.chapter5.dfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Ex25 {
    private static List<List<Integer>> map;
    private static int nodeCount;
    private static int edgeCount;
    private static boolean OK;

    public static void main(String[] args) throws IOException {
        List<int[]> lines = Files.lines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/dfs/params/ex25.txt"))
                .map(line -> Arrays.stream(line.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .collect(Collectors.toList());

        nodeCount = lines.get(0)[0];
        edgeCount = lines.get(0)[1];

        map = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            map.add(new LinkedList<>());
        }

        for (int i = 1; i <= edgeCount; i++) {
            int node1 = lines.get(i)[0];
            int node2 = lines.get(i)[1];
            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        for (int i = 0; i < nodeCount; i++) {
            dfs(i, 0, new boolean[nodeCount]);
            if (OK) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static void dfs(int num, int visitCount, boolean[] visited) {
        visited[num] = true;
        visitCount++;
        if (visitCount >= 5) {
            OK = true;
            return;
        }
        List<Integer> visitableFriends = map.get(num).stream()
                .filter(friend -> !visited[friend])
                .toList();

        for (int visitableFriend : visitableFriends) {
            dfs(visitableFriend, visitCount, visited);
        }
        visited[num] = false;
    }
}
