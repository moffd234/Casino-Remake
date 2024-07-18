package com.github.zipcodewilmington.casino.games.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

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
        System.out.println(printWelcomeMessage());

        while(getContinueInput().equals("yes")){
            double wager = getWagerAmount(account);
            account.subtractLosses(wager);

            String guess= getHeadsTailsInput();
            String outcome = handleHeadsTails();

            System.out.println(handleOutcome(wager, guess, outcome));
        }
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

    public String getHeadsTailsInput(){
        IOConsole console = new IOConsole();

        String input = console.getStringInput("Enter Guess: Heads or Tails").toLowerCase();

        while(!input.equals("tails") && !input.equals("heads")){
            input = console.getStringInput("Incorrect Input. Please Enter Heads or Tails" +
                                                  "\nEnter Guess: Heads or Tails");
        }

        return input;
    }

    public String handleOutcome(double wager, String guess, String flipOutcome){
        if(guess.equals(flipOutcome)){
            account.addWinnings(wager * 2);
            return "YOU WIN! The answer was " + flipOutcome;
        }
        return "You Lose. The answer was " + flipOutcome;
    }

    public CasinoAccount getAccount() {
        return account;
    }

    public CoinFlipPlayer getPlayer() {
        return player;
    }
}
