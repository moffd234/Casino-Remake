package com.github.zipcodewilmington.GameTest.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.games.CoinFlip.CoinFlipGame;
import com.github.zipcodewilmington.casino.games.CoinFlip.CoinFlipPlayer;
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
    public void testGetPlayer(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));

        game.add(player);

        CoinFlipPlayer actual = game.getPlayer();

        Assert.assertEquals(player, actual);
    }

    @Test
    public void testGetPlayerNull(){
        CoinFlipPlayer actualPlayer = game.getPlayer();

        Assert.assertNull(actualPlayer);
    }

    @Test
    public void testAddPlayer(){
        CoinFlipPlayer actualPlayer = game.getPlayer();
        Assert.assertNull(actualPlayer);

        CoinFlipPlayer expectedPlayer = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));

        game.add(expectedPlayer);
        actualPlayer = game.getPlayer();

        Assert.assertEquals(expectedPlayer, actualPlayer);
    }


    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
        Assert.assertTrue(game instanceof GamblingGameInterface);
    }
}
