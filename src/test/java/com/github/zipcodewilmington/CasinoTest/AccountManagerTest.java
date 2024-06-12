package com.github.zipcodewilmington.CasinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
