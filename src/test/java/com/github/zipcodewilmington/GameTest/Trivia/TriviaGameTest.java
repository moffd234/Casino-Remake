package com.github.zipcodewilmington.GameTest.Trivia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.TriviaGame.Question;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGame;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGamePlayer;
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
        Assert.assertFalse(questions.get(0).getA());
    }

    @Test
    public void testGetQuestionsTrue() {
        String jsonString = "{\"response_code\":0,\"results\":[{\"type\":\"boolean\",\"difficulty\":\"easy\"," +
                "\"category\":\"General Knowledge\",\"question\":" +
                "\"Scotland voted to become an independent country during the referendum from September 2014.\"," +
                "\"correct_answer\":\"True\",\"incorrect_answers\":[\"True\"]}]}";

        ArrayList<Question> questions = game.getQuestions(jsonString);

        Assert.assertEquals(
                "Scotland voted to become an independent country during the referendum from September 2014.",
                questions.get(0).getQ());
        Assert.assertTrue(questions.get(0).getA());
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

    @Test
    public void testAdd(){
        CasinoAccount expectedAccount = new CasinoAccount("tester","tester");
        PlayerInterface expectedPlayer = new TriviaGamePlayer(expectedAccount);

        game.add(expectedPlayer);

        PlayerInterface actualPlayer = game.getPlayer();
        CasinoAccount actualAccount = actualPlayer.getArcadeAccount();

        Assert.assertEquals(expectedAccount, actualAccount);
        Assert.assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void testRemove(){
        CasinoAccount account = new CasinoAccount("tester","tester");
        PlayerInterface player = new TriviaGamePlayer(account);

        game.add(player);

        game.remove(player);

        PlayerInterface actualPlayer = game.getPlayer();

        Assert.assertNull(actualPlayer);
    }

    @Test
    public void testPrintGameOver(){
        String expected = "Game Over! Score: 10/10";

        String actual = game.printGameOver(10);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPrintGameOverZero(){
        String expected = "Game Over! Score: 0/10";

        String actual = game.printGameOver(0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateQuestion() throws Exception {
        String jsonString = "{\"question\":\"In Pok&eacute;mon, Pikachu is an Electric-type Pok&eacute;mon.\",\"correct_answer\":\"True\"}";
        JsonNode node = new ObjectMapper().readTree(jsonString);
        String expectedQuestion = "In Pokémon, Pikachu is an Electric-type Pokémon.";
        Question result = game.createQuestion(node);

        String actualQuestion = result.getQ();
        boolean actualAnswer = result.getA();

        Assert.assertEquals(expectedQuestion, actualQuestion);
        Assert.assertTrue(actualAnswer);
    }

    @Test
    public void testIsInstanceOfGameInterface(){
        Assert.assertTrue(game instanceof TriviaGame);
    }
}
