package com.javaalgorithm.example.chapter3;

import java.util.Arrays;
import java.util.List;

/**
 * p.44 구간 합 구하기
 */
public class Ex3 {
    public static void main(String[] args) {
        String line1 = "5 3"; // 데이터의 갯수, 질의 갯수
        String line2 = "5 4 3 2 1"; // 구간 합을 구할 대상 배열
        List<String> ijList = List.of("1 3", "2 4", "5 5");

        int[] line1Array = Arrays.stream(line1.split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        int numOfDatas = line1Array[0];
        int queryCount = line1Array[1];

        int[] datas = new int[numOfDatas + 1];
        datas[0] = 0;

        int[] sums = new int[numOfDatas + 1];
        sums[0] = 0;

        int[] numArray = Arrays.stream(line2.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 1; i <= numOfDatas; i++) {
            datas[i] = numArray[i - 1];
            sums[i] = sums[i - 1] + datas[i];
        }

        ijList.stream()
                .map(ijStr -> Arrays.stream(ijStr.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .forEach(ij -> {
                    int i = ij[0];
                    int j = ij[1];
                    System.out.println(sums[j] - sums[i - 1]);
                });
    }
}
