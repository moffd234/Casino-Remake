package com.github.zipcodewilmington.GameTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
    TicTacToe game;

    @Before
    public void setUp(){
        game = new TicTacToe();
    }

    @Test
    public void testAdd(){
        TicTacToePlayer expected = new TicTacToePlayer();

        game.add(expected);

        TicTacToePlayer actual = game.getPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        TicTacToePlayer player = new TicTacToePlayer();
        game.add(player);

        game.remove(player);

        TicTacToePlayer actual = game.getPlayer();

        Assert.assertNull(actual);
    }

    @Test
    public void testGetPlayerNull(){
        TicTacToePlayer actual = game.getPlayer();

        Assert.assertNull(actual);
    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
    }
}
