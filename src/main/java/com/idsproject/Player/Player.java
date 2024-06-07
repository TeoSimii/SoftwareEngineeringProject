package com.idsproject.Player;

import com.idsproject.Items.Item;
import com.idsproject.Weapons.Weapon;
import com.idsproject.Armor.Armor;

import javax.swing.*;
import java.util.ArrayList;

public class Player{
    protected String name;
    protected int hp;
    protected int maxHp = 100;
    protected int score;
    protected int weight;
    static final int maxWeight = 50;
    protected int attackDamage=5;
    protected boolean equippedWeapon;
    protected boolean equippedArmor;
    protected int x=0;
    protected int y=0;
    protected int prex=0;
    protected int prey=0;

    ArrayList<Item> inventory = new ArrayList<Item>();

    // constructor of Player with its attributes
    public Player(String Answername, int InitmaxHp, int score, int attackDamage) {
        name=Answername;
        hp=maxHp;
        maxHp=InitmaxHp;
        this.score = 0;
        this.weight = 0;
        this.attackDamage = attackDamage;
        this.equippedWeapon = false;
        this.equippedArmor = false;
    }

    // various getter
    public int attack() {
        return attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    // adder of hps
    public void healHp (int value) {
        hp=hp+value;
    }

    // adder of the score
    public void addScore (int value) {
        score=score+value;
    }

    // diminish hps
    public void takeDamage(int damage){
        hp=hp-damage;
    }

    // Metodi Get
    public int getHp () {
        return hp;
    }
    public int getScore() {
        return score;
    }
    public int getWeight() {
        return this.weight;
    }
    public int getAttackDamage() {
        return attackDamage;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getPreX(){ return prex;}
    public int getPreY(){ return prey;}

    //Metodi Set
    public void setName(String name){this.name = name;}
    public void setHp (int hp) {
        this.hp=hp;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    // clears inventory
    public void clearInventory() {
        this.inventory.clear();
    }

    // various setter
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPreX(int prex) {
        this.prex = prex;
    }
    public void setPreY(int prey) {
        this.prey = prey;
    }

    // 4 methods of equip/unequip of weapon/armor
    public String equipWeaponInSlot (Weapon w) {
        if (!equippedWeapon) {
            equippedWeapon = true;
            w.setEquipped();
            attackDamage += w.getAttackDamage();
            return "You successfully equipped "+ w.getName() + "\n";
        }
        else {
            return "You cannot equip another weapon, you have to unequip another before. Try with 'unequip weapon' ";
        }
    }

    public String unequipWeaponInSlot(Weapon w) {
        if (equippedWeapon) {
            equippedWeapon=false;
            w.setEquipped();
            attackDamage -= w.getAttackDamage();
            return "You successfully unequipped "+ w.getName() + "\n";
        }
        else {
            return "You have no weapon equipped";
        }
    }

    public String equipArmorInSlot (Armor a) {
        if (!equippedArmor) {
            equippedArmor = true;
            a.setEquipped();
            hp += a.getArmorPoints();
            maxHp += a.getArmorPoints();
            return "You successfully equipped "+ a.getName() + "\n";
        }
        else {
            return "You cannot equip another armor, you have to unequip another before. Try with 'unequip armor'";
        }
    }

    public String unequipArmorInSlot(Armor a) {
        String out="";
        if (equippedArmor) {
            equippedArmor=false;
            a.setEquipped();
            maxHp -= a.getArmorPoints();
            hp-=a.getArmorPoints();
            out += "You successfully unequipped "+ a.getName() + "\n";
            // when armor is disequipped, hps can drop to 0 or remain over maxHp if exepctions are not well managed
            if (hp > maxHp) {
               hp=maxHp;
            } else if(hp<0){
                hp=1;
            }
        }
        else {
            return "You have no armor equipped";
        }
        return out;
    }

    // sets previous and actual coordinates
    public void setCoordinates(String direction){
        if(direction.equalsIgnoreCase("north")){
            prex=x;
            x--;
        }
        else if(direction.equalsIgnoreCase("south")){
            prex=x;
            x++;
        }
        else if(direction.equalsIgnoreCase("west")){
            prey=y;
            y--;
        }
        else if(direction.equalsIgnoreCase("east")){
            prey=y;
            y++;
        }
    }

    // verifies if previous and actual coordinates match
    public boolean hasMoved(){
        if(x==prex && y==prey){
            return true;
        }
        else return false;
    }

    // method for key item "The emperor" that teleport Player to the boss room
    public void teleport(String s){
        if(s.equalsIgnoreCase("boss")){
            prex=x;
            prey=y;
            x=3;
            y=3;
        } else{
            prex=x;
            prey=y;
            x=0;
            y=0;
        }
    }

    // makes the player move back to the previous room
    public String moveBack(){
        String out="";
        if(y==prey && x==prex){
            out += "You never moved from the room!";
        } else{
            int temp=x;
            int tempy=y;
            y=prey;
            x=prex;
            prex=temp;
            prey=tempy;
        }
        return out;
    }

    // after check for weight and possibility to pick up, this method lets the player take from the room an item
    public String pickUp(Item item){

        String out = "";
        if (item.getWeight() + weight > maxWeight || !item.isPickable()) {
            out +=item.getName() + "not picked up!";
            out += "<br>You can't carry this item";
        } else {
            if(inventory.contains(item)){
                int temp;
                temp = inventory.indexOf(item);
                Item TempItem=inventory.get(temp);
                TempItem.ItemCountSet("increment");
                inventory.set(temp,TempItem);
                weight += item.getWeight();
            }
            else {
                inventory.add(item);
                weight += item.getWeight();
                out += "You picked up: " + item.getName();
            }
        }
        return out;
    }

    // method to return the inventory
    public String ShowInventory(){
        String inv = "";
        for(int i=0;i<inventory.size();i++){
            inv += inventory.get(i).getName()+ ", ";
        }
        return inv;
    }

    // checks if player is still alive
    public boolean isAlive () {
        if(hp > 0)
            return true;
        else
            return false;
    }

    // checks if inventory contains a certain item
    public Item checkInventory(String s){
        for(int i=0;i<inventory.size();i++){
            if(inventory.get(i).getName().equalsIgnoreCase(s)){
                return inventory.get(i);
            }
        }
        return null;
    }

    // discard an item from the inventory
    public String discardItem (Item item){ //modifica peso
        String out="";
        if(inventory.contains(item)){
            int temp;
            temp = inventory.indexOf(item);
            Item TempItem=inventory.get(temp);
            if(item.isEquipped()){
                out += "You must unequip this item before you discard it!";
            }
            else if(TempItem.getStack()==1){
                inventory.remove(item);
                weight -= item.getWeight();
            }
            else{
                TempItem.ItemCountSet("decrement");
                inventory.set(temp,TempItem);
                weight -= item.getWeight();
            }
        }
        else{
            out += "You don't have that item!";
        }
        return out;
    }


}
