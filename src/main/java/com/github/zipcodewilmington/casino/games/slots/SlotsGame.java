package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GamblingGameInterface {
    SlotsPlayer player;

    @Override
    public double getWagerAmount() {
        return 0;
    }

    @Override
    public void add(PlayerInterface player) {
        this.player = (SlotsPlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        if(player == this.player) {
            this.player = null;
        }
    }

    public String[] getSymbols(){
        List<String> givenList = Arrays.asList("7", "Bell", "Bar", "Cherry", "Lemon", "Orange");
        Random rand = new Random();

        String[] output = new String[3];
        for(int i = 0; i < 3; i++){
            output[i] = givenList.get(rand.nextInt(givenList.size()));;
        }
        return output;
    }

    @Override
    public void run() {
    }

    @Override
    public String printWelcomeMessage() {
        return " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$         /$$$$$$  /$$        /$$$$$$  /$$$$$$$$ /$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$       /$$__  $$| $$       /$$__  $$|__  $$__//$$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$  \\__/| $$      | $$  \\ $$   | $$  | $$  \\__/\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      |  $$$$$$ | $$      | $$  | $$   | $$  |  $$$$$$ \n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$       \\____  $$| $$      | $$  | $$   | $$   \\____  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$       /$$  \\ $$| $$      | $$  | $$   | $$   /$$  \\ $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      |  $$$$$$/| $$$$$$$$|  $$$$$$/   | $$  |  $$$$$$/\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/        \\______/ |________/ \\______/    |__/   \\______/ ";
    }


    public String printRules() {
        return "Rules:\n" +
                "1. Enter a wager amount.\n" +
                "2. Match three symbols on the pay line to win.\n" +
                "3. Payouts vary based on the symbols matched:\n" +
                "   - Three 7s: Jackpot(10x)\n" +
                "   - Three Bells: Big Win(5x)\n" +
                "   - Three Bars: Medium Win (2x)\n" +
                "   - Three Cherries: Small Win (1.5x)\n" +
                "   - Any other combination: No Win\n";
    }

    public Boolean isWinner(String[] symbols){
        return symbols[0].equals(symbols[1]) && symbols[0].equals(symbols[2]);
    }

    public SlotsPlayer getPlayer() {
        return player;
    }
}
