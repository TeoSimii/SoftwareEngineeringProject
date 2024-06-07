package com.idsproject.Items;

/*
Class HealItem: extending Item, creates healing items and returns their healing power
*/

public class HealItem extends Item {
    protected int healingPower;

    public HealItem(String type)
    {
        if (type == null) return;

        name = type;
        canUse = true;
        canPickUp=true;
        heals= true;


        switch (type.toLowerCase())
        {


            case "potion":
                healingPower=10;
                weight = 1;
                description = "A bittersweet basic potion, restores 10Hp";
                break;

            case "estus flask":
                healingPower=20;
                weight = 2;
                description = "A potion from a far land, restores 20Hp";
                break;

            case "old potion":
                healingPower=-15;
                weight = 2;
                description = "Looks quite old, but it might still work... I think?";
                break;

            default:
                name = "notHealItem";
                healingPower=10;
                weight = 1;
                description = "A basic potion, restores 10Hp";
                break;
        } // end of switch (type)
    }

    public int getHealingPower(){
        return healingPower;
    }

}
