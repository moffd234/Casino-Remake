package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.IOConsole;

public interface GamblingGameInterface extends GameInterface{

    default double getWagerAmount(CasinoAccount playerAccount){
        IOConsole console = new IOConsole();
        double wagerAmount = console.getDoubleInput("Enter a wager amount");
        while(wagerAmount > playerAccount.getBalance() || wagerAmount < 1.00){
            System.out.println("Wager must be <= your total balance and > $1.00\nYour balance: "
                    + playerAccount.getBalance());
            wagerAmount = console.getDoubleInput("Enter a wager amount");
        }
        return wagerAmount;
    }
}
