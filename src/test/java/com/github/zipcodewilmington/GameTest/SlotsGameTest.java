package com.github.zipcodewilmington.GameTest;

import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotsGameTest {
    SlotsGame game;

    @Before
    public void setup() {
        game = new SlotsGame();
    }

    @Test
    public void testAdd(){
        SlotsPlayer expected = new SlotsPlayer();

        game.add(expected);

        SlotsPlayer actual = game.getPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        SlotsPlayer player = new SlotsPlayer();
        game.add(player);

        game.remove(player);

        SlotsPlayer actual = game.getPlayer();
        Assert.assertNull(actual);
    }
    
}
