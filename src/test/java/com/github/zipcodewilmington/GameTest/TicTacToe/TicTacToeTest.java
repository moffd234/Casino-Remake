package com.github.zipcodewilmington.GameTest.TicTacToe;

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
    public void testPrintWelcomeMessage(){
        String expected = " /$$      /$$           /$$                                                   /$$$$$$$$              /$$$$$$$$ /$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$  /$$$$$$$$\\n\" +\n" +
                "                \"| $$  /$ | $$          | $$                                                  |__  $$__/             |__  $$__/|_  $$_/ /$$__  $$|__  $$__//$$__  $$ /$$__  $$|__  $$__//$$__  $$| $$_____/\\n\" +\n" +
                "                \"| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$          | $$  /$$$$$$          | $$     | $$  | $$  \\\\__/   | $$  | $$  \\\\ $$| $$  \\\\__/   | $$  | $$  \\\\ $$| $$      \\n\" +\n" +
                "                \"| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$         | $$ /$$__  $$         | $$     | $$  | $$         | $$  | $$$$$$$$| $$         | $$  | $$  | $$| $$$$$   \\n\" +\n" +
                "                \"| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\\\ $$| $$ \\\\ $$ \\\\ $$| $$$$$$$$         | $$| $$  \\\\ $$         | $$     | $$  | $$         | $$  | $$__  $$| $$         | $$  | $$  | $$| $$__/   \\n\" +\n" +
                "                \"| $$$/ \\\\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/         | $$| $$  | $$         | $$     | $$  | $$    $$   | $$  | $$  | $$| $$    $$   | $$  | $$  | $$| $$      \\n\" +\n" +
                "                \"| $$/   \\\\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$         | $$|  $$$$$$/         | $$    /$$$$$$|  $$$$$$/   | $$  | $$  | $$|  $$$$$$/   | $$  |  $$$$$$/| $$$$$$$$\\n\" +\n" +
                "                \"|__/     \\\\__/ \\\\_______/|__/ \\\\_______/ \\\\______/ |__/ |__/ |__/ \\\\_______/         |__/ \\\\______/          |__/   |______/ \\\\______/    |__/  |__/  |__/ \\\\______/    |__/   \\\\______/ |________/";
        String actual = game.printWelcomeMessage();
    }

    @Test
    public void testGetGameBoardEmpty(){
        String[][] expected = new String[3][3];
        String[][] actual = game.getGameBoard();

        Assert.assertArrayEquals(expected, actual);

    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
    }
}
