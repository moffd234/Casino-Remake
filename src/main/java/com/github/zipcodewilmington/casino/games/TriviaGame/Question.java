package com.github.zipcodewilmington.casino.games.TriviaGame;

public class Question {
    private final String q;
    private final String a;

    public Question(String q, String a) {
        this.q = q;
        this.a = a;
    }

    public String getQ() {
        return q;
    }

    public boolean getA() {
        return Boolean.parseBoolean(a);
    }
}
