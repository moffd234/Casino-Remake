package com.github.zipcodewilmington.casino.games.CoinFlip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayerInterface;

public class CoinFlipPlayer implements GamblingPlayerInterface {

    CasinoAccount arcadeAccount;

    public CoinFlipPlayer(CasinoAccount arcadeAccount) {
        this.arcadeAccount = arcadeAccount;
    }


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
        return arcadeAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}
