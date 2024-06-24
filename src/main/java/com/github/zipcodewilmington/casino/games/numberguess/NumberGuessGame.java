package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GamblingGameInterface {
    private final IOConsole console = new IOConsole();
    private NumberGuessPlayer player;
    private CasinoAccount playerAccount;

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

    public String handleOutcome(int guess, int randNum, double wager) {

        if (guess == randNum) {
            playerAccount.addWinnings(wager * 2);
            return "You won! The answer was " + randNum;
        }

        return "You lost! Correct number: " + randNum;
    }

    @Override
    public void add(PlayerInterface player) {
        this.player = (NumberGuessPlayer) player;
        this.playerAccount = player.getArcadeAccount();
    }

    @Override
    public void remove(PlayerInterface player) {
        if(this.player.equals(player)) {
            this.player = null;
            this.playerAccount = null;
        }
    }

    @Override
    public void run() {
        System.out.println(printWelcomeMessage());

        while(getContinueInput().equals("yes")){
            double wager = getWagerAmount();
            playerAccount.subtractLosses(wager);
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

    public NumberGuessPlayer getPlayer() {
        return player;
    }

    @Override
    public double getWagerAmount() {
        double wagerAmount = console.getDoubleInput("Enter a wager amount");
        while(wagerAmount > playerAccount.getBalance() || wagerAmount < 1.00){
            System.out.println("Wager must be <= your total balance and > $1.00\nYour balance: "
                    + playerAccount.getBalance());
            wagerAmount = console.getDoubleInput("Enter a wager amount");
        }
        return wagerAmount;
    }
}