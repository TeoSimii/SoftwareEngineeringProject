package com.idsproject.Enemies;

import java.util.Random;

/*
Class Enemy: creates enemies and permits to see their variables
*/

public class Enemy
{

    protected int Hp;
    protected String Name;
    protected int mobTier;
    protected int Given_Score;
    protected int maxHp;

    protected Random RANDOM = new Random();

    public static final String[] TIER1_ENEMY_NAMES = { "Slime","Clay puppet"};
    public static final String[] TIER2_ENEMY_NAMES = { "Ghoul","Puppeteer"};
    public static final String[] TIER3_ENEMY_NAMES = { "Golem","Sanctuary Guardian"};
    public static final String[] TIER4_ENEMY_NAMES = { "Basilisk","Shadow of Yahrnam"};
    public static final String Boss="Demon of Hatred";

    public static final int TIER1_MAXIMUM_HEALTH = 15;
    public static final int TIER2_MAXIMUM_HEALTH = 30;
    public static final int TIER3_MAXIMUM_HEALTH = 40;
    public static final int TIER4_MAXIMUM_HEALTH = 60;
    public static final int BOSS_MAXIMUM_HEALTH = 150;

    public static final int TIER1_ATTACK = 7;
    public static final int TIER2_ATTACK = 10;
    public static final int TIER3_ATTACK = 15;
    public static final int TIER4_ATTACK = 25;
    public static final int BOSS_ATTACK = 35;


    //Costruttore nemici di Tier1
    public Enemy()
    {
        /* Fetch a random name from the list of enemies. */
        Name = TIER1_ENEMY_NAMES[RANDOM.nextInt(TIER1_ENEMY_NAMES.length)];
        /* Give the enemy a random health */
        Hp = RANDOM.nextInt(TIER1_MAXIMUM_HEALTH);
        maxHp=Hp;

        /* Give the enemy a score */
        Given_Score = 100;
        mobTier=1;
    } // end of constructor Enemy()

    //Costruttore nemici di Altri Tier
    public Enemy(int tier)
    {
        if(tier==1){
            Name = TIER1_ENEMY_NAMES[RANDOM.nextInt(TIER1_ENEMY_NAMES.length)];
            Hp = RANDOM.nextInt(TIER1_MAXIMUM_HEALTH+1-5)+5;
            maxHp=Hp;
            Given_Score = 100;
            mobTier=1;
        }


        else if(tier==2){
            Name = TIER2_ENEMY_NAMES[RANDOM.nextInt(TIER2_ENEMY_NAMES.length)];
            Hp = RANDOM.nextInt(TIER2_MAXIMUM_HEALTH+1-15)+15;
            maxHp=Hp;
            Given_Score = 200;
            mobTier=2;
        }

        else if(tier==3){
            Name = TIER3_ENEMY_NAMES[RANDOM.nextInt(TIER3_ENEMY_NAMES.length)];
            Hp = RANDOM.nextInt(TIER3_MAXIMUM_HEALTH+1-30)+30;
            maxHp=Hp;
            Given_Score = 300;
            mobTier=3;
        }

        else if(tier==4){
            Name = TIER4_ENEMY_NAMES[RANDOM.nextInt(TIER4_ENEMY_NAMES.length)];
            Hp = RANDOM.nextInt(TIER4_MAXIMUM_HEALTH +1 -40)+40;
            maxHp=Hp;
            Given_Score = 400;
            mobTier=4;
        }

        else{
            Name = Boss;
            Hp = BOSS_MAXIMUM_HEALTH;
            maxHp=Hp;
            mobTier=5;
            Given_Score = 1000;
        }
    } // end of constructor Enemy()

    //Metodi generazione attacco nemici
    public int attack(int Tier)
    {
        if(Tier==1)
            return RANDOM.nextInt(TIER1_ATTACK+1-3)+3;
        else if(Tier==2)
            return RANDOM.nextInt(TIER2_ATTACK+1-5)+5;
        else if(Tier==3)
            return RANDOM.nextInt(TIER3_ATTACK+1-8)+8;
        else if(Tier==4)
            return RANDOM.nextInt(TIER4_ATTACK+1-15)+15;
        else
            return RANDOM.nextInt(BOSS_ATTACK+1-20)+20;

    } // end of method attack()


    //Metodo danni subiti, damage sarà il danno generato dall'attacco del player
    public void takeDamage(int damage)
    {
        Hp = Hp - damage;
    } // end of method takeDamage(int damage)

    public String getname()
    {
        return Name;
    } 

    public int gethealth()
    {
        return Hp;
    } 

    public int getMaxHp()
    {
        return maxHp;
    }

    public int getscore()
    {
        return Given_Score;
    }

    public int getTier()
    {
        return mobTier;
    }

}
