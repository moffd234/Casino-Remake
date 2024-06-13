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

            if ("select-game".equals(arcadeDashBoardInput)) {
                CasinoAccount casinoAccount = getAccount(casinoAccountManager);
                boolean isValidLogin = casinoAccount != null;

                if (isValidLogin) {
                    String gameSelectionInput = getGameSelectionInput().toUpperCase();

                    handleGameSelection(gameSelectionInput);
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

    private void handleGameSelection(String gameSelectionInput) {
        if (gameSelectionInput.equals("SLOTS")) {
            play(new SlotsGame(), new SlotsPlayer());

        } else if (gameSelectionInput.equals("NUMBERGUESS")) {
//                        play(new NumberGuessGame(), new NumberGuessPlayer());

        } else {
            // TODO - implement better exception handling

            String errorMessage = "[ %s ] is an invalid game selection";
            throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
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
                .append("\n\t[ create-account ], [ select-game ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ]")
                .toString());
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }
}
