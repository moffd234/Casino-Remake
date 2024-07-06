package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class TicTacToe implements GameInterface {

    private final int ROWS = 3;
    private final int COLS = 3;
    private TicTacToePlayer player;
    private String[][] gameBoard = new String[ROWS][COLS];

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
        return " /$$      /$$           /$$                                                   /$$$$$$$$              /$$$$$$$$ /$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$  /$$$$$$$$\n" +
                "| $$  /$ | $$          | $$                                                  |__  $$__/             |__  $$__/|_  $$_/ /$$__  $$|__  $$__//$$__  $$ /$$__  $$|__  $$__//$$__  $$| $$_____/\n" +
                "| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$          | $$  /$$$$$$          | $$     | $$  | $$  \\__/   | $$  | $$  \\ $$| $$  \\__/   | $$  | $$  \\ $$| $$      \n" +
                "| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$         | $$ /$$__  $$         | $$     | $$  | $$         | $$  | $$$$$$$$| $$         | $$  | $$  | $$| $$$$$   \n" +
                "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$         | $$| $$  \\ $$         | $$     | $$  | $$         | $$  | $$__  $$| $$         | $$  | $$  | $$| $$__/   \n" +
                "| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/         | $$| $$  | $$         | $$     | $$  | $$    $$   | $$  | $$  | $$| $$    $$   | $$  | $$  | $$| $$      \n" +
                "| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$         | $$|  $$$$$$/         | $$    /$$$$$$|  $$$$$$/   | $$  | $$  | $$|  $$$$$$/   | $$  |  $$$$$$/| $$$$$$$$\n" +
                "|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/         |__/ \\______/          |__/   |______/ \\______/    |__/  |__/  |__/ \\______/    |__/   \\______/ |________/";
    }

    public TicTacToePlayer getPlayer() {
        return player;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void initGameBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                gameBoard[i][j] = " ";
            }
        }
    }
}
