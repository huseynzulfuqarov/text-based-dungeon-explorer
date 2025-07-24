package org.example;

public class Potion extends Item{
    private int healAmount;

    public Potion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }
    @Override
    public void use(Player player) {
        boolean check = player.setHealth(healAmount);
        if(check){
            player.useItem(this);
        }
    }
}
