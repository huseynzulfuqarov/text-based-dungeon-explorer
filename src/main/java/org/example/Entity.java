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

    public abstract String attack();

    public void takeDamage(int damage) {
        if (health >= 0) {
            this.health -= damage;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    protected void setHealth(int newHealth) {
       this.health = newHealth;
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
