package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGame;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;

public class TriviaGameTest {

    @Test
    public void testGetQuestionResponse(){
        TriviaGame game = new TriviaGame();
        String response = game.getQuestionResponse();

        boolean actual = response.contains("response_code");
        Assert.assertTrue(actual);
    }
}
