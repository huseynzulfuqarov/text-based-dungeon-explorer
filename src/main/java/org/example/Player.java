package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private ArrayList<Item> inventory;
    private Weapon activeWeapon;

    public Player(String name, int health, ArrayList<Item> inventory, Weapon startWeapon) {
        super(name, health);
        this.inventory = inventory;
        this.activeWeapon = startWeapon;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    public void useItem(Item item) {
        inventory.remove(item);
    }

    public void equipWeapon(Weapon weapon) {
        this.activeWeapon = weapon;
        System.out.println(weapon.getName() + " silahını seçdiniz.");
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("İnventarınız boşdur.");
            return;
        }
        System.out.println("--- İNVENTAR ---");
        for (Item item : inventory) {
            System.out.println(item.toString());
        }
        System.out.println("Aktiv silah: " + activeWeapon.getName());
        System.out.println("----------------");
    }


    public boolean heal(int amount) {
        if (getHealth() < 100) {
            int newHealth = getHealth() + amount;
            if (newHealth > 100) {
                setHealth(100);
            } else {
                setHealth(newHealth);
            }
            System.out.println("Canınız " + amount + " qədər bərpa olundu. Hazırkı can: " + getHealth());
            return true;
        } else {
            System.out.println("Canınız artıq maksimumdur.");
            return false;
        }
    }

    @Override
    public String attack() {
        return "Siz " + activeWeapon.getName() + " ilə hücum etdiniz!";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(inventory, player.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inventory);
    }

    @Override
    public String toString() {
        return "Player -> " + super.toString() +
                " | Inventory: " + inventory.toString();
    }
}
