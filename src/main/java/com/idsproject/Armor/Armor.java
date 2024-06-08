package com.idsproject.Armor;
import com.idsproject.Items.Item;

/*
Class Armor: creates 4 different type of armors (by passing the name in the implicit parameter), 
can return their armor points and set if the armor is equipped in the specific slot or not
*/

public class Armor extends Item {
    protected int armorPoints;

    public static final int RATHALOS_ARMOR_HP = 30;
    public static final int NECROMANCER_CLOACK_HP = 40;
    public static final int ELITE_KNIGHT_ARMOR_HP = 20;
    public static final int BERSERKER_ARMOR_HP = 50;

    public Armor(String type)
    {
        if (type == null) return;
        isArmor=true;
        name = type;
        equipped=false;
        canPickUp=true;

        switch (type)
        {

            case "Rathalos Armor":
                armorPoints = RATHALOS_ARMOR_HP;
                weight = 15;
                description = "An armor made from the scales of an apex monster of the Ancient Forest, also known as the King of the Skies";
                break;

            case "Necromancer Cloack":
                armorPoints = NECROMANCER_CLOACK_HP;
                weight = 7;
                description = "A cloack that represent death itself, extremely durable and very light";
                break;

            case "Elite Knight Armor":
                armorPoints = ELITE_KNIGHT_ARMOR_HP;
                weight = 20;
                description = "An armor made from the finest of steels, symbol of a knight of great status";
                break;

            case "Berserker Armor":
                armorPoints = BERSERKER_ARMOR_HP;
                weight = 25;
                description = "An armor that feeds off the rage of the user, extremely heavy and very resistant to attacks";
                break;

            default:
                name = "Ordinary Clothes";
                armorPoints = 0;
                weight = 0;
                description = "Normal clothes, nothing special";
                break;
        } // end of switch (type)
    }

    public int getArmorPoints()
    {
        return armorPoints;
    }

    public void setEquipped(){
        if(equipped)
            equipped=false;
        else
            equipped=true;
    }

}
