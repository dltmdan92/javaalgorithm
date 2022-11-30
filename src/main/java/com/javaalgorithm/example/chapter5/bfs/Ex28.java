package com.javaalgorithm.example.chapter5.bfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ex28 {
    private static List<List<Edge>> map = new ArrayList<>();
    private static int nodeCount = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter5/bfs/params/ex28.txt"));
        nodeCount = Integer.parseInt(lines.get(0));

        int[] nodes = new int[nodeCount];
        map.add(Collections.emptyList());

        for (int i = 1; i <= nodeCount; i++) {
            int[] edges = Arrays.stream(lines.get(i).split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            int startNode = edges[0];
            map.add(startNode, new ArrayList<>());

            nodes[i - 1] = startNode;

            for (int j = 1; j <= edges.length - 3; j+=2) {
                int destinationNode = edges[j];
                int distance = edges[j + 1];
                map.get(startNode).add(new Edge(startNode, destinationNode, distance));
            }
        }

        Integer biggestNode = BFS(nodes[1]).entrySet().stream()
                .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .get();

        Map<Integer, Integer> distances = BFS(biggestNode);

        System.out.println(distances.values().stream().sorted(Comparator.reverseOrder()).findFirst().get());
    }

    private static Map<Integer, Integer> BFS(int startNode) {
        boolean[] visited = new boolean[nodeCount + 1];
        Queue<Edge> queue = new LinkedList<>();
        Map<Integer, Integer> distances = new HashMap<>();

        List<Edge> edges = map.get(startNode);

        visited[startNode] = true;
        distances.put(startNode, 0);
        queue.addAll(edges);

        while (!queue.isEmpty()) {
            Edge pollNode = queue.poll();

            List<Edge> edgesFromStartNode = map.get(pollNode.getStartNode());
            edgesFromStartNode.stream()
                    .filter(edgeFromStartNode -> !visited[edgeFromStartNode.getDestinationNode()])
                    .sorted((x, y) -> Integer.compare(y.getDistance(), x.getDistance()))
                    .forEach(edgeFromStartNode -> {
                        int destinationDistance = edgeFromStartNode.getDistance() + distances.get(pollNode.getStartNode());
                        distances.put(edgeFromStartNode.getDestinationNode(), destinationDistance);
                        visited[edgeFromStartNode.getDestinationNode()] = true;
                        queue.addAll(map.get(edgeFromStartNode.getDestinationNode()));
            });
        }
        return distances;
    }

    private static class Edge {
        private int startNode;
        private int destinationNode;
        private int distance;

        public Edge(int startNode, int destinationNode, int distance) {
            this.startNode = startNode;
            this.destinationNode = destinationNode;
            this.distance = distance;
        }

        public int getDestinationNode() {
            return destinationNode;
        }

        public int getDistance() {
            return distance;
        }

        public int getStartNode() {
            return startNode;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "startNode=" + startNode +
                    ", destinationNode=" + destinationNode +
                    ", distance=" + distance +
                    '}';
        }
    }
}
