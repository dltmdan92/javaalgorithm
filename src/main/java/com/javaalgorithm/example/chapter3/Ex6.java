package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex6 {
    public static void main(String[] args) throws IOException {
        String param = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex6.txt")).get(0);
        int n = Integer.parseInt(param);

        int mid = (n + 1) / 2;
        int count = 1; // 자기 자신 +1
        for (int i = 1; i <= mid; i++) {
            if (isMatched(i, n)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isMatched(int startIdx, int n) {
        int sum = 0;
        for (int movingIdx = startIdx; sum <= n; movingIdx--) {
            sum += movingIdx;
            if (sum == n) {
                return true;
            }
        }
        return false;
    }
}
