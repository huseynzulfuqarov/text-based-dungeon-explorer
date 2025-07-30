package org.example;

import java.util.Objects;

public class Room {
    private String description;
    private Monster monster;
    private Item item;
    private Room north, south, east, west;

    public Room(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return this.monster;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getNorth() {
        return this.north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getSouth() {
        return this.south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getEast() {
        return this.east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public Room getWest() {
        return this.west;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(description, room.description) && Objects.equals(monster, room.monster) && Objects.equals(item, room.item) && Objects.equals(north, room.north) && Objects.equals(south, room.south) && Objects.equals(east, room.east) && Objects.equals(west, room.west);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, monster, item, north, south, east, west);
    }

    @Override
    public String toString() {
        return "Room -> " +
                " | Description='" + description + '\'' +
                " | Monster=" + monster +
                " | Item=" + item +
                " | North=" + north +
                " | South=" + south +
                " | East=" + east +
                " | West=" + west;
    }
}
