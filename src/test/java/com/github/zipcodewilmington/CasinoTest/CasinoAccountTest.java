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

    @Test
    public void addWinningsTest(){
        Double expected = 25.0;
        Double actual = account.addWinnings(25.0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addWinningsTestNegative(){
        try {
            account.addWinnings(-25.0);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Optionally, you can check the exception message or other properties
            Assert.assertEquals("Wager must be a positive number", e.getMessage());
        }
    }

    @Test
    public void addWinningsTestZero(){
        try {
            account.addWinnings(0.0);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Optionally, you can check the exception message or other properties
            Assert.assertEquals("Wager must be a positive number", e.getMessage());
        }
    }

    @Test
    public void subtractLossesTest(){
        Double expected = 25.0;
        account.setBalance(100);

        Double actual = account.subtractLosses(75);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void subtractLossesTestNegative(){
        try {
            account.subtractLosses(-25.0);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Optionally, you can check the exception message or other properties
            Assert.assertEquals("Wager must be a positive number", e.getMessage());
        }
    }

    @Test
    public void subtractLossesTestZero(){
        try {
            account.addWinnings(0.0);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Optionally, you can check the exception message or other properties
            Assert.assertEquals("Wager must be a positive number", e.getMessage());
        }
    }

    @Test
    public void toStringTest() {
        String expected = "CasinoAccount{" +
                "username='" + account.getUsername() + '\'' +
                ", password='" + account.getPassword() + '\'' +
                ", balance=" + account.getBalance() +
                '}';

        String actual = account.toString();

        Assert.assertEquals(expected, actual);
    }
}
