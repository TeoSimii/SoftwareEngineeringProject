package com.idsproject.Rooms;

import com.idsproject.Items.Item;
import com.idsproject.Items.KeyItem;
import com.idsproject.Items.HealItem;
import com.idsproject.NPC.Npc;
import com.idsproject.Weapons.Weapon;
import com.idsproject.Armor.Armor;

import javax.swing.*;
import java.util.ArrayList;

// class to create rooms, with list of every different type of entity inside
public class Room {
    protected int tier;
    protected int id=99;
    protected ArrayList<Item> items = new ArrayList<Item>();
    protected ArrayList<String> connectedRooms = new ArrayList<String>();
    protected ArrayList<Npc> npcs= new ArrayList<Npc>();
    protected boolean northLocked;
    protected boolean southLocked;
    protected boolean eastLocked;
    protected boolean westLocked;
    
    

    public Room()
    {}

// constructor where every room is set with its items and characters
    public Room(int index)
    {
        northLocked=false;
        southLocked=false;
        eastLocked=false;
        westLocked=false;

        switch (index)
        {

            case 0:
                items.add(new Weapon("Butterfly Knife"));
                items.add(new KeyItem("pebble"));
                items.add(new KeyItem("old skull"));
                id=0;
                connectedRooms.add("south");
                tier=1;
                break;

            case 1:
                items.add(new Armor("Rathalos Armor"));
                items.add(new KeyItem("corpse"));
                items.add(new KeyItem("old skull"));
                items.add(new KeyItem("gold coin"));
                id=1;
                connectedRooms.add("south");
                tier=2;
                break;

            case 4:
                items.add(new HealItem("potion"));
                items.add(new KeyItem("pebble"));
                items.add(new Armor("Elite Knight Armor"));
                id=4;
                tier=1;
                connectedRooms.add("south");
                connectedRooms.add("north");
                connectedRooms.add("east");
                break;

            case 5:
                items.add(new KeyItem("bandages"));
                items.add(new KeyItem("corpse"));
                id=5;
                tier=1;
                connectedRooms.add("west");
                connectedRooms.add("north");
                connectedRooms.add("east");
                break;

            case 6:
                items.add(new KeyItem("old skull"));
                items.add(new KeyItem("corpse"));
                items.add(new HealItem("old potion"));
                id=6;
                tier=2;
                connectedRooms.add("west");
                connectedRooms.add("south");
                connectedRooms.add("east");
                eastLocked=true;
                break;

            case 7:
                items.add(new KeyItem("pebble"));
                npcs.add(new Npc("patches"));
                items.add(new Armor("Berserker Armor"));
                id=7;
                tier=3;
                connectedRooms.add("west");
                connectedRooms.add("south");
                break;

            case 8:
                items.add(new HealItem("estus flask"));
                npcs.add(new Npc("injured knight"));
                id=8;
                tier=2;
                connectedRooms.add("north");
                connectedRooms.add("south");
                break;

            case 10:
                items.add(new KeyItem("corpse"));
                items.add(new KeyItem("the emperor"));
                items.add(new Weapon("Artoria's Sword"));
                items.add(new KeyItem("gold coin"));
                id=10;
                tier=3;
                connectedRooms.add("north");
                break;

            case 11:
                items.add(new KeyItem("damaged scroll"));
                items.add(new Weapon("Mortal Blade"));
                id=11;
                tier=4;
                connectedRooms.add("north");
                break;

            case 12:
                items.add(new KeyItem("old skull"));
                items.add(new KeyItem("gold coin"));
                id=12;
                tier=3;
                connectedRooms.add("north");
                connectedRooms.add("east");
                eastLocked=true;
                break;

            case 13:
                items.add(new Weapon("Moonlight Sword"));
                items.add(new KeyItem("gold coin"));
                id=13;
                tier=4;
                connectedRooms.add("west");
                connectedRooms.add("east");
                break;

            case 14:
                items.add(new Armor("Necromancer Cloack"));
                items.add(new KeyItem("gold coin"));
                id=14;
                tier=4;
                connectedRooms.add("west");
                connectedRooms.add("east");
                break;

            case 15:
                id=15;
                tier=5;
                connectedRooms.add("east");
                break;

            default:
                break;
        } // end of switch (type)
    }

// different getters for variables
    public int getId(){
        return id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Npc> getNpc() {
        return npcs;
    }

// setters
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    //checks if the direction is free to go
    public boolean HasDirection(String s){
        return connectedRooms.contains(s);
    }

    // set that direction as locked
    public void SetLocked(String s){
        if(s.equalsIgnoreCase("north")){
            northLocked=!northLocked;
        }

        else if(s.equalsIgnoreCase("south")){
            southLocked=!southLocked;
        }

        else if(s.equalsIgnoreCase("east")){
            eastLocked=!eastLocked;
        }

        else if (s.equalsIgnoreCase("west")){
            westLocked=!westLocked;
        }
    }

    public int getTier(){
        return tier;
    }

    // list of items in the room
    public void showItems(JLabel log){
        String out="<html><center>"+"=============Area Information==============<br>";

        out +="Items: <br>";
        for(int i=0;i<items.size();i++)
            out += items.get(i).getName()+"<br>";

        out +="Npcs: <br>";
        for(int i=0;i<npcs.size();i++)
            out += npcs.get(i).getName()+"<br>";

        out += "Doors: <br>";
        for(int i=0;i<connectedRooms.size();i++)
            out += connectedRooms.get(i)+"<br>";

        out += "<center><html>";

        log.setText(out);
    }

    // checks if item is in the room
    public Item isInRoom(String s){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getName().equalsIgnoreCase(s)){
                return items.get(i);
            }
        }
        return null;
    }

    // checks if npc is in the room
    public Npc isNpcInRoom(String s){
        for(int i=0;i<npcs.size();i++){
            if(npcs.get(i).getName().equalsIgnoreCase(s)){
                return npcs.get(i);
            }
        }
        return null;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void removeAllItem(){
        items.clear();
    }

    // return boolean value indicating if a direction is locked or not
    public boolean isLocked(String s){
        if(s.equalsIgnoreCase("north")){
            return northLocked;
        }

        else if(s.equalsIgnoreCase("south")){
            return southLocked;
        }

        else if(s.equalsIgnoreCase("east")){
            return eastLocked;
        }

        else{
            return westLocked;
        }

    }


    public void updateNpc(Npc npc){
        npcs.remove(0);
        npcs.add(npc);
    }
}
