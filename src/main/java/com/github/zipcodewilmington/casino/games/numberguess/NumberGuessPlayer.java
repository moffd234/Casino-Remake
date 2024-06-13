package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer implements PlayerInterface {
    CasinoAccount arcadeAccount;

    public NumberGuessPlayer(CasinoAccount arcadeAccount){
        this.arcadeAccount = arcadeAccount;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return new CasinoAccount();
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}