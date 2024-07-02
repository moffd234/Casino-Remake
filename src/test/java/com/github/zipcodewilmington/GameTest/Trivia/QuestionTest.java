package com.github.zipcodewilmington.GameTest.Trivia;

import com.github.zipcodewilmington.casino.games.TriviaGame.Question;
import org.junit.Assert;
import org.junit.Test;

public class QuestionTest {

    @Test
    public void testGetQuestion() {
        String expected = "Question";
        Question question = new Question(expected, "false");

        String actual = question.getQ();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAnswerFalse() {
        Question question = new Question("Question", "false");

        boolean actual = question.getA();

        Assert.assertFalse(actual);
    }

    @Test
    public void testGetAnswerFalse1() {
        Question question = new Question("Question", "False");

        boolean actual = question.getA();

        Assert.assertFalse(actual);
    }

    @Test
    public void testGetAnswerTrue() {
        Question question = new Question("Question", "true");

        boolean actual = question.getA();

        Assert.assertTrue(actual);
    }

    @Test
    public void testGetAnswerTrue1() {
        Question question = new Question("Question", "True");

        boolean actual = question.getA();

        Assert.assertTrue(actual);
    }
}
