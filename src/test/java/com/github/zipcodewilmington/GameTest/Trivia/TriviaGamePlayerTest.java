package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGamePlayer;
import org.junit.Assert;
import org.junit.Test;

public class TriviaGamePlayerTest {
    @Test
    public void testGetAccount() {
        CasinoAccount expected = new CasinoAccount("tester","tester");
        TriviaGamePlayer player = new TriviaGamePlayer(expected);

        CasinoAccount actual = player.getArcadeAccount();

        Assert.assertEquals(expected, actual);
    }
}
