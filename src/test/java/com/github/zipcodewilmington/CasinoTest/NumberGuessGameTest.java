package com.github.zipcodewilmington.CasinoTest;

import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberGuessGameTest {
    private NumberGuessGame game;
    private final NumberGuessPlayer player = new NumberGuessPlayer();

    @Before
    public void setUp(){
        game = new NumberGuessGame(player);
    }

    @Test
    public void testGetRandomNumber() {
        int num = game.getRandomNum();

        Assert.assertTrue(0 <= num && num <= 10);
    }

    @Test
    public void testIsWinnerTrue() {
        boolean outcome = game.isWinner(5, 5);
        Assert.assertTrue(outcome);
    }

    @Test
    public void testIsWinnerFalse() {
        boolean outcome = game.isWinner(1, 5);
        Assert.assertFalse(outcome);
    }
}
