package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.io.IOException;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);

    @Override
    public void run() {
        String arcadeDashBoardInput;
        CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
        do {
            arcadeDashBoardInput = getArcadeDashboardInput();

            if ("login".equals(arcadeDashBoardInput)) {
                handleLogin(casinoAccountManager);
            } else if ("create-account".equals(arcadeDashBoardInput)) {
                createNewAccount(casinoAccountManager);
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private void handleLogin(CasinoAccountManager casinoAccountManager) {
        CasinoAccount casinoAccount = getAccount(casinoAccountManager);
        boolean isValidLogin = casinoAccount != null;

        if (isValidLogin) {
            handleManageSelect(casinoAccount);
        } else {
            System.out.println("Invalid login");
            handleLogin(casinoAccountManager);
        }
    }

    private void handleManageSelect(CasinoAccount casinoAccount) {
        String input = promptManageOrSelect();
        if(input.trim().equalsIgnoreCase("select-game")){
            handleGameSelection(casinoAccount);
        }
        else if(input.trim().equalsIgnoreCase("manage-account")) {
            handleAccountManagement(casinoAccount);
        }
        else{
            handleManageSelect(casinoAccount);
        }
    }

    private void handleAccountManagement(CasinoAccount casinoAccount) {
        String input;
        input = promptAddFundsResetOrGoBack(casinoAccount);
        if(input.trim().equalsIgnoreCase("add-funds")){
            double amountToAdd = console.getDoubleInput("Enter amount to add");
            casinoAccount.updateBalance(amountToAdd);

        }
        else if(input.trim().equalsIgnoreCase("reset-password")){
            resetPassword(casinoAccount);
        }
        else if(input.trim().equalsIgnoreCase("go-back")){
            return;
        }
        else{
            System.out.println("Invalid input");
            handleAccountManagement(casinoAccount);
        }
    }

    private void handleGameSelection(CasinoAccount account) {
        String gameSelectionInput = getGameSelectionInput().toUpperCase();
        if (gameSelectionInput.equals("SLOTS")) {
            play(new SlotsGame(), new SlotsPlayer());

        } else if (gameSelectionInput.equals("NUMBERGUESS")) {
            NumberGuessPlayer player = new NumberGuessPlayer(account);
            play(new NumberGuessGame(player), player);

        } else {
            System.out.println("Invalid game selection");
            handleGameSelection(account);
        }
    }

    private void createNewAccount(CasinoAccountManager casinoAccountManager) {
        console.println("Welcome to the account-creation screen.");
        String accountName = console.getStringInput("Enter your account name:");
        String accountPassword = console.getStringInput("Enter your account password:");
        CasinoAccount newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
        while(newAccount == null){
            System.out.println("Invalid username");
            accountName = console.getStringInput("Enter your account name:");
            accountPassword = console.getStringInput("Enter your account password:");
            newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
        }
        try {
            casinoAccountManager.registerAccount(newAccount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CasinoAccount getAccount(CasinoAccountManager casinoAccountManager){
        String accountName = console.getStringInput("Enter your account name:");
        String accountPassword = console.getStringInput("Enter your account password:");
        return casinoAccountManager.getAccount(accountName, accountPassword);
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput("Welcome to the Arcade Dashboard!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ create-account ], [ login ]");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("Welcome to the Game Selection Dashboard!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ SLOTS ], [ NUMBERGUESS ]");
    }

    private String promptManageOrSelect(){
        return console.getStringInput("You are logged in!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ manage-account ], [ select-game ]");
    }

    private String promptAddFundsResetOrGoBack(CasinoAccount account){
        return console.getStringInput("You have $" + account.getBalance() +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ add-funds ], [reset-password], [ go-back ]");
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }

    private void resetPassword(CasinoAccount casinoAccount){
        String input = console.getStringInput("Enter new password");
        String confirmInput = console.getStringInput("Re-enter password");
        while(!input.equals(confirmInput)){
            System.out.println("Passwords do not match");
            input = console.getStringInput("Enter new password");
            confirmInput = console.getStringInput("Re-enter password");
        }
        casinoAccount.setPassword(input);
    }

}
