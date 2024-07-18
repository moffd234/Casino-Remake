package com.github.zipcodewilmington.casino.games.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class CoinFlipGame implements GamblingGameInterface {

    CoinFlipPlayer player;
    CasinoAccount account;

    @Override
    public void add(PlayerInterface player) {
        this.player = (CoinFlipPlayer) player;
        account = player.getArcadeAccount();
    }

    @Override
    public void remove(PlayerInterface player) {
        if(this.player == player) {
            this.player = null;
            this.account = null;
        }
    }

    @Override
    public void run() {

    }

    @Override
    public String printWelcomeMessage() {
        return " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$         /$$$$$$   /$$$$$$  /$$$$$$ /$$   /$$ /$$$$$$$$ /$$       /$$$$$$ /$$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$       /$$__  $$ /$$__  $$|_  $$_/| $$$ | $$| $$_____/| $$      |_  $$_/| $$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$      | $$  \\__/| $$  \\ $$  | $$  | $$$$| $$| $$      | $$        | $$  | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$      | $$      | $$  | $$  | $$  | $$ $$ $$| $$$$$   | $$        | $$  | $$$$$$$/\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$      | $$      | $$  | $$  | $$  | $$  $$$$| $$__/   | $$        | $$  | $$____/ \n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$      | $$    $$| $$  | $$  | $$  | $$\\  $$$| $$      | $$        | $$  | $$      \n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/      |  $$$$$$/|  $$$$$$/ /$$$$$$| $$ \\  $$| $$      | $$$$$$$$ /$$$$$$| $$      \n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/        \\______/  \\______/ |______/|__/  \\__/|__/      |________/|______/|__/";
    }

    public int getRandomNum(){
        return (int)(Math.random() * 2);
    }

    public String handleHeadsTails(){
        int randomNum = getRandomNum();
        if(randomNum == 0){
            return "tails";
        }
        return "heads";
    }

    public CasinoAccount getAccount() {
        return account;
    }

    public CoinFlipPlayer getPlayer() {
        return player;
    }
}
