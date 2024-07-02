package com.github.zipcodewilmington.GameTest.NumberGuess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGamePlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import org.junit.Assert;
import org.junit.Test;

public class NumberGuessGamePlayerTest {
    @Test
    public void testGetAccount() {
        CasinoAccount expected = new CasinoAccount("tester","tester");
        NumberGuessPlayer player = new NumberGuessPlayer(expected);

        CasinoAccount actual = player.getArcadeAccount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsInstanceOfGameInterface(){
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount("tester","tester"));
        Assert.assertTrue(player instanceof PlayerInterface);
    }
}
