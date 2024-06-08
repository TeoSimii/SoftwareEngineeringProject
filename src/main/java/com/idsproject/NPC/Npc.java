package com.idsproject.NPC;

/*
Class Npc: creates Npcs, returns/set their variables and the state of the linked quest
*/

public class Npc {
    protected String name;
    protected boolean QuestCompleted=false;

    public Npc() {}

    public Npc(String type)
    {
        if (type == null) return;

        name = type;

        switch (type.toLowerCase())
        {

            case "injured knight":
                break;

            case "patches":
                break;

        } // end of switch (type)
    }

    public String getName() {
        return name;
    }

    public boolean isQuestCompleted(){
        return QuestCompleted;
    }

    public void setQuestCompleted(){
        QuestCompleted= !QuestCompleted;
    }
}
