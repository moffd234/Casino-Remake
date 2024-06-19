package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Random;
//TODO: TEST THE GAME AND WRITE A GetYesNoInput METHOD
/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GamblingGameInterface {
    private final IOConsole console = new IOConsole();
    private ArrayList<NumberGuessPlayer> players = new ArrayList<>();

    public NumberGuessGame(NumberGuessPlayer player) {
        add(player);
    }

    public int getRandomNum() {
        Random random = new Random();
        return random.nextInt(11); // Gets a num where 0 <= num < 11
    }


    // TODO: Find out how to make unit test for console input in V2
    public int getGuess(){
        int input = console.getIntegerInput("Enter a number between 0 - 10 (inclusive)");

        while(input < 0 || input > 10){
            input = console.getIntegerInput("Enter a number between 0 - 10 (inclusive)");
        }

        return input;
    }

    // TODO: Find out how to make unit test for console input in V2
    public String getContinueInput(){
        return console.getYesNoInput("Would you like to continue?");
    }

    public String handleOutcome(int guess, int randNum, int wager) {;
        NumberGuessPlayer player1 = players.get(0);
        CasinoAccount playerAccount = player1.getArcadeAccount();
        System.out.println(playerAccount);
        if (guess == randNum) {
            playerAccount.addWinnings(wager);
            return "You won! The answer was " + randNum;
        }

        playerAccount.subtractLosses(wager);
        return "You lost! Correct number: " + randNum;
    }

    @Override
    public void add(PlayerInterface player) {
        players.add((NumberGuessPlayer) player);
    }

    @Override
    public void remove(PlayerInterface player) {
        players.remove(player);
    }

    @Override
    public void run() {
        System.out.println(printWelcomeMessage());

        while(getContinueInput().equals("yes")){
            int wager = getWagerAmount();
            int guess = getGuess();
            int randomNum = getRandomNum();
            System.out.println(handleOutcome(guess, randomNum, wager));
        }
    }

    @Override
    public String printWelcomeMessage() {

        return " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$        /$$   /$$ /$$   /$$ /$$      /$$        /$$$$$$  /$$   /$$ /$$$$$$$$  /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$      | $$$ | $$| $$  | $$| $$$    /$$$       /$$__  $$| $$  | $$| $$_____/ /$$__  $$ /$$__  $$| $$_____/| $$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$$$| $$| $$  | $$| $$$$  /$$$$      | $$  \\__/| $$  | $$| $$      | $$  \\__/| $$  \\__/| $$      | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      | $$ $$ $$| $$  | $$| $$ $$/$$ $$      | $$ /$$$$| $$  | $$| $$$$$   |  $$$$$$ |  $$$$$$ | $$$$$   | $$$$$$$/\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$      | $$  $$$$| $$  | $$| $$  $$$| $$      | $$|_  $$| $$  | $$| $$__/    \\____  $$ \\____  $$| $$__/   | $$__  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$      | $$\\  $$$| $$  | $$| $$\\  $ | $$      | $$  \\ $$| $$  | $$| $$       /$$  \\ $$ /$$  \\ $$| $$      | $$  \\ $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      | $$ \\  $$|  $$$$$$/| $$ \\/  | $$      |  $$$$$$/|  $$$$$$/| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$$$$$$$| $$  | $$\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/       |__/  \\__/ \\______/ |__/     |__/       \\______/  \\______/ |________/ \\______/  \\______/ |________/|__/  |__/";
    }

    public ArrayList<NumberGuessPlayer> getPlayers() {
        return players;
    }

    @Override
    public int getWagerAmount() {
        return console.getIntegerInput("Enter a wager amount");
    }
}