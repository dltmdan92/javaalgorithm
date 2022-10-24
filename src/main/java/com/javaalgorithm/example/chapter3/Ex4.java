package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ex4 {
    public static void main(String[] args) throws IOException {
        List<String> params = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex4.txt"));
        String[] line1 = params.get(0).split(" ");
        int size = Integer.parseInt(line1[0]);
        int queries = Integer.parseInt(line1[1]);

        int[][] sums = new int[size][size];

        for (int x = 0; x < size; x++) {
            String[] line = params.get(x + 1).split(" ");
            for (int y = 0, lineLength = line.length; y < lineLength; y++) {
                int num = Integer.parseInt(line[y]);

                int leftSquare = (y > 0) ? sums[x][y - 1] : 0;
                int upRectangle = (x > 0) ? sums[x - 1][y] : 0;
                int upRectangleMinusOne = (x > 0 && y > 0) ? sums[x - 1][y - 1] : 0;
                int upOne = upRectangle - upRectangleMinusOne;
                sums[x][y] = leftSquare + upOne + num;
            }
        }

        int queryStartIndex = size + 1;
        for (int i = 0; i < queries; i++) {
            String[] line = params.get(queryStartIndex + i).split(" ");
            int startX = Integer.parseInt(line[0]) - 1;
            int startY = Integer.parseInt(line[1]) - 1;
            int endX = Integer.parseInt(line[2]) - 1;
            int endY = Integer.parseInt(line[3]) - 1;

            int upRectangle = (startX > 0) ? sums[startX - 1][endY] : 0;
            int leftRectangle = (startY > 0 ) ? sums[endX][startY - 1] : 0;
            int leftRectangleMinus = (startX > 0 && startY > 0) ? sums[startX - 1][startY - 1] : 0;

            System.out.println(sums[endX][endY] - upRectangle - leftRectangle + leftRectangleMinus);
        }
    }
}
