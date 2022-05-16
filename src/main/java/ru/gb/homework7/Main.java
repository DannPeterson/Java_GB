package ru.gb.homework7;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(40);
        Cat[] cats = new Cat[] {
                new Cat("Cat1", 10),
                new Cat("Cat2", 20),
                new Cat("Cat3", 30)
        };

        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat.isSatiety() ? cat.getName() + " is not hungry" : cat.getName() + " is hungry");
            System.out.println("Plate have " + plate.getFood() + " food");
        }
    }
}
