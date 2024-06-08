package com.idsproject.Armor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmorTest {
    private Armor rathalosArmor;
    private Armor necromancerCloak;
    private Armor eliteKnightArmor;
    private Armor berserkerArmor;
    private Armor ordinaryClothes;

    @BeforeEach
    public void setUp() {
        rathalosArmor = new Armor("Rathalos Armor");
        necromancerCloak = new Armor("Necromancer Cloack");
        eliteKnightArmor = new Armor("Elite Knight Armor");
        berserkerArmor = new Armor("Berserker Armor");
        ordinaryClothes = new Armor(""); // Testing the null case
    }

    @Test
    public void testArmorPoints() {
        assertEquals(Armor.RATHALOS_ARMOR_HP, rathalosArmor.getArmorPoints());
        assertEquals(Armor.NECROMANCER_CLOACK_HP, necromancerCloak.getArmorPoints());
        assertEquals(Armor.ELITE_KNIGHT_ARMOR_HP, eliteKnightArmor.getArmorPoints());
        assertEquals(Armor.BERSERKER_ARMOR_HP, berserkerArmor.getArmorPoints());
        assertEquals(0, ordinaryClothes.getArmorPoints());
    }

    @Test
    public void testWeight() {
        assertEquals(15, rathalosArmor.getWeight());
        assertEquals(7, necromancerCloak.getWeight());
        assertEquals(20, eliteKnightArmor.getWeight());
        assertEquals(25, berserkerArmor.getWeight());
        assertEquals(0, ordinaryClothes.getWeight());
    }

    @Test
    public void testDescription() {
        assertEquals("An armor made from the scales of an apex monster of the Ancient Forest, also known as the King of the Skies", rathalosArmor.getDescription());
        assertEquals("A cloack that represent death itself, extremely durable and very light", necromancerCloak.getDescription());
        assertEquals("An armor made from the finest of steels, symbol of a knight of great status", eliteKnightArmor.getDescription());
        assertEquals("An armor that feeds off the rage of the user, extremely heavy and very resistant to attacks", berserkerArmor.getDescription());
        assertEquals("Normal clothes, nothing special", ordinaryClothes.getDescription());
    }

    @Test
    public void testName() {
        assertEquals("Rathalos Armor", rathalosArmor.getName());
        assertEquals("Necromancer Cloack", necromancerCloak.getName());
        assertEquals("Elite Knight Armor", eliteKnightArmor.getName());
        assertEquals("Berserker Armor", berserkerArmor.getName());
        assertEquals("Ordinary Clothes", ordinaryClothes.getName());
    }

    @Test
    public void testIsEquipped() {
        assertFalse(rathalosArmor.isEquipped());
        rathalosArmor.setEquipped();
        assertTrue(rathalosArmor.isEquipped());
        rathalosArmor.setEquipped();
        assertFalse(rathalosArmor.isEquipped());
    }

    @Test
    public void testIsArmor() {
        assertTrue(rathalosArmor.isArmor());
        assertTrue(necromancerCloak.isArmor());
        assertTrue(eliteKnightArmor.isArmor());
        assertTrue(berserkerArmor.isArmor());
        assertTrue(ordinaryClothes.isArmor());
    }

    @Test
    public void testisPickable() {
        assertTrue(rathalosArmor.isPickable());
        assertTrue(necromancerCloak.isPickable());
        assertTrue(eliteKnightArmor.isPickable());
        assertTrue(berserkerArmor.isPickable());
        assertTrue(ordinaryClothes.isPickable());
    }
}
