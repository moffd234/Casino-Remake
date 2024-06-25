package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.games.TriviaGame.Question;
import org.junit.Assert;
import org.junit.Test;

public class QuestionTest {

    @Test
    public void testGetQuestion() {
        String expected = "Question";
        Question question = new Question(expected, "Answer");

        String actual = question.getQ();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAnswer() {
        String expected = "Answer";
        Question question = new Question("Question", expected);

        String actual = question.getA();

        Assert.assertEquals(expected, actual);
    }
}
