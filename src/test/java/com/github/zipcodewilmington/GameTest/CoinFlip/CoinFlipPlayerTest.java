package com.github.zipcodewilmington.GameTest.CoinFlip;

import com.github.zipcodewilmington.casino.GamblingPlayerInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.CoinFlip.CoinFlipPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinFlipPlayerTest {
    CoinFlipPlayer player;

    @Before
    public void setUp() {
        player = new CoinFlipPlayer();
    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(player instanceof PlayerInterface);
        Assert.assertTrue(player instanceof GamblingPlayerInterface);
    }
}
