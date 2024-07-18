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
    public void testConstructor(){
        Assert.assertNull(game.getPlayer());
        Assert.assertNull(game.getAccount());
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
    public void testGetAccountNull() {
        Assert.assertNull(game.getAccount());
    }

    @Test
    public void testGetAccount(){
        CoinFlipPlayer expectedPlayer = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        CasinoAccount expectedAccount = expectedPlayer.getArcadeAccount();

        game.add(expectedPlayer);

        CasinoAccount actualAccount = game.getAccount();

        Assert.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testRemoveSuccessful(){
        CasinoAccount account = new CasinoAccount("tester", "tester");
        CoinFlipPlayer player = new CoinFlipPlayer(account);

        game.add(player);

        CoinFlipPlayer actualPlayer = game.getPlayer();
        CasinoAccount actualAccount = actualPlayer.getArcadeAccount();

        Assert.assertEquals(player, actualPlayer);
        Assert.assertEquals(account, actualAccount);

        game.remove(player);
        actualPlayer = game.getPlayer();
        actualAccount = game.getAccount();

        Assert.assertNull(actualPlayer);
        Assert.assertNull(actualAccount);
    }

    @Test
    public void testRemoveFailed(){
        CasinoAccount account = new CasinoAccount("tester", "tester");
        CoinFlipPlayer player = new CoinFlipPlayer(account);

        game.add(player);

        CoinFlipPlayer actualPlayer = game.getPlayer();
        CasinoAccount actualAccount = game.getAccount();

        Assert.assertEquals(player, actualPlayer);
        Assert.assertEquals(account, actualAccount);

        game.remove(new CoinFlipPlayer(new CasinoAccount("tester", "tester")));

        actualPlayer = game.getPlayer();
        actualAccount = game.getAccount();

        Assert.assertEquals(player, actualPlayer);
        Assert.assertEquals(account, actualAccount);
    }

    @Test
    public void testPrintWelcomeMessage(){
        String expected = " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$         /$$$$$$   /$$$$$$  /$$$$$$ /$$   /$$ /$$$$$$$$ /$$       /$$$$$$ /$$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$       /$$__  $$ /$$__  $$|_  $$_/| $$$ | $$| $$_____/| $$      |_  $$_/| $$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$  \\__/| $$  \\ $$  | $$  | $$$$| $$| $$      | $$        | $$  | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      | $$      | $$  | $$  | $$  | $$ $$ $$| $$$$$   | $$        | $$  | $$$$$$$/\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$      | $$      | $$  | $$  | $$  | $$  $$$$| $$__/   | $$        | $$  | $$____/ \n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$      | $$    $$| $$  | $$  | $$  | $$\\  $$$| $$      | $$        | $$  | $$      \n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      |  $$$$$$/|  $$$$$$/ /$$$$$$| $$ \\  $$| $$      | $$$$$$$$ /$$$$$$| $$      \n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/        \\______/  \\______/ |______/|__/  \\__/|__/      |________/|______/|__/";

        String actual = game.printWelcomeMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRandomNum(){
        for(int i = 0; i < 100; i++){
            int num = game.getRandomNum();
            Assert.assertTrue(-.01 < num && num < 1.01);
        }
    }

    @Test
    public void testHandleOutcomeWinTails(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        game.add(player);

        String expectedString = "YOU WIN! The answer was tails";
        double expectedAccountBalance = player.getArcadeAccount().getBalance() + 20;

        String actualString = game.handleOutcome(10, "tails", "tails");
        double actualAccountBalance = player.getArcadeAccount().getBalance();

        Assert.assertEquals(expectedString, actualString);
        Assert.assertEquals(expectedAccountBalance, actualAccountBalance,0.001);
    }

    @Test
    public void testHandleOutcomeLoseTails(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        game.add(player);

        String expectedString = "You Lose. The answer was tails";

        String actualString = game.handleOutcome(10, "heads", "tails");

        player.getArcadeAccount().subtractLosses(10); // This would usually be done in the game.run() method
                                                            // I am putting it here to ensure the test account's balance
                                                            // Doesn't get too high

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void testHandleOutcomeWinHeads() {
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));

        game.add(player);

        String expectedString = "YOU WIN! The answer was heads";
        double expectedAccountBalance = player.getArcadeAccount().getBalance() + 20;

        String actualString = game.handleOutcome(10, "heads", "heads");
        double actualAccountBalance = player.getArcadeAccount().getBalance();

        Assert.assertEquals(expectedString, actualString);
        Assert.assertEquals(expectedAccountBalance, actualAccountBalance,0.001);
    }

    @Test
    public void testHandleOutcomeLoseHeads(){
        CoinFlipPlayer player = new CoinFlipPlayer(new CasinoAccount("tester", "tester"));
        game.add(player);

        String expectedString = "You Lose. The answer was heads";

        String actualString = game.handleOutcome(10, "tails", "heads");

        player.getArcadeAccount().subtractLosses(10); // This would usually be done in the game.run() method
                                                            // I am putting it here to ensure the test account's balance
                                                            // Doesn't get too high

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
        Assert.assertTrue(game instanceof GamblingGameInterface);
    }
}
