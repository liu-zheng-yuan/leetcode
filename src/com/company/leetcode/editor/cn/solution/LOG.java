package com.company.leetcode.editor.cn.solution;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LOG {
    public static int count = 0;

    public static void printIn() {
        for (int i = 0; i < count; i++) {
            System.out.print(("   "));
        }
        count++;
    }

    public static void printOut() {
        count--;
        for (int i = 0; i < count; i++) {
            System.out.print(("   "));
        }
    }

    public static void log(String s1, Object o1, String s2, Object o2) {
        if (o1 instanceof int[]) {
            int[] a1 = (int[]) o1;
            o1 = Arrays.stream(a1).boxed().collect(Collectors.toList());
        }
        if (o2 instanceof int[]) {
            int[] a2 = (int[]) o2;
            o2 = Arrays.stream(a2).boxed().collect(Collectors.toList());
        }
        System.out.printf("%s = %s,%s = %s", s1, o1, s2, o2);
    }

    public static void log(String s1, Object o1, String s2, Object o2, String s3, Object o3) {
        if (o1 instanceof int[]) {
            int[] a1 = (int[]) o1;
            o1 = Arrays.stream(a1).boxed().collect(Collectors.toList());
        }
        if (o2 instanceof int[]) {
            int[] a2 = (int[]) o2;
            o2 = Arrays.stream(a2).boxed().collect(Collectors.toList());
        }
        if (o3 instanceof int[]) {
            int[] a3 = (int[]) o3;
            o3 = Arrays.stream(a3).boxed().collect(Collectors.toList());
        }
        System.out.printf("%s = %s,%s = %s,%s = %s", s1, o1, s2, o2, s3, o3);
    }

    public static void log(String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4) {
        if (o1 instanceof int[]) {
            int[] a1 = (int[]) o1;
            o1 = Arrays.stream(a1).boxed().collect(Collectors.toList());
        }
        if (o2 instanceof int[]) {
            int[] a2 = (int[]) o2;
            o2 = Arrays.stream(a2).boxed().collect(Collectors.toList());
        }
        System.out.printf("%s = %s,%s = %s,%s = %s,%s = %s", s1, o1, s2, o2, s3, o3, s4, o4);
    }

    public static void log(String s1, Object o1, String s2, Object o2, String s3, Object o3, String s4, Object o4, String s5, Object o5) {
        if (o1 instanceof int[]) {
            int[] a1 = (int[]) o1;
            o1 = Arrays.stream(a1).boxed().collect(Collectors.toList());
        }
        if (o2 instanceof int[]) {
            int[] a2 = (int[]) o2;
            o2 = Arrays.stream(a2).boxed().collect(Collectors.toList());
        }
        System.out.printf("%s = %s,%s = %s,%s = %s,%s = %s,%s = %s", s1, o1, s2, o2, s3, o3, s4, o4, s5, o5);
    }
}
