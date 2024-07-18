package com.github.zipcodewilmington.GameTest.CoinFlip;

import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.games.CoinFlip.CoinFlipGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinFlipGameTest {
    CoinFlipGame game;


    @Before
    public void setup(){
        game = new CoinFlipGame();
    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
        Assert.assertTrue(game instanceof GamblingGameInterface);
    }
}
