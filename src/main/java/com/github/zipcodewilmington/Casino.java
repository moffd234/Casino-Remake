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
                CasinoAccount casinoAccount = getAccount(casinoAccountManager);
                boolean isValidLogin = casinoAccount != null;

                if (isValidLogin) {
                    handleManageSelect(casinoAccount);
                } else {
                    // TODO - implement better exception handling
                    String errorMessage = "No account found that name and password";
                    System.out.println(errorMessage);
                }
            } else if ("create-account".equals(arcadeDashBoardInput)) {
                createNewAccount(casinoAccountManager);
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private void handleManageSelect(CasinoAccount casinoAccount) {
        String input = promptManageOrSelect();
        if(input.equalsIgnoreCase("select-game")){
            handleGameSelection(casinoAccount);
        }
        else if(input.equalsIgnoreCase("manage-account")) {
            handleAccountManagement(casinoAccount);
        }
        else{
            handleManageSelect(casinoAccount);
        }
    }

    private int handleAccountManagement(CasinoAccount casinoAccount) {
        String input;
        // TODO - Handle account managing inputs
        input = promptAddFundsOrGoBack(casinoAccount);
        if(input.equalsIgnoreCase("add-funds")){
            double amountToAdd = console.getDoubleInput("Enter amount to add");
            casinoAccount.updateBalance(amountToAdd);
        }
        else if(input.equalsIgnoreCase("go-back")){
            return 0;
        }
        else{
            System.out.println("Invalid input");
            handleAccountManagement(casinoAccount);
        }
        return 0;
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
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ create-account ], [ login ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ]")
                .toString());
    }

    private String promptManageOrSelect(){
        return console.getStringInput(new StringBuilder()
                .append("You are logged in!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ manage-account ], [ select-game ]")
                .toString());
    }

    private String promptAddFundsOrGoBack(CasinoAccount account){
        return console.getStringInput(new StringBuilder()
                .append("You have $" + account.getBalance())
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ add-funds ], [ go-back ]")
                .toString());
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }

}
