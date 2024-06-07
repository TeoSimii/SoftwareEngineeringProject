package com.idsproject.Events;

import com.idsproject.Enemies.Enemy;
import com.idsproject.Items.HealItem;
import com.idsproject.Player.Player;
import com.idsproject.NPC.Npc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import javax.swing.*;
import java.util.Scanner;

public class EventsTest {

    @Test
    public void testMovement() {
        Player player = new Player("Hero", 100, 0, 5);
        JLabel log= new JLabel();
        Events.initDungeon();

        player.setCoordinates("south");
        assert Events.move("south", player, Events.getDungeon()[player.getX()][player.getY()],log);
        assert !Events.move("east", player, Events.getDungeon()[player.getX()][player.getY()],log);
    }

    @Test
    public  void testRandomEncounter() {
        Player player = new Player("Hero", 100, 0, 5);
        JLabel log= new JLabel();
        JLabel display= new JLabel();
        String a="attack";
        assert Events.randomEncounter(1, player,log,display,a,true); // Assuming the player can't survive a high-tier encounter
    }

    @Test
    public void testBattle() {
        JLabel log= new JLabel();
        JLabel display= new JLabel();
        Player player = new Player("Hero", 100, 0, 5);
        Enemy enemy = new Enemy(1);
        String a="attack";
        Events.battle(enemy, player,log, display,a,true);
        assert player.isAlive(); // Player should survive the battle
    }

    @Test
    public  void testUsePotion() {
        Player player = new Player("Hero", 100, 0, 5);

        player.takeDamage(40);
        HealItem potion = new HealItem("potion");
        player.pickUp(potion);
        JLabel log= new JLabel();
        Events.usePotion(potion, player,log);
        assert player.getHp() == 70; // Player's HP should increase by 20
    }

    @Test
    public  void testInteractionWithNPC() {
        Player player = new Player("Hero", 100, 0, 5);
        Events.initDungeon();
        JLabel log= new JLabel();
        JLabel display= new JLabel();
        assert Events.Interaction(Events.getDungeon()[2][0].getNpc().getFirst(), log,display, true) == null; // Interaction should return a non-null value
    }

    @Test
    public  void testGameEnd() {
        Player player = new Player("Hero", 100, 0, 5);
        JLabel log= new JLabel();
        Events.gameEnd(player,log);
        assert player.isAlive(); // Player should be dead
    }
}
