package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer implements PlayerInterface {
    CasinoAccount arcadeAccount;

    public SlotsPlayer(CasinoAccount arcadeAccount) {
        this.arcadeAccount = arcadeAccount;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return this.arcadeAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}