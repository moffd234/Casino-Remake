package com.github.zipcodewilmington.casino.games.TriviaGame;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class TriviaGamePlayer implements PlayerInterface {
    CasinoAccount account;

    public TriviaGamePlayer(CasinoAccount account) {
        this.account = account;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return account;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}
