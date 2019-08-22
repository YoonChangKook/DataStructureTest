package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 9935번
 *
 * @author 국윤창
 */
public class Problem9935 {
    private Problem9935() {}

    public static void run() {
        String str, explosion;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        explosion = scanner.nextLine();

        String before;
        do {
            before = str;
            str = str.replaceAll(explosion, "");
        } while (before != str);

        System.out.println(str.equals("") ? "FRULA" : str);
    }

    private static int[] getPi(String str) {
        int size = str.length();
        int[] pi = new int[str.length()];
        for (int i = 1, j = 0; i < size; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    private static List<Integer> kmp(String str, String pattern) {
        List<Integer> result = new ArrayList<>();
        int[] pi = getPi(pattern);
        int strSize = str.length();
        int patternSize = pattern.length();

        for (int i = 0, j = 0; i < strSize; i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (str.charAt(i) == pattern.charAt(j)) {
                if (j == patternSize - 1) {
                    result.add(i - patternSize + 1);
                } else {
                    j++;
                }
            }
        }

        return result;
    }
}
