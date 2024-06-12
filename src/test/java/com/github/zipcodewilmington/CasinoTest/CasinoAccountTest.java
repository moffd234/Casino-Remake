package com.github.zipcodewilmington.CasinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasinoAccountTest {
    CasinoAccount account;

    @Before
    public void setUp() {
        account =  new CasinoAccount("Dan", "Password");
    }

    @Test
    public void getUsernameTest() {
        String expected = "Dan";
        String actual = account.getUsername();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPasswordTest() {
        String expected = "Password";
        String actual = account.getPassword();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBalanceTest() {
        Double expected = 0.0;
        Double actual = account.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPasswordTest() {
        String expected = "Password123";

        account.setPassword("Password123");
        String actual = account.getPassword();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBalanceTest() {
        Double expected = 25.0;

        account.setBalance(25.0);
        Double actual = account .getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateBalanceTestPositive(){
        Double expected = 25.0;

        Double actual = account.updateBalance(25.0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateBalanceTestNegative(){
        Double expected = -25.0;

        Double actual = account.updateBalance(-25.0);

        Assert.assertEquals(expected, actual);
    }
}
