package com.github.zipcodewilmington.GameTest.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeTest {
    TicTacToe game;

    @Before
    public void setUp(){
        game = new TicTacToe();
    }

    @Test
    public void testAdd(){
        TicTacToePlayer expected = new TicTacToePlayer();

        game.add(expected);

        TicTacToePlayer actual = game.getPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        TicTacToePlayer player = new TicTacToePlayer();
        game.add(player);

        game.remove(player);

        TicTacToePlayer actual = game.getPlayer();

        Assert.assertNull(actual);
    }

    @Test
    public void testGetPlayerNull(){
        TicTacToePlayer actual = game.getPlayer();

        Assert.assertNull(actual);
    }

    @Test
    public void testPrintWelcomeMessage(){
        String expected = " /$$      /$$           /$$                                                   /$$$$$$$$              /$$$$$$$$ /$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$  /$$$$$$$$\n" +
                "| $$  /$ | $$          | $$                                                  |__  $$__/             |__  $$__/|_  $$_/ /$$__  $$|__  $$__//$$__  $$ /$$__  $$|__  $$__//$$__  $$| $$_____/\n" +
                "| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$          | $$  /$$$$$$          | $$     | $$  | $$  \\__/   | $$  | $$  \\ $$| $$  \\__/   | $$  | $$  \\ $$| $$      \n" +
                "| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$         | $$ /$$__  $$         | $$     | $$  | $$         | $$  | $$$$$$$$| $$         | $$  | $$  | $$| $$$$$   \n" +
                "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$         | $$| $$  \\ $$         | $$     | $$  | $$         | $$  | $$__  $$| $$         | $$  | $$  | $$| $$__/   \n" +
                "| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/         | $$| $$  | $$         | $$     | $$  | $$    $$   | $$  | $$  | $$| $$    $$   | $$  | $$  | $$| $$      \n" +
                "| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$         | $$|  $$$$$$/         | $$    /$$$$$$|  $$$$$$/   | $$  | $$  | $$|  $$$$$$/   | $$  |  $$$$$$/| $$$$$$$$\n" +
                "|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/         |__/ \\______/          |__/   |______/ \\______/    |__/  |__/  |__/ \\______/    |__/   \\______/ |________/";
        String actual = game.printWelcomeMessage();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGetGameBoardEmpty(){
        char[][] expected = new char[3][3];
        char[][] actual = game.getGameBoard();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testInitGameBoard(){
        game.initGameBoard();
        char[][] GameBoard = game.getGameBoard();
        char expected = ' ';

        for (char[] chars : GameBoard) {
            for (char actual : chars) {
                Assert.assertEquals(expected, actual);
            }
        }
    }

    @Test
    public void testCellAlreadyOccupiedFalse(){
        game.initGameBoard();

        boolean actual = game.isCellOccupied(0, 0);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCellAlreadyOccupiedTrue(){
        game.initGameBoard();

        game.placeTurn(0, 0);

        boolean actual = game.isCellOccupied(0, 0);

        Assert.assertTrue(actual);
    }

    @Test
    public void testGetTurn(){
        char expected = 'x';

        char actual = game.getTurn();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGetTurnO(){
        char expected = 'o';

        game.initGameBoard();
        boolean placed = game.placeTurn(0, 0);

        char actual = game.getTurn();

        Assert.assertEquals(expected, actual);
        Assert.assertTrue(placed);
    }

    @Test
    public void testPlaceTurn() {
        char expectedCellVal = 'x';
        char expectedTurnVal = 'o';

        game.initGameBoard();
        boolean placed = game.placeTurn(0, 0);

        char[][] gameBoard = game.getGameBoard();
        char actualCellVal = gameBoard[0][0];
        char actualTurnVal = game.getTurn();

        Assert.assertEquals(expectedCellVal, actualCellVal);
        Assert.assertEquals(expectedTurnVal, actualTurnVal);
        Assert.assertTrue(placed);
    }

    @Test
    public void testPlaceTurnOccupied(){
        char expectedCellVal = 'x';
        char expectedTurnVal = 'o';

        game.initGameBoard();

        boolean placed = game.placeTurn(0, 0);

        char[][] gameBoard = game.getGameBoard();
        char actualCellVal = gameBoard[0][0];
        char actualTurnVal = game.getTurn();

        Assert.assertTrue(placed);

        placed = game.placeTurn(0, 0);

        Assert.assertEquals(expectedCellVal, actualCellVal);
        Assert.assertEquals(expectedTurnVal, actualTurnVal);

        // Test that you can't place a move in an occupied cell
        gameBoard = game.getGameBoard();
        actualCellVal = gameBoard[0][0];
        actualTurnVal = game.getTurn();

        Assert.assertEquals(expectedCellVal, actualCellVal);
        Assert.assertEquals(expectedTurnVal, actualTurnVal);
        Assert.assertFalse(placed);
    }

    @Test
    public void testPrintCell(){
        String expected = " x ";

        game.initGameBoard();
        game.placeTurn(0,0);

        String actual = game.printCell(0, 0);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void printCellEmpty(){
        String expected = "   ";

        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String actual = game.printCell(i, j);
                Assert.assertEquals(expected, actual);
            }
        }
    }

    @Test
    public void testLineSeparator(){
        String expected = "---+---+---\n";

        String actual = game.printLineSeparator();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPrintRow(){
        String expected =" x | o | x \n";

        game.initGameBoard();
        game.placeTurn(0,0);
        game.placeTurn(0,1);
        game.placeTurn(0,2);

        String actual = game.printRow(0);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPrintRowEmpty(){
        String expected = "   |   |   \n";

        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            String actual = game.printRow(i);
            Assert.assertEquals(expected,actual);
        }
    }

    @Test
    public void testPrintGameBoard(){
        String expected =
                " x | o | x \n" +
                "---+---+---\n" +
                " o | x | o \n" +
                "---+---+---\n" +
                " x | o | x \n";

        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                game.placeTurn(i, j);
            }
        }

        String actual = game.printGameBoard();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPrintGameBoardEmpty(){
        String expected =
                "   |   |   \n" +
                "---+---+---\n" +
                "   |   |   \n" +
                "---+---+---\n" +
                "   |   |   \n";

        game.initGameBoard();

        String actual = game.printGameBoard();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testIsBoardFull(){
        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                game.placeTurn(i, j);
            }
        }

        boolean actual = game.isBoardFull();

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsBoardFullFalse(){
        game.initGameBoard();

        boolean actual = game.isBoardFull();

        Assert.assertFalse(actual);
    }

    @Test
    public void testCheckWinner(){
        game.initGameBoard();

        // populate board for a diagonal ( \ ) win for x
        for(int i = 0; i < 3; i++){
            game.placeTurn(i, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.checkWinner('x');

        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerRightDiagonalO(){
        game.initGameBoard();

        // populate board for a diagonal ( \ ) win for o
        game.placeTurn(2,0); game.placeTurn(0,0);
        game.placeTurn(2,1); game.placeTurn(0,1);
        game.placeTurn(1,1); game.placeTurn(0,2);

        boolean actual = game.checkWinner('o');

        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerLeftDiagonalX(){
        game.initGameBoard();

        // populate board for a diagonal ( / ) win for x
        for(int i = 0; i < 3; i++){
            game.placeTurn(0, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.checkWinner('x');

        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerLeftDiagonalO(){
       game.initGameBoard();

        // populate board for a diagonal ( \ ) win for o
        game.placeTurn(2,2); game.placeTurn(0,2);
        game.placeTurn(1,0); game.placeTurn(1,1);
        game.placeTurn(1,2); game.placeTurn(2,0);

        boolean actual = game.checkWinner('o');

        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerVerticalX() {
        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            game.placeTurn(i, 0);
            game.placeTurn(i, 2);
        }

        boolean actual = game.checkWinner('x');
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerVerticalO() {
        game.initGameBoard();

        // populate board for a vertical win for o
        game.placeTurn(2,2); game.placeTurn(0,0);
        game.placeTurn(1,1); game.placeTurn(1,0);
        game.placeTurn(1,2); game.placeTurn(2,0);

        boolean actual = game.checkWinner('o');
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerHorizontalX(){
        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            game.placeTurn(0, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.checkWinner('x');
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckWinnerHorizontalO() {
        game.initGameBoard();

        // populate board for a horizontal win for o
        game.placeTurn(2,2); game.placeTurn(0,0);
        game.placeTurn(1,1); game.placeTurn(0,1);
        game.placeTurn(1,2); game.placeTurn(0,2);

        boolean actual = game.checkWinner('o');
        Assert.assertTrue(actual);
    }

    @Test
    public void testXWins() {
        game.initGameBoard();

        // populate board for a diagonal ( \ ) win for x
        for(int i = 0; i < 3; i++){
            game.placeTurn(i, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.xWins();

        Assert.assertTrue(actual);
    }

    @Test
    public void testOWins() {
        game.initGameBoard();

        // populate board for a diagonal ( \ ) win for o
        game.placeTurn(2,0); game.placeTurn(0,0);
        game.placeTurn(2,1); game.placeTurn(0,1);
        game.placeTurn(1,1); game.placeTurn(0,2);

        boolean actual = game.oWins();

        Assert.assertTrue(actual);
    }

    @Test
    public void testXWinsLeftDiagonal(){
        game.initGameBoard();

        // populate board for a diagonal ( / ) win for x
        for(int i = 0; i < 3; i++){
            game.placeTurn(0, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.xWins();

        Assert.assertTrue(actual);
    }

    @Test
    public void testOWinsLeftDiagonal(){
       game.initGameBoard();

        // populate board for a diagonal ( / ) win for o
        game.placeTurn(2,2); game.placeTurn(0,2);
        game.placeTurn(1,0); game.placeTurn(1,1);
        game.placeTurn(1,2); game.placeTurn(2,0);

        boolean actual = game.oWins();

        Assert.assertTrue(actual);
    }

    @Test
    public void testXWinsVertical() {
        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            game.placeTurn(i, 0);
            game.placeTurn(i, 2);
        }

        boolean actual = game.xWins();
        Assert.assertTrue(actual);
    }

    @Test
    public void testOWinsVertical() {
        game.initGameBoard();

        // populate board for a vertical win for o
        game.placeTurn(2,2); game.placeTurn(0,0);
        game.placeTurn(1,1); game.placeTurn(1,0);
        game.placeTurn(1,2); game.placeTurn(2,0);

        boolean actual = game.oWins();
        Assert.assertTrue(actual);
    }

    @Test
    public void testXWinsHorizontal(){
        game.initGameBoard();

        for(int i = 0; i < 3; i++){
            game.placeTurn(0, i);
            game.placeTurn(2, i);
        }

        boolean actual = game.xWins();
        Assert.assertTrue(actual);
    }

    @Test
    public void testOWinsHorizontal() {
        game.initGameBoard();

        // populate board for a horizontal win for o
        game.placeTurn(2,2); game.placeTurn(0,0);
        game.placeTurn(1,1); game.placeTurn(0,1);
        game.placeTurn(1,2); game.placeTurn(0,2);

        boolean actual = game.oWins();
        Assert.assertTrue(actual);
    }

    @Test
    public void testGetAvailableCells(){
        game.initGameBoard();
        ArrayList<int[]> expected = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            expected.add(new int[]{i, 1});
        }

        for(int i = 0; i < 3; i++){
            game.placeTurn(i, 0);
            game.placeTurn(i, 2);
        }

        ArrayList<int[]> actual = game.getAvailableCells();

        for (int[] expectedCell : expected) {
            boolean found = false;
            for (int[] actualCell : actual) {
                if (Arrays.equals(expectedCell, actualCell)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void testInheritance(){
        Assert.assertTrue(game instanceof GameInterface);
    }
}
