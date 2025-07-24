package org.example;

import java.util.Objects;

public abstract class Entity {
    private String name;
    private int health;

    public Entity(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    public boolean setHealth(int health) {
        if (health < 100) {
            if(this.health + health > 100) {
                this.health = 100;
            }
            else {
                this.health += health;
            }
            return true;
        }
        return false;
    }

    public abstract String attack();

    public void takeDamage(int damage) {
        if (health >= 0) {
            this.health -= damage;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return health == entity.health && Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health);
    }

    @Override
    public String toString() {
        return "Entity ->" +
                " | Name: " + name +
                " | Health: " + health;
    }
}
