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
    private NumberGuessPlayer player;

    public NumberGuessGame(NumberGuessPlayer player) {
        this.player = player;
    }

    public int getRandomNum() {
        Random random = new Random();
        return random.nextInt(11); // Gets a num where 0 <= num < 11
    }

    public boolean isWinner(int num, int randNum) {
        return num == randNum;
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

    }
    
}