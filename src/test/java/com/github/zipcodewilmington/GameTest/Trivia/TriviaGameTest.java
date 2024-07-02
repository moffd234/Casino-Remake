package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.games.TriviaGame.Question;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void testGetQuestions() {
        String jsonString = "{\"response_code\":0,\"results\":[{\"type\":\"boolean\",\"difficulty\":\"easy\"," +
                "\"category\":\"General Knowledge\",\"question\":" +
                "\"Scotland voted to become an independent country during the referendum from September 2014.\"," +
                "\"correct_answer\":\"False\",\"incorrect_answers\":[\"True\"]}]}";

        ArrayList<Question> questions = game.getQuestions(jsonString);

        Assert.assertEquals(
                "Scotland voted to become an independent country during the referendum from September 2014.",
                questions.get(0).getQ());
        Assert.assertEquals("False", questions.get(0).getA());
    }

    @Test
    public void testGetQuestions_EmptyResults() {
        String jsonString = "{\"response_code\":0,\"results\":[]}";

        ArrayList<Question> questions = game.getQuestions(jsonString);

        Assert.assertTrue(questions.isEmpty());
    }

    @Test
    public void testGetQuestions_InvalidJson() {
        String jsonString = "Test Invalid JSON";
        TriviaGame triviaGame = new TriviaGame();

        ArrayList<Question> questions = triviaGame.getQuestions(jsonString);

       Assert.assertNull(questions);
    }
}
