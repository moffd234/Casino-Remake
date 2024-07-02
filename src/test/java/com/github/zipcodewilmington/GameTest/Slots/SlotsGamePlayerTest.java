package com.github.zipcodewilmington.GameTest.Slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import org.junit.Assert;
import org.junit.Test;

public class SlotsGamePlayerTest {
    @Test
    public void testGetAccount() {
        CasinoAccount expected = new CasinoAccount("tester","tester");
        SlotsPlayer player = new SlotsPlayer(expected);

        CasinoAccount actual = player.getArcadeAccount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsInstanceOfGameInterface(){
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount("tester","tester"));
        Assert.assertTrue(player instanceof PlayerInterface);
    }
}
