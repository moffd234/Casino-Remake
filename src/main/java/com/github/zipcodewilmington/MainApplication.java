package com.github.zipcodewilmington;


import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;

public class MainApplication {
    public static void main(String[] args) {
        NumberGuessGame game = new NumberGuessGame(new NumberGuessPlayer());
        game.run();
    }
}
