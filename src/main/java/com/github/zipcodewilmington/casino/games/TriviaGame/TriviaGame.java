package com.github.zipcodewilmington.casino.games.TriviaGame;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import com.github.zipcodewilmington.utils.FileLogger;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TriviaGame implements GameInterface {
    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

    }

    @Override
    public String printWelcomeMessage() {
        return "";
    }

    public Response getQuestionResponse(){
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://opentdb.com/api.php?amount=10&difficulty=easy&type=boolean").build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            response.close();
            return response;
        } catch (IOException e) {
            FileLogger.logSevere("Error getting question response " + e.getMessage());
        }
        return null;
    }

}
