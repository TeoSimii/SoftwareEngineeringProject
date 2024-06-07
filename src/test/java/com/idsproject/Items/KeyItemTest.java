package com.idsproject.Items;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeyItemTest {

    private KeyItem bossKey;
    private KeyItem rustyKey;
    private KeyItem oldSkull;
    private KeyItem corpse;
    private KeyItem theEmperor;
    private KeyItem pebble;
    private KeyItem goldCoin;
    private KeyItem bandages;
    private KeyItem damagedScroll;
    private KeyItem unknownItem;

    @BeforeEach
    public void setUp() {
        bossKey = new KeyItem("boss key");
        rustyKey = new KeyItem("rusty key");
        oldSkull = new KeyItem("old skull");
        corpse = new KeyItem("corpse");
        theEmperor = new KeyItem("the emperor");
        pebble = new KeyItem("pebble");
        goldCoin = new KeyItem("gold coin");
        bandages = new KeyItem("bandages");
        damagedScroll = new KeyItem("damaged scroll");
        unknownItem = new KeyItem("unknown");
    }

    @Test
    public void testBossKey() {
        assertEquals("boss key", bossKey.getName());
        assertFalse(bossKey.canUse);
        assertTrue(bossKey.isPickable());
        assertFalse(bossKey.getHeals());
        assertEquals(0, bossKey.getWeight());
        assertFalse(bossKey.canDiscard());
        assertEquals("The key to the boss room, be ready", bossKey.getDescription());
    }

    @Test
    public void testRustyKey() {
        assertEquals("rusty key", rustyKey.getName());
        assertFalse(rustyKey.canUse);
        assertTrue(rustyKey.isPickable());
        assertFalse(rustyKey.getHeals());
        assertEquals(0, rustyKey.getWeight());
        assertFalse(rustyKey.canDiscard());
        assertEquals("A rusty key, might be used to unlock a gate", rustyKey.getDescription());
    }

    @Test
    public void testOldSkull() {
        assertEquals("old skull", oldSkull.getName());
        assertFalse(oldSkull.canUse);
        assertTrue(oldSkull.isPickable());
        assertFalse(oldSkull.getHeals());
        assertEquals(2, oldSkull.getWeight());
        assertEquals("I... I don't even know why you picked it up, it's just a skull", oldSkull.getDescription());
    }

    @Test
    public void testCorpse() {
        assertEquals("corpse", corpse.getName());
        assertFalse(corpse.canUse);
        assertFalse(corpse.isPickable());
        assertFalse(corpse.getHeals());
        assertEquals(999, corpse.getWeight());
        assertEquals("The corpse of a soldier, may his soul return to the great flame", corpse.getDescription());
    }

    @Test
    public void testTheEmperor() {
        assertEquals("the emperor", theEmperor.getName());
        assertTrue(theEmperor.canUse);
        assertTrue(theEmperor.isPickable());
        assertFalse(theEmperor.getHeals());
        assertEquals(5, theEmperor.getWeight());
        assertEquals("A special totem, might teleport you directly inside of the boss room or back to the entrance. \nBe ready to gamble with fate", theEmperor.getDescription());
    }

    @Test
    public void testPebble() {
        assertEquals("pebble", pebble.getName());
        assertFalse(pebble.canUse);
        assertTrue(pebble.isPickable());
        assertFalse(pebble.getHeals());
        assertEquals(1, pebble.getWeight());
        assertEquals("Can you stop picking up useless things? It's literally just a pebble", pebble.getDescription());
    }

    @Test
    public void testGoldCoin() {
        assertEquals("gold coin", goldCoin.getName());
        assertFalse(goldCoin.canUse);
        assertTrue(goldCoin.isPickable());
        assertFalse(goldCoin.getHeals());
        assertEquals(1, goldCoin.getWeight());
        assertEquals("uhhhh, shiny!! :)", goldCoin.getDescription());
    }

    @Test
    public void testBandages() {
        assertEquals("bandages", bandages.getName());
        assertFalse(bandages.canUse);
        assertTrue(bandages.isPickable());
        assertFalse(bandages.getHeals());
        assertEquals(1, bandages.getWeight());
        assertEquals("A pile of bandages, might help someone in need", bandages.getDescription());
    }

    @Test
    public void testDamagedScroll() {
        assertEquals("damaged scroll", damagedScroll.getName());
        assertFalse(damagedScroll.canUse);
        assertTrue(damagedScroll.isPickable());
        assertFalse(damagedScroll.getHeals());
        assertEquals(0, damagedScroll.getWeight());
        assertFalse(damagedScroll.canDiscard());
        assertEquals("A scroll that once belonged to an ancient cult, has something written on it: Fear the old blood", damagedScroll.getDescription());
    }

    @Test
    public void testUnknownItem() {
        assertEquals("notItem", unknownItem.getName());
        assertFalse(unknownItem.canUse);
        assertTrue(unknownItem.isPickable());
        assertFalse(unknownItem.getHeals());
        assertEquals(0, unknownItem.getWeight());
        assertEquals("Skill issue, you messed up the code", unknownItem.getDescription());
    }
}
