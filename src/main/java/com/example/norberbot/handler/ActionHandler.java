package com.example.norberbot.handler;

import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.methods.MethodsClient;

public interface ActionHandler {
    public void handle(ActionContext event, String content, MethodsClient client);
}
