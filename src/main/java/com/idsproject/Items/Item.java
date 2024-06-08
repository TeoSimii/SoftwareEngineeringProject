package com.idsproject.Items;

/*
Class Item: creates the basis for derivative classes of items. It doesn't define constructors
but only variables that establish if an item is pickable/usable/equipped or explain properties
*/

public class Item {
    protected String name;
    protected String description;
    protected int weight;
    protected boolean canUse;
    protected boolean canPickUp;
    protected boolean heals;
    protected boolean equipped=false;
    protected int stack=1;
    protected boolean canDiscard=true;
    protected boolean isArmor=false;
    protected boolean isWeapon=false;

    //getter

    public String getName() {
        return name;
    }

    public boolean canDiscard(){
        return canDiscard;
    }

    public boolean isArmor(){
        return isArmor;
    }

    public boolean isWeapon(){
        return isWeapon;
    }

    public String getDescription() {
        return description;
    }
    public int getWeight() {
        return weight;
    }

    public boolean isPickable() {
        return canPickUp;
    }

    public boolean isEquipped(){
        return equipped;
    }

    public int getStack(){
        return stack;
    }

    public void ItemCountSet(String s){
        if (s.equalsIgnoreCase("decrement"))
            stack--;
        else{
            stack++;
        }
    }


    public boolean getHeals(){
        return heals;
    }
}
