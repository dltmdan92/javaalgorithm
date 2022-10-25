package com.javaalgorithm.example.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex5 {
    public static void main(String[] args) throws IOException {
        List<String> params = Files.readAllLines(Path.of("src/main/java/com/javaalgorithm/example/chapter3/params/ex5.txt"));

        int[] line1 = Arrays.stream(params.get(0).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        int n = line1[0];
        int m = line1[1];

        int[] line2 = Arrays.stream(params.get(1).split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] mods = new int[n + 1];
        int[] modCounts = new int[m];

        for (int i = 0; i < line2.length; i++) {
            int num = line2[i];
            int point = i + 1;

            mods[point] = (num + mods[i]) % m;

            modCounts[mods[point]]++;
        }

        int result = modCounts[0];

        for (int i = 0; i < modCounts.length; i++) {
            int modCountOfI = modCounts[i];

            int caseCount = modCountOfI * (modCountOfI - 1) / 2;
            result += caseCount;
        }

        System.out.println(result);
    }

}
