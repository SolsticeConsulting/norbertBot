package com.example.norberbot.handler;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.Event;

import java.io.IOException;

public interface EventHandler {
    public void handle(Event event, String eventContent, MethodsClient client);
    public void replyToAnEvent(Event event, MethodsClient client, String answer) throws SlackApiException, IOException;
}
