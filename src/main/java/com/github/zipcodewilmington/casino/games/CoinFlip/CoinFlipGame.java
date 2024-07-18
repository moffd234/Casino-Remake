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

    }

    @Override
    public void run() {

    }

    @Override
    public String printWelcomeMessage() {
        return "";
    }

    public CasinoAccount getAccount() {
        return account;
    }

    public CoinFlipPlayer getPlayer() {
        return player;
    }
}
