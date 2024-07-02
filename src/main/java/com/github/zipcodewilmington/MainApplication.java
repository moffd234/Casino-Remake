package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGame;
import com.github.zipcodewilmington.casino.games.TriviaGame.TriviaGamePlayer;

public class MainApplication {
    public static void main(String[] args) {
//        new Casino().run();
        TriviaGame triviaGame = new TriviaGame();
        triviaGame.add(new TriviaGamePlayer(new CasinoAccount("test", "test")));
        triviaGame.run();
    }
}
