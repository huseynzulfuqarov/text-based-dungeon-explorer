package org.example;

import java.util.Objects;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void use(Player player) {
        System.out.println("Bu bir silahdır. Döyüşdə avtomatik istifadə edilir. Dəyişmək üçün 'equip' əmrindən istifadə edin.");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damage);
    }

    @Override
    public String toString() {
        return "Weapon -> " + super.toString() +
                " | Damage: " + damage;
    }
}
