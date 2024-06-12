package com.github.zipcodewilmington.casino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {

    private ArrayList<CasinoAccount> accountList = new ArrayList<>();

    public CasinoAccountManager() {

    }

    public void readCSV(){
        String csvFile = "./casinoAccounts.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int nextId = Integer.parseInt(br.readLine());

            while ((line = br.readLine()) != null) {
                // split line with comma
                String[] account = line.split(csvSplitBy);

                String username = account[0];
                String password = account[1];
                double balance = Double.parseDouble(account[2]);

                accountList.add(new CasinoAccount(username, password, balance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount getAccount(String accountName, String accountPassword) {
        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        String currentClassName = getClass().getName();
        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
    }

    /**
     * logs & creates a new `ArcadeAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount createAccount(String accountName, String accountPassword) {
        return new CasinoAccount(accountName, accountPassword);
    }

    /**
     * logs & registers a new `ArcadeAccount` to `this.getArcadeAccountList()`
     *
     * @param casinoAccount the arcadeAccount to be added to `this.getArcadeAccountList()`
     */
    public void registerAccount(CasinoAccount casinoAccount) {
        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        String currentClassName = getClass().getName();
        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
    }

    public ArrayList<CasinoAccount> getAccountList() {
        return accountList;
    }

    public void addAccount(CasinoAccount casinoAccount) {
        accountList.add(casinoAccount);
    }
}
