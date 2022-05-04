package ru.gb.peterson.homework6;

public class Cat extends Animal {
    private static int counter;

    public Cat(String name, int age, double weight) {
        super(name, age, weight);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void swim(int meters) {
        System.out.println(getName() + " не умеет плавать.");
    }

    @Override
    public void run(int meters) {
        if (meters > 200) {
            System.out.println(getName() + " не умеет бегать так далеко.");
        } else {
            System.out.println(getName() + " пробежал " + meters + " метров.");
        }
    }
}