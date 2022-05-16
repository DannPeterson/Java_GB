package ru.gb.homework7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (n <= food) {
            food -= n;
            return true;
        }
        return false;
    }

    public void increaseFood(int n) {
        food += n;
    }

    public int getFood() {
        return food;
    }
}