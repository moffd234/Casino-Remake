package com.github.zipcodewilmington.GameTest.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayerInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.CoinFlip.CoinFlipPlayer;
import org.junit.Assert;
import org.junit.Test;

public class CoinFlipPlayerTest {

    @Test
    public void testConstructor(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        Assert.assertNotNull(player.getArcadeAccount());
    }

    @Test
    public void testGetArcadeAccount(){
        CasinoAccount expected = new CasinoAccount("tester", "tester");
        CoinFlipPlayer player = new CoinFlipPlayer(expected);

        CasinoAccount actual = player.getArcadeAccount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInheritance(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        Assert.assertTrue(player instanceof PlayerInterface);
        Assert.assertTrue(player instanceof GamblingPlayerInterface);
    }
}
