package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Room startRoom = new Room("startRoom");
        Room monsterRoom1 = new Room("Room1");
        Room monsterRoom2 = new Room("Room2");
        Monster monster = new Monster("Inek", 100);
        Potion potion = new Potion("Sud", 20);
        Monster monster2 = new Monster("Qoyun", 100);
        Potion potion2 = new Potion("Qatiq", 30);


        startRoom.setEast(monsterRoom1);
        monsterRoom1.setNorth(monsterRoom2);

        monsterRoom1.setMonster(monster);
        monsterRoom1.setItem(potion);

        monsterRoom2.setMonster(monster2);
        monsterRoom2.setItem(potion2);

        Weapon bicaq = new Weapon("Bicaq", 30);
        Weapon balta = new Weapon("Balta", 50);

        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(bicaq);
        inventory.add(balta);

        Player qessab = new Player("Qasim", 100, inventory);

        while (true) {
            describeRoom();
            String choice = sc.nextLine();


        }

    }

    public static void describeRoom() {
        System.out.println("""
                Siz baslangic otaqdasiniz
                Otaaq bosdur.
                Indi ne edek?
                "go east", "attack", "take item", "use potion"
                """);
    }
}