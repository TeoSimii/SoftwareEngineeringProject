package com.idsproject.Events;

import com.idsproject.Ascii.Ascii;
import com.idsproject.Enemies.Enemy;
import com.idsproject.SaveFile.saveFile;
import java.util.*;
import com.idsproject.Items.HealItem;
import com.idsproject.Items.Item;
import com.idsproject.Player.Player;
import com.idsproject.Rooms.Room;
import com.idsproject.Armor.Armor;
import com.idsproject.Weapons.Weapon;
import com.idsproject.NPC.Npc;
import com.idsproject.Items.KeyItem;
import jdk.jfr.Description;

import javax.management.DescriptorKey;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class Events {
    public static Room[][] dungeon = new Room[4][4];
    private static String choice;
    private static final JButton submit =new JButton("Submit");
    private static boolean buttonPressed;
    JTextField test = new JTextField();

    //inizializzazione della matrice della mappa di gioco
    public static void initDungeon() {
        int a = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                dungeon[i][j] = new Room(a);
                a++;
            }
    }

    // initialization of the dungeon (with items and characters inside)
    public static void setDungeon(Room[][] dungeon) {
        Events.dungeon = dungeon;
    }

    // returns dungeon
    public static Room[][] getDungeon () {
        return dungeon;
    }

    // method that summons an enemy for when player enters a room. Every room has a tier that defines
    // which type of enemies can be created
    public static boolean randomEncounter(int tier, Player player, JLabel log, JLabel display, String a, Boolean test) {
        Enemy mob = new Enemy(tier);
        log.setText("You encountered a " + mob.getname() + "!\n\n");
        if(mob.getname().equalsIgnoreCase("Slime"))
            display.setText(Ascii.slime);
        if(mob.getname().equalsIgnoreCase("Clay puppet"))
            display.setText(Ascii.puppet);
        if(mob.getname().equalsIgnoreCase("Ghoul"))
            display.setText(Ascii.ghoul);
        if(mob.getname().equalsIgnoreCase("Puppeteer"))
            display.setText(Ascii.puppeteer);
        if(mob.getname().equalsIgnoreCase("Golem"))
            display.setText(Ascii.golem);
        if(mob.getname().equalsIgnoreCase("Sanctuary Guardian"))
            display.setText(Ascii.sanctuary_guardian);
        if(mob.getname().equalsIgnoreCase("Basilisk"))
            display.setText(Ascii.basilisk);
        if(mob.getname().equalsIgnoreCase("Shadow of Yahrnam"))
            display.setText(Ascii.shadow);
        if(mob.getname().equalsIgnoreCase("Demon of Hatred"))
            display.setText(Ascii.demon);

        log.setText("<html><center>You encountered a" + mob.getname() + " !<center><html>");
        battle(mob, player, log, display, a, test);
        if (player.isAlive() && tier > 4)
            return false;
        else return player.isAlive();
    }

    //method for managing turn-style battle
        public static void battle(Enemy Enemy, Player player, JLabel log, JLabel display, String a, Boolean test) {

        while (true) {
            log.setText("<html><center>"+Enemy.getname() + "HP:" + Enemy.gethealth() + "/" + Enemy.getMaxHp()
            +" "+ player.getName() + "HP:" + player.getHp() + "/" + player.getMaxHp() +"<br>Attack<br> Use potion <br>Run away"+"<center><html>");

            // synchronization of thread with the button submit
            synchronized (submit) {
                try {
                    // boolean check to see if we're in a test phase or not
                    if(!test){
                        // thread waits for the pushing of the button "submit"
                        submit.wait();
                    }
                    // we're in a test phase and we expect a set string
                    else{choice=a;}
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // First option of battle: attack the foe and calculates damage
            if (choice.toLowerCase().equals("attack")) {
                int damageDone = player.attack();
                int damageTaken = Enemy.attack(Enemy.getTier());
                Enemy.takeDamage(damageDone);
                if(Enemy.gethealth()>0){
                    player.takeDamage(damageTaken);
                    log.setText("<html><center>"+"You dealt " + damageDone + " damage to the " + Enemy.getname() + "!"
                            +"<br>The enemy dealt " + damageTaken + " damage to you!"+"<html><center>");
                } else {
                    log.setText("<html><center>"+"You dealt " + damageDone + " damage to the " + Enemy.getname() + "!");
                }

                // same function as before (see upper)
                synchronized (submit) {
                    try {
                        if(!test){submit.wait();}
                        else{choice=a;}
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //Controllo se player è vivo
                if (!player.isAlive()) {
                    break;
                } else if (Enemy.gethealth() <= 0) {
                    player.addScore(Enemy.getscore());
                    display.setText("");
                    log.setText("<html><center>"+"You defeated the " + Enemy.getname() + "!"
                    +"<br>You gained " + Enemy.getscore() + " points!"+"<center><html>");
                    break;
                }

            // Second option of battle: use a potion to heal during battle    
            } else if (choice.equalsIgnoreCase("use potion")) {
                // first scenario: player is already at maximum hp
                if (player.getHp() == player.getMaxHp()) {
                    log.setText("You are already at full health!");
                    synchronized (submit) {
                        try {
                            if(!test){submit.wait();}
                            else{choice=a;}
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // second scenario: player isn't at maximum hp
                } else {
                    Item Item;
                    log.setText("<html><center>"+"Which Item do you want to use?<br>"+
                     player.ShowInventory()+"<center><html>");
                    synchronized (submit) {
                        try {
                            if(!test){submit.wait();}
                            else{choice=a;}
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    String text = choice;
                    Item=player.checkInventory(text);
                    // checks if inventory contains selected item
                    if(Item==null){
                        log.setText("You don't have that item!");
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // item is present in the inventory and is an healing item   
                    else if(Item.getHeals()){
                        HealItem heal = (HealItem) Item;
                        usePotion(heal, player, log);
                    }
                    // item is present in the inventory but it isn't an healing item    
                    else {
                        log.setText("You can't use that item!");
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
             // third option of battle: run away from the foe (not assured)   
            } else if (choice.toLowerCase().equals("run away")) {
                // probability of 35% to escape
                if (Math.random() * 10 + 1 <= 3.5 && Enemy.getTier() <= 4) {
                    log.setText("You successfully ran away!");
                    display.setText("");
                    break;
                } else {
                    if (Enemy.getTier() > 4) {
                        log.setText("You can't escape from the demon of hatred!"); 
                    } else { //attempt of running away failed
                        log.setText("You failed to run away!");
                        synchronized (submit) {
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // in case of failed escape, the enemy will damage the player
                        int damageTaken = Enemy.attack(Enemy.getTier());
                        player.takeDamage(damageTaken);
                        log.setText("The enemy dealt " + damageTaken + " damage to you!");
                        //Controllo se player è vivo
                        if (!player.isAlive()) {
                            gameEnd(player, log);//Aggiungi funzione per resettare tutto
                            break;
                        } else if (Enemy.gethealth() <= 0) {  // Enemy defeated
                            player.addScore(Enemy.getscore());
                            log.setText("<html><center>"+"You defeated the " + Enemy.getname() + "!"
                            +"<br>You gained " + Enemy.getscore() + " points!"+"<center><html>");
                            break;
                        }
                    }
                }


            } else {  // Option inserted not available
                log.setText("You can't do that");
            }

        }

    }

    // checks if player has won (function called in the game loop as condition)
    public static void gameEnd(Player player, JLabel log) {
        if (player.isAlive()) {
            log.setText("<html><center>"+"You managed to defeat the demon of hatred and bring back peace to the world!"
            + "During your journey you earned " + player.getScore() + " points! Good job!"
            + "Try to score even better next time!"+"<center><html>");
        } else {
            log.setText("<html><center>"+"You died..."
            + "<br>During your journey you earned " + player.getScore() + " points..."
            + "<br>Try to score even better next time!"+"<center><html>");
        }
    }

    // function to move inside the grid
    public static boolean move(String direction, Player player, Room room, JLabel log) {
        // checks if there aren't locked doors or walls in the selected direction
        if (room.HasDirection(direction) && !room.isLocked(direction)) {
            player.setCoordinates(direction);
            return true;
        } else if (!room.HasDirection(direction)) {
            log.setText("There isn't a room in this direction!");
        } else {
            log.setText("The door is locked!");
        }
        return false;
    }


    // usage of an healItem
    static void usePotion(HealItem heal, Player player, JLabel log) {
        // hp already maxed
        if (player.getHp() == player.getMaxHp())
            log.setText("You already have max Hp!");
        // hp not full
        else {
            player.healHp(heal.getHealingPower());
            //Se la pozione cura + della tua vita massima, ripristina al massimo consentito
            if (player.getHp() > player.getMaxHp()) {
                player.healHp(player.getMaxHp() - player.getHp());
            }
            log.setText(player.discardItem(heal));
        }
    }



    // core of the game: this loop is active from the start to the end of the game and contains all the game dynamics
    public static synchronized void gameLoop(Player player, JLabel log, JLabel display, JTextField command,JPanel bottomPanel) throws InterruptedException {
        try {
            command.setText("");
            submit.addActionListener(e -> {
                buttonPressed = true;
                choice = command.getText();  // input inserted is taken
                command.setText("");  // reset text field
                synchronized (submit) {
                    submit.notify();  // communicates that input is taken
                }
            });
            // removal and add of the layout "text-button"
                bottomPanel.remove(1);
                bottomPanel.add(submit);
                Thread t1 = new Thread() {
                    public synchronized void run() {

                            boolean isRunning = true;
                            String[] cho;
                            String[] arr;
                            log.setText("<html><center>" + "You entered in the dungeon <br> Choose what to do (help for command list):" + "<center><html>");
                            while (isRunning && player.isAlive()) {

                                submit.requestFocus();
                                // when the button is pushed, the game starts
                                if (buttonPressed) {
                                    cho = Events.choice.split(" ", 2);
                                    log.setText(Arrays.toString(cho));
                                    // choice of movement
                                    if (cho[0].equalsIgnoreCase("north") || cho[0].equalsIgnoreCase("south") || cho[0].equalsIgnoreCase("east") || cho[0].equalsIgnoreCase("west")) {
                                        boolean moved = move(Events.choice, player, dungeon[player.getX()][player.getY()], log);
                                        if (moved) {
                                            isRunning = randomEncounter(dungeon[player.getX()][player.getY()].getTier(), player, log, display, choice, false);
                                        }
                                     // choice of showing everything in the room   
                                    } else if (cho[0].equalsIgnoreCase("list")) {
                                        dungeon[player.getX()][player.getY()].showItems(log);
                                    // choice of picking up an item (requests a second parameter)    
                                    } else if (cho[0].equalsIgnoreCase("take")) {
                                        try{Item b = dungeon[player.getX()][player.getY()].isInRoom(cho[1]);
                                        if (b == null) {
                                            log.setText("<html><center>" + "This item isn't in the room!" + "<center><html>");
                                        } else if (!b.isPickable()) {
                                            log.setText("<html><center>" + "You can't take this item!" + "<center><html>");
                                        } else {
                                            player.pickUp(b);
                                            if (dungeon[player.getX()][player.getY()].isInRoom(cho[1]) != null && player.checkInventory(b.getName())!=null) {
                                                dungeon[player.getX()][player.getY()].removeItem(b);
                                            } else {
                                                log.setText("<html><center>" + "You weight too much to carry this item!" + "<center><html>");
                                            }
                                        }}catch (ArrayIndexOutOfBoundsException a) {
                                            log.setText("Missing parameter in command");
                                        }
                                    // choice of showing inventory    
                                    } else if (cho[0].equalsIgnoreCase("inventory")) {
                                        while (true) {
                                            log.setText("<html><center>" + "=========================<br>"
                                                    + "========Inventory========<br>"
                                                    + player.ShowInventory()
                                                    + "<br>=========================<br>"
                                                    + "======Player Status======<br>"
                                                    + player.getName() + ": " + player.getHp() + "/" + player.getMaxHp()
                                                    + "<br>weight: " + player.getWeight() + "/50"
                                                    + "<br>Choose what to do ('q' to quit,'h' for a list of actions)" + "<center><html>");
                                            String invChoice;
                                            //#############################################################################################################
                                            // Pezzo per aspettare che il pulsante venga premuto
                                            synchronized (submit) {
                                                try {
                                                    submit.wait();
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            //#############################################################################################################
                                            invChoice = choice;
                                            arr = invChoice.split(" ", 2);
                                            if (arr[0].equalsIgnoreCase("q")) {
                                                log.setText("You exited from the inventory");
                                                break;
                                                // request of help: prints list of command
                                            } else if (arr[0].equalsIgnoreCase("h")) {
                                                log.setText("<html><center>" + "===============Action===============<br>" + "List of commands:<br>"
                                                        + "<br>-Equip/Unequip *item name*"
                                                        + "<br>-Discard *item name*"
                                                        + "<br>-Inspect *item name*"
                                                        + "<br>-Use *item name*" + "<center><html>");
                                                synchronized (submit) {
                                                    try {
                                                        submit.wait();
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                                // request of discardment of an object (requires object name as 2nd parameter)
                                            } else if (arr[0].equalsIgnoreCase("discard")) {
                                                try {
                                                    Item temp;
                                                    temp = player.checkInventory(arr[1]);
                                                    if (temp == null) {
                                                        log.setText("You don't have this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else if (!temp.canDiscard()) {
                                                        log.setText("You can't discard this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else {
                                                        log.setText(player.discardItem(temp));
                                                    }
                                                }catch (ArrayIndexOutOfBoundsException a) {
                                                    log.setText("Missing parameter in command");
                                                }
                                                // request to equip an object (requires object name as 2nd parameter and it has to be a weapon/armor)
                                            } else if (arr[0].equalsIgnoreCase("equip")) {
                                                try {
                                                    Item temp;
                                                    temp = player.checkInventory(arr[1]);
                                                    if (temp == null) {
                                                        log.setText("You don't have this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else if (temp.isArmor()) {
                                                        Armor a = (Armor) temp;
                                                        log.setText(player.equipArmorInSlot(a));
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else if (temp.isWeapon()) {
                                                        Weapon a = (Weapon) temp;
                                                        log.setText(player.equipWeaponInSlot(a));
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else {
                                                        log.setText("You can't equip this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }catch (ArrayIndexOutOfBoundsException a) {
                                                    log.setText("Missing parameter in command");
                                                }
                                                // request to unequip an object (requires object name as 2nd parameter and it has to be a weapon/armor)
                                            } else if (arr[0].equalsIgnoreCase("unequip")) {
                                                try {
                                                    Item temp;
                                                    temp = player.checkInventory(arr[1]);
                                                    if (temp == null) {
                                                        log.setText("You don't have this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else if (temp.isArmor()) {
                                                        Armor a = (Armor) temp;
                                                        log.setText(player.unequipArmorInSlot(a));
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else if (temp.isWeapon()) {
                                                        Weapon a = (Weapon) temp;
                                                        log.setText(player.unequipWeaponInSlot(a));
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else {
                                                        log.setText("You can't unequip this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }catch (ArrayIndexOutOfBoundsException a) {
                                                    log.setText("Missing parameter in command");
                                                }
                                             // request of use of an object (requires object name as 2nd parameter and it has to be a key/healing item)    
                                            } else if (arr[0].equalsIgnoreCase("use")) {
                                                try {                                                    
                                                    Item Item = player.checkInventory(arr[1]);
                                                    try {
                                                        if (arr[1] == null) {
                                                            log.setText("You don't have that item!");
                                                            try {
                                                                sleep(2000);
                                                            } catch (InterruptedException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                        } else if (Item.getHeals()) {
                                                            HealItem heal = (HealItem) Item;
                                                            usePotion(heal, player, log);
                                                        } else if (arr[1].equalsIgnoreCase("the emperor")) {
                                                            Random RANDOM = new Random();
                                                            int a = RANDOM.nextInt(100);
                                                            if (a >= 50) {
                                                                player.teleport("boss");
                                                                isRunning = randomEncounter(dungeon[player.getX()][player.getY()].getTier(), player, log, display, choice, false);
                                                                break;
                                                            } else {
                                                                player.teleport("start");
                                                                isRunning = randomEncounter(4, player, log, display, choice, false);
                                                                break;
                                                            }

                                                        } else {
                                                            log.setText("You can't use that item!");
                                                            try {
                                                                sleep(2000);
                                                            } catch (InterruptedException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                        }
                                                    } catch (NullPointerException e) {
                                                        log.setText("You don't have that item!");
                                                    }
                                                }catch (ArrayIndexOutOfBoundsException a) {
                                                    log.setText("Missing parameter in command");
                                                }
                                             // request of inspection of an object (requires object name as 2nd parameter)                                                    
                                            } else if (arr[0].equalsIgnoreCase("inspect")) {
                                                try {
                                                    Item temp;
                                                    temp = player.checkInventory(arr[1]);
                                                    if (temp == null) {
                                                        log.setText("You don't have this item!");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    } else {
                                                        log.setText(temp.getDescription());
                                                        //log.setText("You have: " + temp.getStack() + " of this item");
                                                        try {
                                                            sleep(2000);
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }catch (ArrayIndexOutOfBoundsException a) {
                                                    log.setText("Missing parameter in command");
                                                }
                                            } else {
                                                log.setText("Looks like you used a non existent command");
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        }
                                    // choice of interaction with an npc    
                                    } else if (cho[0].equalsIgnoreCase("talk")) {
                                        try{
                                            Npc temp = dungeon[player.getX()][player.getY()].isNpcInRoom(cho[1]);
                                            if (temp == null) {
                                                log.setText("This person is not in the room!");
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            } else {
                                                temp = Interaction(temp, log, display, false);
                                                if (temp != null) {
                                                    dungeon[player.getX()][player.getY()].updateNpc(temp);
                                                    log.setText(player.pickUp(new KeyItem("boss key")));
                                                }
                                            }
                                        }catch (ArrayIndexOutOfBoundsException a){
                                            log.setText("Missing parameter in command");
                                        }
                                    // choice of giving an item to a specific npc  
                                    } else if (cho[0].equalsIgnoreCase("give")) {
                                        try {
                                            String[] a = cho[1].split(" ", 2);
                                            Npc temp = dungeon[player.getX()][player.getY()].isNpcInRoom(a[1]);
                                            Item b = player.checkInventory(a[0]);
                                            if (temp == null) {
                                                log.setText("This person is not in the room!");
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            } else if (b == null) {
                                                log.setText("You don't have this item");
                                            } else if (b.getName().equalsIgnoreCase("bandages")) {
                                                log.setText(player.discardItem(b));
                                                temp.setQuestCompleted();
                                                log.setText(player.pickUp(new KeyItem("rusty key")));
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                dungeon[player.getX()][player.getY()].updateNpc(temp);
                                            } else {
                                                log.setText("You can't give this item!");
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        }catch (ArrayIndexOutOfBoundsException a) {
                                            log.setText("Missing parameter in command");}
                                        // choice of saving the match
                                    } else if (cho[0].equalsIgnoreCase("save")) {
                                        saveFile.save(player, dungeon);
                                        log.setText("<html><center>" + "Saved successfully" + "<center><html>");
                                        // choice of inspecting an item in the room: returns description if present
                                    } else if (cho[0].equalsIgnoreCase("inspect")) {
                                        try {
                                            Item item = dungeon[player.getX()][player.getY()].isInRoom(cho[1]);
                                            if (item == null) {
                                                log.setText("This item isn't in the room!");
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            } else {
                                                log.setText(item.getDescription());
                                                try {
                                                    sleep(2000);
                                                } catch (InterruptedException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        }catch (ArrayIndexOutOfBoundsException a) {
                                            log.setText("Missing parameter in command");}
                                     // request of help   
                                    } else if (cho[0].equalsIgnoreCase("help")) {
                                        log.setText("<html><center>" + "====================================================<br>" +
                                                "Here is a list of commands you can use:<br>"
                                                + "-list: (To show a list of the items, directions and npcs of the room)<br>"
                                                + "-north/south/east/west: (To go to another room)<br><br>"
                                                + "-inventory: (To open the inventory, more commands once opened)<br>"
                                                + "-take *item name*: (To pick an item)<br><br>"
                                                + "-open *direction*: (To open a locked door in that direction)<br>"
                                                + "-talk *npc name*: (To interact with an npc)<br><br>"
                                                + "-give *item name* *npc name*: (To give an item to an npc)<br><br>"
                                                + "-save: to save the game<br>"
                                                + "-inspect *item name*: to get the description of an item" + "<center><html>"
                                                + "-exit: to quit<br>");
                                        // possibility of opening a locked door if the player has its key
                                    } else if (cho[0].equalsIgnoreCase("open")) {
                                        try{
                                        if (dungeon[player.getX()][player.getY()].HasDirection(cho[1]) && dungeon[player.getX()][player.getY()].isLocked(cho[1])) {
                                            if (dungeon[player.getX()][player.getY()].getId() == 6) {

                                                Item test = player.checkInventory("rusty key");
                                                if (test != null) {
                                                    dungeon[player.getX()][player.getY()].SetLocked(cho[1]);
                                                    log.setText(player.discardItem(test));
                                                    log.setText("You opened the locked door!");
                                                    try {
                                                        sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                                else{
                                                    log.setText("You need rusty key to open this door");
                                                    try {
                                                        sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                            } else if (dungeon[player.getX()][player.getY()].getId() == 12) {
                                                Item test = player.checkInventory("boss key");
                                                if (test != null) {
                                                    dungeon[player.getX()][player.getY()].SetLocked(cho[1]);
                                                    log.setText(player.discardItem(test));
                                                    log.setText("You opened the locked door!");
                                                    try {
                                                        sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                                else{
                                                    log.setText("You need a boss key to open this door");
                                                    try {
                                                        sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                            }
                                        } else {
                                            log.setText("You can't do that!");
                                            try {
                                                sleep(2000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }catch (ArrayIndexOutOfBoundsException a) {
                                            log.setText("Missing parameter in command");}
                                    } else if (cho[0].equalsIgnoreCase("back")) {
                                        player.moveBack();
                                        if (!player.hasMoved()) {
                                            isRunning = randomEncounter(dungeon[player.getX()][player.getY()].getTier(), player, log, display, choice, false);
                                        }
                                    } else if (cho[0].equalsIgnoreCase("exit")) {
                                        System.exit(0);
                                    } else {
                                        log.setText("this command doesn't exist, digit 'help' for a list of commands");
                                        try {
                                            sleep(2000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    buttonPressed = false;
                                } else {
                                    synchronized (submit) {
                                        try {
                                            submit.wait();
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                            gameEnd(player, log);

                    }
                    //
                };
                t1.start();
            }catch(ArrayIndexOutOfBoundsException e) {
                log.setText("Missing parameter in command");
            }
    }

    // function to interact with npcs: checks if the quest is completed or the necessary conditions are satisfied to set it as completed
    public static Npc Interaction(Npc npc, JLabel log, JLabel display, Boolean test) {
        if (npc.getName().equalsIgnoreCase("injured knight")) {
            if (!npc.isQuestCompleted()) {
                display.setText(Ascii.knight);
                log.setText("<html><center>"+"Oh, fellow adventurer, I can see that you're also on a journey to defeat the demon of hatred<br>"
                + "Unfortunately, he was too strong for me, I've lost my left arm and I can't even walk<br>"
                + "I can give you the key to the boss room, but you'll have to give me something in exchange<br>"
                + "while running away i lost my set of Bandages, please bring them to me, I'm not ready to die yet"+"<center><html>");
                synchronized (submit) {
                    if(!test){
                    try {
                        submit.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                }
                display.setText("");
            } else {
                log.setText("Thank you for bringing me the Bandages, I can live for a little longer now");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                display.setText("");//##########################################################################################################
            }
            // 1st npc: injured knight receive bandages
        } else if (npc.getName().equalsIgnoreCase("patches")) {
            if (!npc.isQuestCompleted()) {
                display.setText(Ascii.patches);
                log.setText("<html><center>"+"Ah, what do we have here, another adventurer blinded by the greed for fame and fortune<br>"
                + "Do you truly think that you'll be able to defeat the demon of hatred? Don't make me laugh<br>"
                + "I can give you the key to open this gate, but you'll have to answer my question<br>"
                + "The demon was sealed in its chamber by a secret cult of mages, which was their secret password?"+"<center><html>");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (submit) {
                    try {
                        submit.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 2nd npc: patches receives correct answer to his riddle
                if (choice.equalsIgnoreCase("fear the old blood")) {
                    npc.setQuestCompleted();
                    log.setText("<html><center>"+"Oh... Hihihi thank you, take this key, I don't need it"
                    + "Instead, I can finally use this password in the secret treasure room that I found so I can finally be Rich"+"<html><center>");
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    display.setText("");
                    return npc;
                } else {
                    log.setText("Don't try to trick me, this could never be the password");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                log.setText("leave me alone, I won't share anything with a greedy adventurer like you");
            }
            display.setText("");

        }
        return null;
    }

    // text intro of the game
    public static void GameIntro(JLabel label){

        label.setText("<html><center>" + "Once upon a time, in a far land, a demon summoned from the collective hate of humanity<br>" +
        "used to spread chaos all over the continent. One day a powerful cult managed to eventually<br>" +
        "seal the demon... however his power are now slowly coming back. His days of imprisonment are<br>" +
        "coming to an end and countless proud adventurers have tried to take on the dungeon<br>" +
        "to finally put an end to his life... without success however. This was until the day a great<br>" +
        "hero entered the dungeon, if I remember right his name was...<br><br><br><br><br><br><br>" +
        "Insert Your Name: <center><html>");
    }

}
