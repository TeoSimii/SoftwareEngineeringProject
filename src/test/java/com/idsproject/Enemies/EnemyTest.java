package com.idsproject.Enemies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyTest {

    private Enemy tier1Enemy;
    private Enemy tier2Enemy;
    private Enemy tier3Enemy;
    private Enemy tier4Enemy;
    private Enemy bossEnemy;

    @BeforeEach
    public void setUp() {
        tier1Enemy = new Enemy(1);
        tier2Enemy = new Enemy(2);
        tier3Enemy = new Enemy(3);
        tier4Enemy = new Enemy(4);
        bossEnemy = new Enemy(5);
    }

    @Test
    public void testDefaultConstructor() {
        Enemy defaultEnemy = new Enemy();
        assertNotNull(defaultEnemy.getname());
        assertTrue(defaultEnemy.gethealth() >= 0 && defaultEnemy.gethealth() <= Enemy.TIER1_MAXIMUM_HEALTH);
        assertEquals(100, defaultEnemy.getscore());
        assertEquals(1, defaultEnemy.getTier());
    }

    @Test
    public void testTier1Enemy() {
        assertNotNull(tier1Enemy.getname());
        assertTrue(tier1Enemy.gethealth() >= 5 && tier1Enemy.gethealth() <= Enemy.TIER1_MAXIMUM_HEALTH);
        assertEquals(100, tier1Enemy.getscore());
        assertEquals(1, tier1Enemy.getTier());
    }

    @Test
    public void testTier2Enemy() {
        assertNotNull(tier2Enemy.getname());
        assertTrue(tier2Enemy.gethealth() >= 15 && tier2Enemy.gethealth() <= Enemy.TIER2_MAXIMUM_HEALTH);
        assertEquals(200, tier2Enemy.getscore());
        assertEquals(2, tier2Enemy.getTier());
    }

    @Test
    public void testTier3Enemy() {
        assertNotNull(tier3Enemy.getname());
        assertTrue(tier3Enemy.gethealth() >= 30 && tier3Enemy.gethealth() <= Enemy.TIER3_MAXIMUM_HEALTH);
        assertEquals(300, tier3Enemy.getscore());
        assertEquals(3, tier3Enemy.getTier());
    }

    @Test
    public void testTier4Enemy() {
        assertNotNull(tier4Enemy.getname());
        assertTrue(tier4Enemy.gethealth() >= 40 && tier4Enemy.gethealth() <= Enemy.TIER4_MAXIMUM_HEALTH);
        assertEquals(400, tier4Enemy.getscore());
        assertEquals(4, tier4Enemy.getTier());
    }

    @Test
    public void testBossEnemy() {
        assertEquals(Enemy.Boss, bossEnemy.getname());
        assertEquals(Enemy.BOSS_MAXIMUM_HEALTH, bossEnemy.gethealth());
        assertEquals(1000, bossEnemy.getscore());
        assertEquals(5, bossEnemy.getTier());
    }

    @Test
    public void testAttack() {
        int attackValue = tier1Enemy.attack(1);
        assertTrue(attackValue >= 3 && attackValue <= Enemy.TIER1_ATTACK);

        attackValue = tier2Enemy.attack(2);
        assertTrue(attackValue >= 5 && attackValue <= Enemy.TIER2_ATTACK);

        attackValue = tier3Enemy.attack(3);
        assertTrue(attackValue >= 8 && attackValue <= Enemy.TIER3_ATTACK);

        attackValue = tier4Enemy.attack(4);
        assertTrue(attackValue >= 15 && attackValue <= Enemy.TIER4_ATTACK);

        attackValue = bossEnemy.attack(5);
        assertTrue(attackValue >= 20 && attackValue <= Enemy.BOSS_ATTACK);
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = tier1Enemy.gethealth();
        tier1Enemy.takeDamage(5);
        assertEquals(initialHealth - 5, tier1Enemy.gethealth());
    }

    @Test
    public void testGetMaxHp() {
        assertEquals(tier1Enemy.getMaxHp(), tier1Enemy.gethealth());
        assertEquals(tier2Enemy.getMaxHp(), tier2Enemy.gethealth());
        assertEquals(tier3Enemy.getMaxHp(), tier3Enemy.gethealth());
        assertEquals(tier4Enemy.getMaxHp(), tier4Enemy.gethealth());
        assertEquals(bossEnemy.getMaxHp(), bossEnemy.gethealth());
    }
}
