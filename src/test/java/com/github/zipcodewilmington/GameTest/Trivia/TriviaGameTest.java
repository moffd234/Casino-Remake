package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TriviaGameTest {
    TriviaGame game;

    @Before
    public void setUp(){
        game = new TriviaGame();
    }

    @Test
    public void testGetQuestionResponse(){
        TriviaGame game = new TriviaGame();
        String response = game.getQuestionResponse();

        boolean actual = response.contains("response_code");
        Assert.assertTrue(actual);
    }

    @Test
    public void testPrintWelcomeMessage() {
        String expected = " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$        /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$    /$$ /$$$$$$  /$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$      |__  $$__/| $$__  $$|_  $$_/| $$   | $$|_  $$_/ /$$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$         | $$   | $$  \\ $$  | $$  | $$   | $$  | $$  | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$         | $$   | $$$$$$$/  | $$  |  $$ / $$/  | $$  | $$$$$$$$\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$         | $$   | $$__  $$  | $$   \\  $$ $$/   | $$  | $$__  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$         | $$   | $$  \\ $$  | $$    \\  $$$/    | $$  | $$  | $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/         | $$   | $$  | $$ /$$$$$$   \\  $/    /$$$$$$| $$  | $$\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/          |__/   |__/  |__/|______/    \\_/    |______/|__/  |__/";
        String actual = game.printWelcomeMessage();

        Assert.assertEquals(expected, actual);
    }
}
