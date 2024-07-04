package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class TicTacToe implements GameInterface {
    TicTacToePlayer player;

    @Override
    public void add(PlayerInterface player) {
        this.player = (TicTacToePlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        if(this.player == player) {
            this.player = null;
        }
    }

    @Override
    public void run() {

    }

    @Override
    public String printWelcomeMessage() {
        return "";
    }

    public TicTacToePlayer getPlayer() {
        return player;
    }
}
