package org.example;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void use(Player player) {
        System.out.println("This is a weapon, it is used in battle.");
    }
}
