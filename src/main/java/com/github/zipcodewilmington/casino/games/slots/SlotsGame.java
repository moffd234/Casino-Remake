package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GamblingGameInterface {
    SlotsPlayer player;

    @Override
    public int getWagerAmount() {
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

    @Override
    public void run() {

    }

    @Override
    public String printWelcomeMessage() {
        return "";
    }


}
