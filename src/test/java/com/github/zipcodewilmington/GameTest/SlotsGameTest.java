package com.github.zipcodewilmington.GameTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotsGameTest {
    SlotsGame game;

    @Before
    public void setup() {
        game = new SlotsGame();
    }

    @Test
    public void testAdd(){
        SlotsPlayer expected = new SlotsPlayer(new CasinoAccount());
        CasinoAccount expectedAccount = expected.getArcadeAccount();

        game.add(expected);

        SlotsPlayer actual = game.getPlayer();
        CasinoAccount actualAccount = actual.getArcadeAccount();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testRemove(){
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount());
        game.add(player);

        game.remove(player);

        SlotsPlayer actual = game.getPlayer();

        Assert.assertNull(actual);
    }

    @Test
    public void testWelcome(){
        String expected = " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$         /$$$$$$  /$$        /$$$$$$  /$$$$$$$$ /$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$       /$$__  $$| $$       /$$__  $$|__  $$__//$$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$  \\__/| $$      | $$  \\ $$   | $$  | $$  \\__/\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      |  $$$$$$ | $$      | $$  | $$   | $$  |  $$$$$$ \n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$       \\____  $$| $$      | $$  | $$   | $$   \\____  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$       /$$  \\ $$| $$      | $$  | $$   | $$   /$$  \\ $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      |  $$$$$$/| $$$$$$$$|  $$$$$$/   | $$  |  $$$$$$/\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/        \\______/ |________/ \\______/    |__/   \\______/ ";

        String actual = game.printWelcomeMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRules(){
        String expected = "Rules:\n" +
                "1. Enter a wager amount.\n" +
                "2. Match three symbols on the pay line to win.\n" +
                "3. Payouts vary based on the symbols matched:\n" +
                "   - Three 7s: Jackpot(10x)\n" +
                "   - Three Bells: Big Win(5x)\n" +
                "   - Three Bars: Medium Win (2x)\n" +
                "   - Three Cherries: Small Win (1.5x)\n" +
                "   - Any other combination: No Win\n";

        String actual = game.printRules();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSymbolsLength(){
        int expected = 3;

        int actual = game.getSymbols().length;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSymbolsDifference(){
        String[] symbols1 = game.getSymbols();
        String[] symbols2 = game.getSymbols();

        Assert.assertNotEquals(symbols1, symbols2);
    }

    @Test
    public void testIsWinnerTrue(){
        String[] symbols = {"7", "7", "7"};
        boolean actual = game.isWinner(symbols);
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsWinnerTrue2(){
        String[] symbols = {"Bar", "Bar", "Bar"};
        boolean actual = game.isWinner(symbols);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsWinnerFalse(){
        String[] symbols = {"7", "Cherry", "Bar"};
        boolean actual = game.isWinner(symbols);
        Assert.assertFalse(actual);
    }

    @Test
    public void testGetWinnerType7(){
        double expected = 10;
        String sym = "7";

        double actual = game.getWinnerType(sym);

        Assert.assertEquals(expected, actual, .0001);
    }

    @Test
    public void testGetWinnerTypeBell(){
        double expected = 5;
        String sym = "Bell";

        double actual = game.getWinnerType(sym);

        Assert.assertEquals(expected, actual, .0001);
    }

    @Test
    public void testGetWinnerTypeBar(){
        double expected = 2;
        String sym = "Bar";

        double actual = game.getWinnerType(sym);

        Assert.assertEquals(expected, actual, .0001);
    }

    @Test
    public void testGetWinnerTypeCherry(){
        double expected = 1.5;
        String sym = "Cherry";

        double actual = game.getWinnerType(sym);

        Assert.assertEquals(expected, actual, .0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWinnerTypeInvalidSymbol() {
        game.getWinnerType("InvalidSymbol");
    }

    @Test
    public void testHandleOutcomeWinner7(){
        CasinoAccount account = new CasinoAccountManager().getAccount("tester", "tester");
        SlotsPlayer player = new SlotsPlayer(account);
        game.add(player);

        double originalBalance = account.getBalance();

        String[] sym = {"7", "7", "7"};

        boolean expectedOutput = true;
        boolean actualOutput = game.handleOutcome(100, sym);

        double expectedBalanceDiff = 1000;
        double balanceDiff = account.getBalance() - originalBalance;


        Assert.assertEquals(expectedBalanceDiff, balanceDiff, .0001);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHandleOutcomeWinnerBell(){
        CasinoAccount account = new CasinoAccountManager().getAccount("tester", "tester");
        SlotsPlayer player = new SlotsPlayer(account);
        game.add(player);

        double originalBalance = account.getBalance();

        String[] sym = {"Bell", "Bell", "Bell"};

        boolean expectedOutput = true;
        boolean actualOutput = game.handleOutcome(100, sym);

        double expectedBalanceDiff = 500;
        double balanceDiff = account.getBalance() - originalBalance;


        Assert.assertEquals(expectedBalanceDiff, balanceDiff, .0001);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHandleOutcomeWinnerBar(){
        CasinoAccount account = new CasinoAccountManager().getAccount("tester", "tester");
        SlotsPlayer player = new SlotsPlayer(account);
        game.add(player);

        double originalBalance = account.getBalance();

        String[] sym = {"Bar", "Bar", "Bar"};

        boolean expectedOutput = true;
        boolean actualOutput = game.handleOutcome(100, sym);

        double expectedBalanceDiff = 200;
        double balanceDiff = account.getBalance() - originalBalance;


        Assert.assertEquals(expectedBalanceDiff, balanceDiff, .0001);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHandleOutcomeWinnerCherry(){
        CasinoAccount account = new CasinoAccountManager().getAccount("tester", "tester");
        SlotsPlayer player = new SlotsPlayer(account);
        game.add(player);

        double originalBalance = account.getBalance();

        String[] sym = {"Cherry", "Cherry", "Cherry"};

        boolean expectedOutput = true;
        boolean actualOutput = game.handleOutcome(100, sym);

        double expectedBalanceDiff = 150;
        double balanceDiff = account.getBalance() - originalBalance;


        Assert.assertEquals(expectedBalanceDiff, balanceDiff, .0001);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHandleOutcomeLoser(){
        CasinoAccount account = new CasinoAccountManager().getAccount("tester", "tester");
        SlotsPlayer player = new SlotsPlayer(account);
        game.add(player);

        double originalBalance = account.getBalance();

        String[] sym = {"Cherry", "Bar", "7"};

        boolean expectedOutput = false;
        boolean actualOutput = game.handleOutcome(1850, sym);

        double expectedBalanceDiff = -1850;
        double balanceDiff = account.getBalance() - originalBalance;


        Assert.assertEquals(expectedBalanceDiff, balanceDiff, .0001);
        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
