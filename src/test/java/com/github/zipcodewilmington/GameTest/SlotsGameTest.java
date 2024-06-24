package com.github.zipcodewilmington.GameTest;

import com.github.zipcodewilmington.casino.PlayerInterface;
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
        SlotsPlayer expected = new SlotsPlayer();

        game.add(expected);

        SlotsPlayer actual = game.getPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        SlotsPlayer player = new SlotsPlayer();
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
}
