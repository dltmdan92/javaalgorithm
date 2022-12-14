package com.javaalgorithm.example.chapter5.dfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Ex23 {
    public static void main(String[] args) throws IOException {
        List<int[]> nums = Files.lines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/dfs/params/ex23.txt"))
                .map(line -> Arrays.stream(line.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .collect(Collectors.toList());

        int[] firstLine = nums.get(0);
        int nodeCount = firstLine[0];
        int edgeCount = firstLine[1];

        boolean[] visited = new boolean[nodeCount + 1];

        List<List<Integer>> map = new ArrayList<>();

        for (int i = 0; i <= nodeCount; i++) {
            map.add(new LinkedList<>());
        }

        for (int i = 1; i < nums.size(); i++) {
            int startNode = nums.get(i)[0];
            int endNode = nums.get(i)[1];

            map.get(startNode).add(endNode);
        }

        int result = 0;


        for (int i = 1; i <= nodeCount; i++) {
            if (isVisitable(visited, i)) {
                result++;
                visit(visited, i);
                DFS(i, map, visited);
            }
        }
        System.out.println(result);
    }

    private static void DFS(int num, List<List<Integer>> map, boolean[] visited) {
        List<Integer> destinations = map.get(num);

        for (int destination : destinations) {
            if (isVisitable(visited, destination)) {
                visit(visited, destination);
                DFS(destination, map, visited);
            }
        }
    }

    private static boolean isVisitable(boolean[] visited, int i) {
        return !visited[i];
    }

    private static void visit(boolean[] visited, int destination) {
        visited[destination] = true;
    }
}
