package com.idsproject.Items;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealItemTest {

    private HealItem potion;
    private HealItem estusFlask;
    private HealItem oldPotion;
    private HealItem unknownItem;

    @BeforeEach
    public void setUp() {
        potion = new HealItem("potion");
        estusFlask = new HealItem("estus flask");
        oldPotion = new HealItem("old potion");
        unknownItem = new HealItem("unknown");
    }

    @Test
    public void testPotion() {
        assertEquals("potion", potion.getName());
        assertTrue(potion.isPickable());
        assertTrue(potion.canUse);
        assertTrue(potion.getHeals());
        assertEquals(10, potion.getHealingPower());
        assertEquals(1, potion.getWeight());
        assertEquals("A bittersweet basic potion, restores 10Hp", potion.getDescription());
    }

    @Test
    public void testEstusFlask() {
        assertEquals("estus flask", estusFlask.getName());
        assertTrue(estusFlask.isPickable());
        assertTrue(estusFlask.canUse);
        assertTrue(estusFlask.getHeals());
        assertEquals(20, estusFlask.getHealingPower());
        assertEquals(2, estusFlask.getWeight());
        assertEquals("A potion from a far land, restores 20Hp", estusFlask.getDescription());
    }

    @Test
    public void testOldPotion() {
        assertEquals("old potion", oldPotion.getName());
        assertTrue(oldPotion.isPickable());
        assertTrue(oldPotion.canUse);
        assertTrue(oldPotion.getHeals());
        assertEquals(-15, oldPotion.getHealingPower());
        assertEquals(2, oldPotion.getWeight());
        assertEquals("Looks quite old, but it might still work... I think?", oldPotion.getDescription());
    }

    @Test
    public void testUnknownItem() {
        assertEquals("notHealItem", unknownItem.getName());
        assertTrue(unknownItem.isPickable());
        assertTrue(unknownItem.canUse);
        assertTrue(unknownItem.getHeals());
        assertEquals(10, unknownItem.getHealingPower());
        assertEquals(1, unknownItem.getWeight());
        assertEquals("A basic potion, restores 10Hp", unknownItem.getDescription());
    }
}
