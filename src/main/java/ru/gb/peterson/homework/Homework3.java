package ru.gb.peterson.homework;

public class Homework3 {

    public static void main(String[] args) {
    }

    public static void task1() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] != 0 ? 0 : 1;
        }
    }

    public static void task2() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;
    }

    public static void task3() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) array[i] = array[i] * 2;
        }
    }

    public static void task4() {
        int[][] array = new int[10][10];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) array[i][j] = 1;
                if (i == array[i].length - j - 1) array[i][j] = 1;
            }
        }
    }

    public static void task5(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) array[i] = initialValue;
    }

    public static void task6() {
        int[] array = {-100, 5, 3, 2, 11, 4, 5, 2500, 4, 8, 9, 1};
        int min = array[1];
        int max = array[1];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }
    }

    public static boolean task7(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j <= i; j++) left = left + array[j];
            for (int k = i + 1; k < array.length; k++) right = right + array[k];
            if (left == right) return true;
        }
        return false;
    }

    public static int[] task8(int[] array, int shift) {
        boolean isNegative = shift < 0;
        if (isNegative) shift = shift * (-1);
        int tmp = 0;
        for (int j = 0; j < shift; j++) {
            if (isNegative) {
                for (int i = array.length - 1; i > 0; i--) {
                    tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                }
            } else {
                for (int i = 0; i < array.length - 1; i++) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
        }
        return array;
    }
}