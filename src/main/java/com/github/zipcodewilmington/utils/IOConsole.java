package com.github.zipcodewilmington.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author leonhunter
 * @created 02/12/2020 - 6:01 PM
 * used to output prompt to user and get input from user
 */
public class IOConsole {
    private final Scanner input;
    private final PrintStream output;
    private final AnsiColor ansiColor;

    public IOConsole() {
        this(AnsiColor.AUTO);
    }

    public IOConsole(AnsiColor ansiColor) {
        this(ansiColor, System.in, System.out);
    }

    public IOConsole(AnsiColor ansiColor, InputStream in, PrintStream out) {
        this.ansiColor = ansiColor;
        this.input = new Scanner(in);
        this.output = out;
    }

    public void print(String val, Object... args) {
        output.format(ansiColor.getColor() + val, args);
    }

    public void println(String val, Object... vals) {
        print(val + "\n", vals);
    }

    public String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        String userInput = input.nextLine();
        checkForExit(userInput);  // Makes sure the user doesn't type exit to leave Casino
        return userInput;
    }

    public Boolean getBooleanInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args).toLowerCase();
        if (stringInput.equals("true") || stringInput.equals("false")) {
            return Boolean.parseBoolean(stringInput);
        } else {
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a true/false value!");
            return getBooleanInput(prompt, args);
        }
    }

    public String getYesNoInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args).toLowerCase();
        if (stringInput.equals("yes") || stringInput.equals("no")) {
            return stringInput;
        }
        else{
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a yes/no value!");
            return getYesNoInput(prompt, args);
        }
    }

    public Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            return Double.parseDouble(stringInput);
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            return Long.parseLong(stringInput);
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, args);
        }
    }

    public Integer getIntegerInput(String prompt, Object... args) {
        return getLongInput(prompt, args).intValue();
    }

    private void checkForExit(String input) {
        if ("exit".equalsIgnoreCase(input)) {
            println("Exiting the game...");
            System.exit(0);
        }
    }
}