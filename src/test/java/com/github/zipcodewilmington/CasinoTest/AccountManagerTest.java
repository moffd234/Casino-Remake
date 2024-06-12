package com.github.zipcodewilmington.CasinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccountManagerTest {
    CasinoAccountManager accountManager;

    @Before
    public void setup(){
        accountManager = new CasinoAccountManager();
    }

    @Test
    public void testCreateAccountInstanceOf() {
        String username = "username";
        String password = "password";

        CasinoAccount account = accountManager.createAccount(username, password);

        Assert.assertTrue(account instanceof CasinoAccount);
    }

    @Test
    public void testCreateAccountUsernamePassword() {
        String expectedUsername = "username";
        String expectedPassword = "password";

        CasinoAccount account = accountManager.createAccount(expectedUsername, expectedPassword);

        String actualUsername = account.getUsername();
        String actualPassword = account.getPassword();

        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void getAccountListTestEmpty() {
        ArrayList<CasinoAccount> expected = new ArrayList<>();
        ArrayList<CasinoAccount> actual = accountManager.getAccountList();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest() {
        CasinoAccount account = accountManager.createAccount("username", "password");
        ArrayList<CasinoAccount> expected = new ArrayList<>();
        expected.add(account);

        accountManager.addAccount(account);
        ArrayList<CasinoAccount> actual = accountManager.getAccountList();

        Assert.assertEquals(expected, actual);
    }
}
