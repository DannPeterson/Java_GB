package ru.gb.homework7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate p) {
        if (p.decreaseFood(appetite)) {
            satiety = true;
            System.out.println(getName() + " eats " + appetite);
        } else {
            System.out.println("Not enough food for " + getName());
        }
    }

    public String getName() {
        return name;
    }

    public boolean isSatiety() {
        return satiety;
    }
}
