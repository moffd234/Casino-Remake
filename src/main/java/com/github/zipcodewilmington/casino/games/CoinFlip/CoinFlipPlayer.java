package com.github.zipcodewilmington.casino.games.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayerInterface;

public class CoinFlipPlayer implements GamblingPlayerInterface {
    @Override
    public int wager() {
        return 0;
    }

    @Override
    public int updateAccount(int amount) {
        return 0;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return null;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}
