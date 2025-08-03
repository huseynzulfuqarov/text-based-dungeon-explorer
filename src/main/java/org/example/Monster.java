package org.example;

public class Monster extends Entity {
    private int damage;
    public Monster(String name, int health, int damage) {
        super(name, health);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String attack() {
        return getName() + " sizə hücum edir!";
    }
}