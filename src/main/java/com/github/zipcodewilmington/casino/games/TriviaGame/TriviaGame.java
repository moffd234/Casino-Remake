package com.github.zipcodewilmington.casino.games.TriviaGame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import com.github.zipcodewilmington.utils.FileLogger;
import com.github.zipcodewilmington.utils.IOConsole;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class TriviaGame implements GameInterface {
    TriviaGamePlayer player;
    CasinoAccount playerAccount;
    IOConsole console = new IOConsole();

    @Override
    public void add(PlayerInterface player) {
        this.player = (TriviaGamePlayer) player;
        this.playerAccount = player.getArcadeAccount();
    }

    @Override
    public void remove(PlayerInterface player) {
        if(this.player == player){
            this.player = null;
            this.playerAccount = null;
        }
    }

    @Override
    public void run() {
        System.out.println(printWelcomeMessage());

        while(getContinueInput().equals("yes")){
            int score = 0;
            ArrayList<Question> questions = getQuestions(getQuestionResponse());
            for(Question question : questions){
                if(handleQuestion(question)){
                    score++;
                }
            }
            System.out.println(printGameOver(score));
        }
    }

    @Override
    public String printWelcomeMessage() {
        return " /$$      /$$ /$$$$$$$$ /$$        /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$       /$$$$$$$$ /$$$$$$        /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$    /$$ /$$$$$$  /$$$$$$ \n" +
                "| $$  /$ | $$| $$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/      |__  $$__//$$__  $$      |__  $$__/| $$__  $$|_  $$_/| $$   | $$|_  $$_/ /$$__  $$\n" +
                "| $$ /$$$| $$| $$      | $$      | $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$               | $$  | $$  \\ $$         | $$   | $$  \\ $$  | $$  | $$   | $$  | $$  | $$  \\ $$\n" +
                "| $$/$$ $$ $$| $$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$            | $$  | $$  | $$         | $$   | $$$$$$$/  | $$  |  $$ / $$/  | $$  | $$$$$$$$\n" +
                "| $$$$_  $$$$| $$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/            | $$  | $$  | $$         | $$   | $$__  $$  | $$   \\  $$ $$/   | $$  | $$__  $$\n" +
                "| $$$/ \\  $$$| $$      | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$               | $$  | $$  | $$         | $$   | $$  \\ $$  | $$    \\  $$$/    | $$  | $$  | $$\n" +
                "| $$/   \\  $$| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$         | $$  |  $$$$$$/         | $$   | $$  | $$ /$$$$$$   \\  $/    /$$$$$$| $$  | $$\n" +
                "|__/     \\__/|________/|________/ \\______/  \\______/ |__/     |__/|________/         |__/   \\______/          |__/   |__/  |__/|______/    \\_/    |______/|__/  |__/";
    }

    public String getQuestionResponse(){
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://opentdb.com/api.php?amount=10&difficulty=easy&type=boolean").build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.body() != null) {
                String output = response.body().string();
                response.close();
                return output;
            }
            response.close();
        } catch (IOException e) {
            FileLogger.logSevere("Error getting question response " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Question> getQuestions(String jsonString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            ArrayList<Question> questions = new ArrayList<>();

            JsonNode results = jsonNode.get("results");
            if (results.isArray()) {
                for (JsonNode result : results) {
                    Question question = new Question(result.get("question").asText(), result.get("correct_answer").asText());
                    questions.add(question);
                }
            }

            return questions;
        } catch (IOException e) {
            FileLogger.logSevere("Error parsing questions " + e.getMessage());
        }
        return null;
    }

    public TriviaGamePlayer getPlayer() {
        return player;
    }

    public boolean handleQuestion(Question question){
        System.out.println(question.getQ());
        boolean input = console.getBooleanInput("[True] [False]");
        if(input == question.getA()){
            System.out.println("Correct");
            return true;
        }
        System.out.println("Incorrect");
        return false;
    }

    public String printGameOver(int score){
        return "Game Over! Score: " + score + "/10";
    }
}
