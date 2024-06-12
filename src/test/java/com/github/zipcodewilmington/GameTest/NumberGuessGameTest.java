package com.github.zipcodewilmington.GameTest;

import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    public void testAdd() {
        NumberGuessPlayer expected = new NumberGuessPlayer();

        game.add(expected);
        ArrayList<NumberGuessPlayer> players = game.getPlayers();

        NumberGuessPlayer actual = players.get(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        NumberGuessPlayer player1 = new NumberGuessPlayer();

        game.add(player1);
        ArrayList<NumberGuessPlayer> players = game.getPlayers();

        Assert.assertTrue(players.contains(player1));

        game.remove(player1);

        players = game.getPlayers();
        boolean actual = players.contains(player1);

        Assert.assertFalse(actual);
    }

    @Test
    public void testWelcomeMessage() {
        String expected = " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$        /$$   /$$ /$$   /$$ /$$      /$$        /$$$$$$  /$$   /$$ /$$$$$$$$  /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$      | $$$ | $$| $$  | $$| $$$    /$$$       /$$__  $$| $$  | $$| $$_____/ /$$__  $$ /$$__  $$| $$_____/| $$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$$$| $$| $$  | $$| $$$$  /$$$$      | $$  \\__/| $$  | $$| $$      | $$  \\__/| $$  \\__/| $$      | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      | $$ $$ $$| $$  | $$| $$ $$/$$ $$      | $$ /$$$$| $$  | $$| $$$$$   |  $$$$$$ |  $$$$$$ | $$$$$   | $$$$$$$/\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$      | $$  $$$$| $$  | $$| $$  $$$| $$      | $$|_  $$| $$  | $$| $$__/    \\____  $$ \\____  $$| $$__/   | $$__  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$      | $$\\  $$$| $$  | $$| $$\\  $ | $$      | $$  \\ $$| $$  | $$| $$       /$$  \\ $$ /$$  \\ $$| $$      | $$  \\ $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      | $$ \\  $$|  $$$$$$/| $$ \\/  | $$      |  $$$$$$/|  $$$$$$/| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$$$$$$$| $$  | $$\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/       |__/  \\__/ \\______/ |__/     |__/       \\______/  \\______/ |________/ \\______/  \\______/ |________/|__/  |__/";

        String actual = game.printWelcomeMessage();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleOutcomeWin() {
        String expected = "You won! The answer was 0";
        String actual = game.handleOutcome(0, 0, 0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleOutcomeLose() {
        String expected = "You lost! Correct number: 2";
        String actual = game.handleOutcome(0, 2, 0);
        Assert.assertEquals(expected, actual);
    }
}
