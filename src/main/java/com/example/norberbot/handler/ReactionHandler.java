package com.example.norberbot.handler;

import com.slack.api.methods.MethodsClient;
import com.slack.api.model.event.Event;
import org.springframework.stereotype.Component;

@Component
public class ReactionHandler implements EventHandler{
    @Override
    public void handle(Event event, String content, MethodsClient client) {

    }

    @Override
    public void replyToAnEvent(Event event, MethodsClient client, String answer) {

    }
}
