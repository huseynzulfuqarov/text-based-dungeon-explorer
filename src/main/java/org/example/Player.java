package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private ArrayList<Item> inventory;
    public Player(String name, int health, ArrayList<Item> inventory) {
        super(name, health);
        this.inventory = inventory;
    }

    public void pickUpItem(Item item){
        inventory.add(item);
    }

    public  void useItem(Item item){
        inventory.remove(item);
    }

    public void showInventory(){
        for(Item item : inventory){
            System.out.println(item);
        }
    }

    @Override
    public String attack(){
       return "You struck with a sword!";
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
        return "Player -> " + super.toString()+
                " | Inventory: " + inventory.toString();
    }
}
