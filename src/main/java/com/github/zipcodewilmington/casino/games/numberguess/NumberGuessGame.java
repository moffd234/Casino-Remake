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

    }

    @Override
    public String printWelcomeMessage() {
        return null;
    }

    public ArrayList<NumberGuessPlayer> getPlayers() {
        return players;
    }
}