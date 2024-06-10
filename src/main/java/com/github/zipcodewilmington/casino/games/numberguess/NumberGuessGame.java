package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GameInterface {
    private final IOConsole console = new IOConsole();
    private ArrayList<NumberGuessPlayer> players = new ArrayList<>();

    public NumberGuessGame(NumberGuessPlayer player) {
        add(player);
    }

    public int getRandomNum() {
        Random random = new Random();
        return random.nextInt(11); // Gets a num where 0 <= num < 11
    }

    public boolean isWinner(int num, int randNum) {
        return num == randNum;
    }

    // TODO: Find out how to make unit test console input in V2
    public int getInput(){
        NumberGuessPlayer player1 = players.get(0);
        return console.getIntegerInput("Enter a number between 0 - 10 (inclusive)");
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
}