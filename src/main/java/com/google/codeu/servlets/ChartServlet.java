package com.google.codeu.servlets;
import com.google.gson.*;
import com.google.codeu.data.Message;

import java.io.IOException;
import java.util.List;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.codeu.data.Datastore;
import com.google.gson.JsonObject;

/**
 * Handles fetching site statistics.
 */
@WebServlet("/messagechart")
public class ChartServlet extends HttpServlet{

    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    /**
     * Responds with site statistics in JSON.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        // The following line should match however you manipulated getMessages() in Step 1
        List<Message> msgList = datastore.getMessages(null);
        Gson gson = new Gson();
        String json = gson.toJson(msgList);
        response.getWriter().println(json);
    }
}
