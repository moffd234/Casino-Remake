package com.github.zipcodewilmington.casino;

public interface GamblingPlayerInterface extends PlayerInterface {
    int wager();

    int updateAccount(int amount);
}
