package com.javaalgorithm.example.chapter5.bfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ex26 {
    private static List<List<Integer>> map = new ArrayList<>();
    private static List<Integer> dfsVisitedList = new ArrayList<>();
    private static List<Integer> bfsVisitedList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<int[]> lines = Files.lines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/bfs/params/ex26.txt"))
                .map(line -> Arrays.stream(line.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .toList();

        int[] firstLine = lines.get(0);
        int nodeCount = firstLine[0];
        int edgeCount = firstLine[1];
        int startNode = firstLine[2];

        for (int i = 0; i <= nodeCount; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 1; i <= edgeCount; i++) {
            int[] line = lines.get(i);
            int node1 = line[0];
            int node2 = line[1];
            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        // dfs
        {
            boolean[] visited = new boolean[nodeCount + 1];
            dfs(startNode, visited);
            System.out.println(dfsVisitedList);
        }

        // bfs
        {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[nodeCount + 1];
            queue.add(startNode);
            bfsVisitedList.add(startNode);
            visited[startNode] = true;
            while (!queue.isEmpty()) {
                Integer node = queue.poll();
                List<Integer> visitableNodes = map.get(node).stream()
                        .filter(nearNode -> !visited[nearNode])
                        .sorted(Comparator.naturalOrder())
                        .peek(nearNode -> visited[nearNode] = true)
                        .toList();
                queue.addAll(visitableNodes);
                bfsVisitedList.addAll(visitableNodes);
            }
            System.out.println(bfsVisitedList);
        }

    }

    private static void dfs(int startNode, boolean[] visited) {
        if (startNode == 0) {
            return;
        }

        visited[startNode] = true;
        dfsVisitedList.add(startNode);

        Integer visitableNode = map.get(startNode).stream()
                .filter(nearNode -> !visited[nearNode])
                .min(Comparator.naturalOrder())
                .orElse(0);

        dfs(visitableNode, visited);
    }
}
