package com.idsproject.NPC;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NpcTest {

    private Npc injuredKnight;
    private Npc patches;

    @BeforeEach
    public void setUp() {
        injuredKnight = new Npc("injured knight");
        patches = new Npc("patches");
    }

    @Test
    public void testInjuredKnight() {
        assertEquals("injured knight", injuredKnight.getName());
        assertFalse(injuredKnight.isQuestCompleted());
    }

    @Test
    public void testPatches() {
        assertEquals("patches", patches.getName());
        assertFalse(patches.isQuestCompleted());
    }

    @Test
    public void testQuestCompletion() {
        assertFalse(injuredKnight.isQuestCompleted());
        injuredKnight.setQuestCompleted();
        assertTrue(injuredKnight.isQuestCompleted());
        injuredKnight.setQuestCompleted();
        assertFalse(injuredKnight.isQuestCompleted());
    }
}
