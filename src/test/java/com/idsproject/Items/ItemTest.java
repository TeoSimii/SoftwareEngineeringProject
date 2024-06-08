package com.idsproject.Items;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {

    private Item item;

    @BeforeEach
    public void setUp() {
        item = new Item();
    }

    @Test
    public void testGetName() {
        assertNull(item.getName()); // assuming name is not set by default
    }

    @Test
    public void testCanDiscard() {
        assertTrue(item.canDiscard());
    }

    @Test
    public void testIsArmor() {
        assertFalse(item.isArmor());
    }

    @Test
    public void testIsWeapon() {
        assertFalse(item.isWeapon());
    }

    @Test
    public void testGetDescription() {
        assertNull(item.getDescription()); // assuming description is not set by default
    }

    @Test
    public void testGetWeight() {
        assertEquals(0, item.getWeight()); // assuming weight is 0 by default
    }

    @Test
    public void testIsPickable() {
        assertFalse(item.isPickable()); // assuming canPickUp is false by default
    }

    @Test
    public void testIsEquipped() {
        assertFalse(item.isEquipped());
    }

    @Test
    public void testGetStack() {
        assertEquals(1, item.getStack()); // default stack is 1
    }

    @Test
    public void testItemCountSetDecrement() {
        item.ItemCountSet("decrement");
        assertEquals(0, item.getStack());
    }

    @Test
    public void testItemCountSetIncrement() {
        item.ItemCountSet("increment");
        assertEquals(2, item.getStack());
    }

    @Test
    public void testGetHeals() {
        assertFalse(item.getHeals()); // assuming heals is false by default
    }
}
