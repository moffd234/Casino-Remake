package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;

public class MainApplication {
    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();
        game.add(new TicTacToePlayer());
        game.initGameBoard();
        game.simGame();
    }
}
