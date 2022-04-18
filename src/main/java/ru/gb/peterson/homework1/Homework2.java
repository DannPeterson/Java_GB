package ru.gb.peterson.homework1;

public class Homework2 {

    public static void main(String[] args) {
    }

    public static boolean checkSum(int a, int b) {
        return (a + b) > 10 && (a + b) <= 20;
    }

    public static void printPositiveOrNegative(int a) {
        if (a < 0) {
            System.out.println("Отрицательное число");
        } else {
            System.out.println("Положительное число");
        }
    }

    public static boolean isNegative(int a) {
        if (a < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void printLine(int linesCount, String line) {
        for (int i = 0; i < linesCount; i++) {
            System.out.println(line);
        }
    }

    public static boolean isLeapYear(int year) {
        if(year % 400 == 0) return true;
        if(year % 100 == 0) return false;
        if(year % 4 == 0) return true;
        return false;
    }
}
