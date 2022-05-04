package ru.gb.peterson.homework6;

public class Dog extends Animal {
    private static int counter;

    public Dog(String name, int age, double weight) {
        super(name, age, weight);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void swim(int meters) {
        if (meters > 10) {
            System.out.println(getName() + " не умеет плавать так далеко.");
        } else {
            System.out.println(getName() + " проплыл " + meters + " метров.");
        }
    }

    @Override
    public void run(int meters) {
        if (meters > 500) {
            System.out.println(getName() + " не умеет бегать так далеко.");
        } else {
            System.out.println(getName() + " пробежал " + meters + " метров.");
        }
    }
}