package com.idsproject.Weapons;
import com.idsproject.Items.Item;

/*
Class Weapon: like Armor, creates weapons based on their type, returns variables and sets weapons as equipped in the specific slot
*/

public class Weapon extends Item {
    // ogni arma fa un certo danno
    protected int attackDamage;

    public static final int BUTTERFLY_KNIFE_DAMAGE = 5;
    public static final int ARTORIAS_SWORD = 15;
    public static final int MORTAL_BLADE_DAMAGE = 20;
    public static final int MOONLIGHT_SWORD_DAMAGE = 35;

    public Weapon(String type)
    {
        if (type == null) return;

        name = type;
        equipped=false;
        isWeapon=true;
        canPickUp=true;

        switch (type)
        {

            case "Butterfly Knife":
                attackDamage = BUTTERFLY_KNIFE_DAMAGE;
                weight = 2;
                description = "A Butterfly Knife, it may not be much, but it's a start";
                break;

            case "Moonlight Sword":
                attackDamage = MOONLIGHT_SWORD_DAMAGE;
                weight = 12;
                description = "A sword imbued with the light of the moon, able to slay even the strongest demon";
                break;

            case "Artoria's Sword":
                attackDamage = ARTORIAS_SWORD;
                weight = 16;
                description = "A sword that was once wielded by the greatest knight in history";
                break;

            case "Mortal Blade":
                attackDamage = MORTAL_BLADE_DAMAGE;
                weight = 10;
                description = "A legendary katana from a far land, able to severe immortality";
                break;

            default:
                name = "notWeapon";
                attackDamage = BUTTERFLY_KNIFE_DAMAGE;
                weight = 2;
                description = "A Butterfly Knife, it may not be much, but it's a start";
                break;
        } // end of switch (type)
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setEquipped(){
        if(equipped)
            equipped=false;
        else
            equipped=true;
    }
}
