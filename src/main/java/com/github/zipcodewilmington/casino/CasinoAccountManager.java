package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.CSVUtils;
import com.github.zipcodewilmington.utils.FileLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {

    private final ArrayList<CasinoAccount> accountList = new ArrayList<>();

    public CasinoAccountManager() {
        readCSV();
        FileLogger.logInfo("Loaded " + accountList.size() + " accounts from casinoAccounts.csv");
    }

    public void readCSV(){
        String csvFile = "./casinoAccounts.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // split line with comma
                String[] account = line.split(csvSplitBy);

                String username = account[0];
                String password = account[1];
                double balance = Double.parseDouble(account[2]);

                CasinoAccount casinoAccount = new CasinoAccount(username, password, balance);
                casinoAccount.setAccountManager(this);
                accountList.add(casinoAccount);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file. Accounts are not loaded");
            FileLogger.logWarning("Error reading CSV file.\n" + e);
        }
    }

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount getAccount(String accountName, String accountPassword) {
        for(CasinoAccount account : accountList){
            if(account.getUsername().equals(accountName) && account.getPassword().equals(accountPassword)) {
                return account;
            }
        }
        return null;
    }

    /**
     * logs & creates a new `ArcadeAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount createAccount(String accountName, String accountPassword) {
        for(CasinoAccount account : accountList){
            if(account.getUsername().equals(accountName)){
                return null;
            }
        }
        return new CasinoAccount(accountName, accountPassword);
    }

    /**
     * logs & registers a new `ArcadeAccount` to `this.getArcadeAccountList()`
     *
     * @param casinoAccount the arcadeAccount to be added to `this.getArcadeAccountList()`
     */
    public void registerAccount(CasinoAccount casinoAccount) throws IOException {

        addAccount(casinoAccount);

        writeAccountsToCSV();
    }

    public ArrayList<CasinoAccount> getAccountList() {
        return accountList;
    }

    public void addAccount(CasinoAccount casinoAccount) {
        accountList.add(casinoAccount);
    }

    public boolean writeAccountsToCSV(){
        String csvFile = "./casinoAccounts.csv";
        FileWriter writer;
        try {
            writer = new FileWriter(csvFile);

            for(CasinoAccount account : accountList) {
                List<String> list = new ArrayList<>();
                list.add(account.getUsername());
                list.add(account.getPassword());
                list.add(String.valueOf(account.getBalance()));

                CSVUtils.writeLine(writer, list);
            }
            writer.flush();
            writer.close();

        }
        catch (IOException e) {
            FileLogger.logSevere("Issue writing accounts to CSV\n" + e);
            return false;
        }
        FileLogger.logInfo("Wrote " + accountList.size() + " accounts to casinoAccounts.csv");

        return true;
    }

    @Override
    public String toString() {
        return "CasinoAccountManager{" +
                "accountList=" + accountList +
                '}';
    }
}
