package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex9 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex9.txt"));
        int[] line1 = Arrays.stream(lines.get(0).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int s = line1[0];
        int p = line1[1];

        int[] a = new int[s + 1];
        int[] c = new int[s + 1];
        int[] g = new int[s + 1];
        int[] t = new int[s + 1];

        int[] minCounts = Arrays.stream(lines.get(2).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int aMin = minCounts[0];
        int cMin = minCounts[1];
        int gMin = minCounts[2];
        int tMin = minCounts[3];

        String[] line2 = lines.get(1).split("");
        for (int i = 0; i < line2.length; i++) {
            switch (line2[i]) {
                case "A":
                    a[i + 1] = a[i] + 1;
                    c[i + 1] = c[i];
                    g[i + 1] = g[i];
                    t[i + 1] = t[i];
                    break;
                case "C":
                    a[i + 1] = a[i];
                    c[i + 1] = c[i] + 1;
                    g[i + 1] = g[i];
                    t[i + 1] = t[i];
                    break;
                case "G":
                    a[i + 1] = a[i];
                    c[i + 1] = c[i];
                    g[i + 1] = g[i] + 1;
                    t[i + 1] = t[i];
                    break;
                case "T":
                    a[i + 1] = a[i];
                    c[i + 1] = c[i];
                    g[i + 1] = g[i];
                    t[i + 1] = t[i] + 1;
                    break;
                default:
                    break;
            }
        }

        int matchCount = 0;
        for (int i = p; i < a.length; i++) {
            int startIdxExclusive = i - p;
            int endIdxInclusive = i;
            if (a[endIdxInclusive] - a[startIdxExclusive] >= aMin &&
                    c[endIdxInclusive] - c[startIdxExclusive] >= cMin &&
                    g[endIdxInclusive] - g[startIdxExclusive] >= gMin &&
                    t[endIdxInclusive] - t[startIdxExclusive] >= tMin) {
                matchCount++;
            }
        }
        System.out.println(matchCount);
    }
}
