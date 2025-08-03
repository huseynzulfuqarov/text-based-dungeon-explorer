package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private static Player player;
    private static Room currentRoom;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Room startRoom = new Room("Siz başlanğıc otaqdasınız. Otaq sakit və boş görünür.");
        Room monsterRoom1 = new Room("Bu otaq nəm və qaranlıqdır. Küncdə nə isə parıldayır.");
        Room monsterRoom2 = new Room("Bu otağın döşəməsi sümüklərlə doludur. Dəhşətli bir qoxu var.");
        Room treasureRoom = new Room("Bu otaq xəzinə ilə doludur! Zəfər sizindir!");

        startRoom.setEast(monsterRoom1);
        monsterRoom1.setWest(startRoom);
        monsterRoom1.setNorth(monsterRoom2);
        monsterRoom2.setSouth(monsterRoom1);
        monsterRoom2.setEast(treasureRoom);
        treasureRoom.setWest(monsterRoom2);

        Monster monster = new Monster("İnək", 60, 15);
        Potion potion = new Potion("Süd", 20);
        monsterRoom1.setMonster(monster);
        monsterRoom1.setItem(potion);

        Monster monster2 = new Monster("Qoyun", 80, 20);
        Potion potion2 = new Potion("Qatıq", 30);
        monsterRoom2.setMonster(monster2);
        monsterRoom2.setItem(potion2);

        Weapon bicaq = new Weapon("Bıçaq", 25);
        Weapon balta = new Weapon("Balta", 40);

        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(bicaq);
        player = new Player("Qəssab Qasım", 100, inventory, bicaq);
        currentRoom = startRoom;

        System.out.println("Xoş gəldiniz, " + player.getName() + "!");

        while (true) {
            if (!player.isAlive()) {
                System.out.println("Təəssüf ki, döyüşdə məğlub oldunuz. Oyun bitdi.");
                break;
            }

            if (currentRoom == treasureRoom) {
                System.out.println(treasureRoom.getDescription());
                System.out.println("Təbriklər, oyunu qazandınız!");
                break;
            }

            describeRoom();
            String input = sc.nextLine().toLowerCase().trim();
            String[] parts = input.split(" ");
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "go":
                    move(argument);
                    break;
                case "attack":
                    if (currentRoom.getMonster() != null) {
                        battle();
                    } else {
                        System.out.println("Burada hücum edəcək heç kim yoxdur.");
                    }
                    break;
                case "take":
                    if (currentRoom.getItem() != null) {
                        player.pickUpItem(currentRoom.getItem());
                        System.out.println(currentRoom.getItem().getName() + " inventarınıza əlavə edildi.");
                        currentRoom.setItem(null);
                    } else {
                        System.out.println("Burada götürüləcək bir şey yoxdur.");
                    }
                    break;
                case "use":
                    Item itemToUse = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(argument) && item instanceof Potion) {
                            itemToUse = item;
                            break;
                        }
                    }
                    if (itemToUse != null) {
                        itemToUse.use(player);
                    } else {
                        System.out.println("İnventarınızda belə bir iksir yoxdur.");
                    }
                    break;
                case "inventory":
                    player.showInventory();
                    break;
                case "equip":
                    boolean equipped = false;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(argument) && item instanceof Weapon) {
                            player.equipWeapon((Weapon) item);
                            equipped = true;
                            break;
                        }
                    }
                    if (!equipped) {
                        System.out.println("İnventarınızda belə bir silah yoxdur.");
                    }
                    break;
                case "quit":
                    System.out.println("Oyundan çıxılır...");
                    return;
                case "help":
                    System.out.println("Mümkün əmrlər: go [north, south, east, west], attack, take, use [item], inventory, equip [weapon], quit");
                    break;
                default:
                    System.out.println("Anlaşılmayan əmr. 'help' yazaraq mümkün əmrləri görə bilərsiniz.");
                    break;
            }
            System.out.println("-----------------------------------------------");
        }
        sc.close();
    }

    public static void describeRoom() {
        System.out.println(currentRoom.getDescription());
        if (currentRoom.getMonster() != null) {
            System.out.println("DİQQƏT! Otaqda bir canavar var: " + currentRoom.getMonster().getName());
        }
        if (currentRoom.getItem() != null) {
            System.out.println("Otaqda bir əşya var: " + currentRoom.getItem().getName());
        }
        System.out.print("Mümkün çıxışlar: ");
        if (currentRoom.getNorth() != null) System.out.print("north ");
        if (currentRoom.getSouth() != null) System.out.print("south ");
        if (currentRoom.getEast() != null) System.out.print("east ");
        if (currentRoom.getWest() != null) System.out.print("west ");
        System.out.println("\nİndi nə edək? ('help' yazaraq kömək ala bilərsiniz)");
    }

    public static void move(String direction) {
        Room nextRoom = null;
        switch (direction) {
            case "north":
                nextRoom = currentRoom.getNorth();
                break;
            case "south":
                nextRoom = currentRoom.getSouth();
                break;
            case "east":
                nextRoom = currentRoom.getEast();
                break;
            case "west":
                nextRoom = currentRoom.getWest();
                break;
            default:
                System.out.println("Bu istiqamətə getmək mümkün deyil.");
                return;
        }

        if (nextRoom != null) {
            currentRoom = nextRoom;
        } else {
            System.out.println("O istiqamətdə bir qapı yoxdur.");
        }
    }

    public static void battle() {
        Monster monster = currentRoom.getMonster();
        System.out.println("Döyüş başladı! Rəqib: " + monster.getName());

        while (player.isAlive() && monster.isAlive()) {
            int playerDamage = player.getActiveWeapon().getDamage();
            monster.takeDamage(playerDamage);
            System.out.println(player.attack() + " " + monster.getName() + "-ə " + playerDamage + " zərər vurdunuz.");
            System.out.println(monster.getName() + " canı: " + monster.getHealth());

            if (!monster.isAlive()) {
                System.out.println(monster.getName() + " məğlub edildi!");
                currentRoom.setMonster(null);
                break;
            }

            int monsterDamage = monster.getDamage();
            player.takeDamage(monsterDamage);
            System.out.println(monster.attack() + " " + player.getName() + "-ə " + monsterDamage + " zərər vurdu.");
            System.out.println(player.getName() + " canı: " + player.getHealth());

            if (!player.isAlive()) {
                System.out.println("Siz məğlub oldunuz...");
            }
        }
    }
}