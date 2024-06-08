package com.idsproject.Rooms;

import com.idsproject.Items.*;
import com.idsproject.NPC.Npc;
import com.idsproject.Weapons.Weapon;
import com.idsproject.Armor.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room0;
    private Room room5;
    private Room room10;
    private Room emptyRoom;

    @BeforeEach
    public void setUp() {
        room0 = new Room(0);
        room5 = new Room(5);
        room10 = new Room(10);
        emptyRoom = new Room();
    }

    @Test
    public void testRoomInitialization() {
        assertEquals(0, room0.getId());
        assertEquals(1, room0.getTier());
        assertTrue(room0.HasDirection("south"));
        assertFalse(room0.HasDirection("north"));
        assertFalse(room0.isLocked("north"));

        assertEquals(5, room5.getId());
        assertEquals(1, room5.getTier());
        assertTrue(room5.HasDirection("west"));
        assertFalse(room5.isLocked("west"));

        assertEquals(10, room10.getId());
        assertEquals(3, room10.getTier());
        assertTrue(room10.HasDirection("north"));
        assertFalse(room10.isLocked("north"));
    }

    @Test
    public void testEmptyRoomInitialization() {
        assertEquals(99, emptyRoom.getId());
        assertTrue(emptyRoom.getItems().isEmpty());
        assertTrue(emptyRoom.getNpc().isEmpty());
        assertFalse(emptyRoom.HasDirection("north"));
    }

    @Test
    public void testAddRemoveItems() {
        Item item = new Weapon("Mortal Blade");
        emptyRoom.getItems().add(item);
        assertTrue(emptyRoom.getItems().contains(item));

        emptyRoom.removeItem(item);
        assertFalse(emptyRoom.getItems().contains(item));
    }

    @Test
    public void testAddRemoveNpcs() {
        Npc npc = new Npc("injured knight");
        emptyRoom.getNpc().add(npc);
        assertTrue(emptyRoom.getNpc().contains(npc));

        emptyRoom.updateNpc(new Npc("patches"));
        assertEquals("patches", emptyRoom.getNpc().get(0).getName());
    }

    @Test
    public void testLockUnlockDoors() {
        room0.SetLocked("north");
        assertTrue(room0.isLocked("north"));
        room0.SetLocked("north");
        assertFalse(room0.isLocked("north"));
    }

    @Test
    public void testItemInRoom() {
        assertNotNull(room0.isInRoom("Butterfly Knife"));
        assertNull(room0.isInRoom("Artoria's Sword"));
    }

    @Test
    public void testNpcInRoom() {
        Npc npc = new Npc("patches");
        room5.getNpc().add(npc);
        assertNotNull(room5.isNpcInRoom("patches"));
        assertNull(room5.isNpcInRoom("injured knight"));
    }

    @Test
    public void testShowItems() {
        JLabel log= new JLabel();
        room0.showItems(log);
        // Manually verify the printed output for items, npcs, and connected rooms
    }
}
