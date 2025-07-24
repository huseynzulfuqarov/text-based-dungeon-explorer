package org.example;

public class Monster extends Entity{
    public Monster(String name, int health) {
        super(name, health);
    }

    @Override
    public String attack(){
        return "The goblin is attacking you with a dagger!";
    }
}
