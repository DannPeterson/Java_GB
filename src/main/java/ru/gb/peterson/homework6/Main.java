package ru.gb.peterson.homework6;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Зерг", 3, 1.5);
        Cat cat2 = new Cat("Терран", 2, 2);
        Dog dog = new Dog("Протос", 5, 20);

        System.out.println("Котов: " + Cat.getCounter());
        System.out.println("Собак: " + Dog.getCounter());
    }
}
