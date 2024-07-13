package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.Random;

import java.util.ArrayList;

public class TicTacToe implements GameInterface {


    private char turn = 'x';
    private final int ROWS = 3;
    private final int COLS = 3;
    private TicTacToePlayer player;
    private final Random random = new Random();
    private final IOConsole console = new IOConsole();
    private final char[][] gameBoard = new char[ROWS][COLS];

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

    public void initGameBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                gameBoard[i][j] = ' ';
            }
        }
    }

    public String printGameBoard() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            output.append(printRow(i));
            if (i < ROWS - 1) {
                output.append(printLineSeparator());
            }
        }
        return output.toString();
    }

    public String printRow(int row) {
        StringBuilder rowOutput = new StringBuilder();
        for (int j = 0; j < COLS; j++) {
            rowOutput.append(printCell(row, j));
            if (j < COLS - 1) {
                rowOutput.append("|");
            }
        }
        rowOutput.append("\n");
        return rowOutput.toString();
    }

    public String printCell(int row, int col){
        return " " + gameBoard[row][col] + " ";
    }

    public String printLineSeparator(){
        return "---+---+---\n";
    }

    public boolean isBoardFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner(char player) {
    // Check horizontal and vertical wins
    for (int i = 0; i < 3; i++) {
        if ((gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player) ||
            (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player)) {
            return true;
        }
    }

    // Check diagonal wins
    return (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player) ||
                (gameBoard[2][0] == player && gameBoard[1][1] == player && gameBoard[0][2] == player);
    }

    public boolean xWins(){
        return checkWinner('x');
    }

    public boolean oWins(){
        return checkWinner('o');
    }

    public boolean isCellOccupied(int row, int col){
        return gameBoard[row][col] != ' ';
    }

    public boolean placeTurn(int row, int col){
        if(!isCellOccupied(row, col)) {
            gameBoard[row][col] = turn;
            turn = (turn == 'x') ? 'o' : 'x';
            return true;
        }
        return false;
    }

    public int getRow(){
        int row = console.getIntegerInput("Enter a row between 0 - 2");
        while(row < 0 || row > 2){
            row = console.getIntegerInput("Input out of bounds\nEnter a row between 0 - 2");
        }
        return row;
    }

    public int getCol(){
        int column = console.getIntegerInput("Enter a column between 0 - 2");
        while(column < 0 || column > 2){
            column = console.getIntegerInput("Input out of bounds\nEnter a column between 0 - 2");
        }
        return column;
    }

    public void getUserInput(){
        int row = getRow();
        int col = getCol();

        while(!placeTurn(row, col)){
            System.out.println("Cell is already occupied. Move somewhere else");
            row = getRow();
            col = getCol();
        }
    }

    public ArrayList<int[]> getAvailableCells(){
        ArrayList<int[]> availableCells = new ArrayList<>();

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(gameBoard[i][j] == ' '){
                    availableCells.add(new int[]{i, j});
                }
            }
        }

        return availableCells;
    }

    public void getAIMove(){
        ArrayList<int[]> availableCells = getAvailableCells();

        if(!availableCells.isEmpty()){
            int[] cell = availableCells.get(random.nextInt(availableCells.size()));
            placeTurn(cell[0], cell[1]);
        }

    }
    
    public char getTurn() {
        return turn;
    }

    public TicTacToePlayer getPlayer() {
        return player;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }
}
