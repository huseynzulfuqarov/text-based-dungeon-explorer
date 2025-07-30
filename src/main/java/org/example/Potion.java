package org.example;

import java.util.Objects;

public class Potion extends Item {
    private int healAmount;

    public Potion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player) {
        boolean check = player.setHealth(healAmount);
        if (check) {
            player.useItem(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Potion potion = (Potion) o;
        return healAmount == potion.healAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), healAmount);
    }

    @Override
    public String toString() {
        return "Potion -> " + super.toString() +
                " | Heal Amount: " + healAmount;
    }
}
