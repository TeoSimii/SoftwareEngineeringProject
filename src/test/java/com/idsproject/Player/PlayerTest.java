package com.idsproject.Player;

import com.idsproject.Items.HealItem;
import com.idsproject.Weapons.Weapon;
import com.idsproject.Armor.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private Weapon weapon;
    private Armor armor;
    private HealItem item;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer", 100, 0, 5);
        weapon = new Weapon("Butterfly Knife");
        armor = new Armor("Rathalos Armor");
        item = new HealItem("potion");
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(100, player.getMaxHp());
        assertEquals(5, player.getAttackDamage());
        assertEquals(0, player.getScore());
        assertEquals(0, player.getWeight());
        assertTrue(player.isAlive());
        assertTrue(player.getInventory().isEmpty());
    }

    @Test
    public void testHealHp() {
        player.takeDamage(50);
        player.healHp(30);
        assertEquals(80, player.getHp());
    }

    @Test
    public void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore());
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(30);
        assertEquals(70, player.getHp());
    }

    @Test
    public void testEquipWeapon() {
        player.equipWeaponInSlot(weapon);
        assertTrue(player.equippedWeapon);
        assertEquals(10, player.getAttackDamage());
    }

    @Test
    public void testUnequipWeapon() {
        player.equipWeaponInSlot(weapon);
        player.unequipWeaponInSlot(weapon);
        assertFalse(player.equippedWeapon);
        assertEquals(5, player.getAttackDamage());
    }

    @Test
    public void testEquipArmor() {
        player.equipArmorInSlot(armor);
        assertTrue(player.equippedArmor);
        assertEquals(130, player.getHp());
        assertEquals(130, player.getMaxHp());
    }

    @Test
    public void testUnequipArmor() {
        player.equipArmorInSlot(armor);
        player.unequipArmorInSlot(armor);
        assertFalse(player.equippedArmor);
        assertEquals(100, player.getMaxHp());
        assertEquals(100, player.getHp());
    }

    @Test
    public void testPickUpItem() {
        player.pickUp(item);
        assertTrue(player.getInventory().contains(item));
        assertEquals(1, player.getWeight());
    }

    @Test
    public void testDiscardItem() {
        player.pickUp(item);
        player.discardItem(item);
        assertFalse(player.getInventory().contains(item));
        assertEquals(0, player.getWeight());
    }

    @Test
    public void testMove() {
        player.setCoordinates("north");
        assertEquals(-1, player.getX());
        assertEquals(0, player.getY());

        player.setCoordinates("south");
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());

        player.setCoordinates("east");
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());

        player.setCoordinates("west");
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    public void testTeleport() {
        player.teleport("boss");
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());

        player.teleport("start");
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    public void testMoveBack() {
        player.setCoordinates("north");
        player.moveBack();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    public void testCheckInventory() {
        player.pickUp(item);
        assertNotNull(player.checkInventory("potion"));
        assertNull(player.checkInventory("Non-existent Item"));
    }

    @Test
    public void testShowInventory() {
        player.pickUp(item);
        player.ShowInventory();
        // Manually verify the printed output for items in inventory
    }

    @Test
    public void testIsAlive() {
        player.takeDamage(100);
        assertFalse(player.isAlive());

        player.healHp(50);
        assertTrue(player.isAlive());
    }
}
