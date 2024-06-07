package com.idsproject.Weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    private Weapon butterflyKnife;
    private Weapon moonlightSword;
    private Weapon artoriasSword;
    private Weapon mortalBlade;

    @BeforeEach
    public void setUp() {
        butterflyKnife = new Weapon("Butterfly Knife");
        moonlightSword = new Weapon("Moonlight Sword");
        artoriasSword = new Weapon("Artoria's Sword");
        mortalBlade = new Weapon("Mortal Blade");
    }

    @Test
    public void testButterflyKnifeInitialization() {
        assertEquals("Butterfly Knife", butterflyKnife.getName());
        assertEquals(Weapon.BUTTERFLY_KNIFE_DAMAGE, butterflyKnife.getAttackDamage());
        assertEquals(2, butterflyKnife.getWeight());
        assertEquals("A Butterfly Knife, it may not be much, but it's a start", butterflyKnife.getDescription());
        assertTrue(butterflyKnife.isWeapon());
        assertTrue(butterflyKnife.isPickable());
        assertFalse(butterflyKnife.isEquipped());
    }

    @Test
    public void testMoonlightSwordInitialization() {
        assertEquals("Moonlight Sword", moonlightSword.getName());
        assertEquals(Weapon.MOONLIGHT_SWORD_DAMAGE, moonlightSword.getAttackDamage());
        assertEquals(12, moonlightSword.getWeight());
        assertEquals("A sword imbued with the light of the moon, able to slay even the strongest demon", moonlightSword.getDescription());
        assertTrue(moonlightSword.isWeapon());
        assertTrue(moonlightSword.isPickable());
        assertFalse(moonlightSword.isEquipped());
    }

    @Test
    public void testArtoriasSwordInitialization() {
        assertEquals("Artoria's Sword", artoriasSword.getName());
        assertEquals(Weapon.ARTORIAS_SWORD, artoriasSword.getAttackDamage());
        assertEquals(16, artoriasSword.getWeight());
        assertEquals("A sword that was once wielded by the greatest knight in history", artoriasSword.getDescription());
        assertTrue(artoriasSword.isWeapon());
        assertTrue(artoriasSword.isPickable());
        assertFalse(artoriasSword.isEquipped());
    }

    @Test
    public void testMortalBladeInitialization() {
        assertEquals("Mortal Blade", mortalBlade.getName());
        assertEquals(Weapon.MORTAL_BLADE_DAMAGE, mortalBlade.getAttackDamage());
        assertEquals(10, mortalBlade.getWeight());
        assertEquals("A legendary katana from a far land, able to severe immortality", mortalBlade.getDescription());
        assertTrue(mortalBlade.isWeapon());
        assertTrue(mortalBlade.isPickable());
        assertFalse(mortalBlade.isEquipped());
    }

    @Test
    public void testDefaultWeaponInitialization() {
        Weapon defaultWeapon = new Weapon("Unknown Weapon");
        assertEquals("notWeapon", defaultWeapon.getName());
        assertEquals(Weapon.BUTTERFLY_KNIFE_DAMAGE, defaultWeapon.getAttackDamage());
        assertEquals(2, defaultWeapon.getWeight());
        assertEquals("A Butterfly Knife, it may not be much, but it's a start", defaultWeapon.getDescription());
        assertTrue(defaultWeapon.isWeapon());
        assertTrue(defaultWeapon.isPickable());
        assertFalse(defaultWeapon.isEquipped());
    }

    @Test
    public void testSetEquipped() {
        butterflyKnife.setEquipped();
        assertTrue(butterflyKnife.isEquipped());
        butterflyKnife.setEquipped();
        assertFalse(butterflyKnife.isEquipped());

        moonlightSword.setEquipped();
        assertTrue(moonlightSword.isEquipped());
        moonlightSword.setEquipped();
        assertFalse(moonlightSword.isEquipped());

        artoriasSword.setEquipped();
        assertTrue(artoriasSword.isEquipped());
        artoriasSword.setEquipped();
        assertFalse(artoriasSword.isEquipped());

        mortalBlade.setEquipped();
        assertTrue(mortalBlade.isEquipped());
        mortalBlade.setEquipped();
        assertFalse(mortalBlade.isEquipped());
    }
}
