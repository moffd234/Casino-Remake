package com.github.zipcodewilmington.CasinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getAccountTest() {
        CasinoAccount expected = new CasinoAccount("username", "password");
        accountManager.addAccount(expected);

        CasinoAccount actual = accountManager.getAccount("username", "password");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountTestEmpty() {
        CasinoAccount actual = accountManager.getAccount("username", "password");

        Assert.assertNull(actual);
    }

    @Test
    public void writeToCSVTest(){
        final String csvFile = "./casinoAccounts.csv";
        accountManager.addAccount(new CasinoAccount("Jakob", "Jakob"));
        accountManager.addAccount(new CasinoAccount("Wilbur", "Jakob"));

        Assert.assertTrue(accountManager.writeAccountsToCSV());

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String> lines = new ArrayList<>();
            String line;
            // Add all the lines to the line list
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Verify that the correct lines are in the CSV file
            Assert.assertTrue(lines.stream().anyMatch(l -> l.startsWith("Jakob,Jakob")));
            Assert.assertTrue(lines.stream().anyMatch(l -> l.startsWith("Wilbur,Jakob")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void toStringTest(){
        String expected = "CasinoAccountManager{" +
                "accountList=" + accountManager.getAccountList() +
                "}";

        String actual = accountManager.toString();

        Assert.assertEquals(expected, actual);
    }
}
