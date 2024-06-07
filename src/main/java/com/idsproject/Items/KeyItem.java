package com.idsproject.Items;

/*
Class KeyItem: extending Item, creates key items establishing their properties and features
*/

public class KeyItem extends Item {

    public KeyItem(String type)
    {
        if (type == null) return;

        name = type;
        heals= false;


        switch (type.toLowerCase())
        {

            case "boss key":
                canUse=false;
                canPickUp=true;
                weight = 0;
                canDiscard=false;
                description = "The key to the boss room, be ready";
                break;

            case "rusty key":
                canUse=false;
                canPickUp=true;
                weight = 0;
                canDiscard=false;
                description = "A rusty key, might be used to unlock a gate";
                break;

            case "old skull":
                canUse=false;
                canPickUp=true;
                weight = 2;
                description = "I... I don't even know why you picked it up, it's just a skull";
                break;

            case "corpse":
                canUse=false;
                canPickUp=false;
                weight = 999;
                description = "The corpse of a soldier, may his soul return to the great flame";
                break;

            case "the emperor":
                canUse=true;
                canPickUp=true;
                weight = 5;
                description = "A special totem, might teleport you directly inside of the boss room or back to the entrance. \nBe ready to gamble with fate";
                break;

            case "pebble":
                canUse=false;
                canPickUp=true;
                weight = 1;
                description = "Can you stop picking up useless things? It's literally just a pebble";
                break;

            case "gold coin":
                canUse=false;
                canPickUp=true;
                weight = 1;
                description = "uhhhh, shiny!! :)";
                break;

            case "bandages":
                canUse=false;
                canPickUp=true;
                weight = 1;
                description = "A pile of bandages, might help someone in need";
                break;

            case "damaged scroll":
                canUse=false;
                canPickUp=true;
                weight = 0;
                canDiscard=false;
                description = "A scroll that once belonged to an ancient cult, has something written on it: Fear the old blood";
                break;

            default:
                name = "notItem";
                canUse=false;
                canPickUp=true;
                weight = 0;
                description = "Skill issue, you messed up the code";
                break;
        } // end of switch (type)
    }

}
