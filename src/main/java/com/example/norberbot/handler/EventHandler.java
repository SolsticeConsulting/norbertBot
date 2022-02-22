package com.example.norberbot.handler;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.event.Event;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface EventHandler {
    public void handle(Event event, String eventContent, MethodsClient client);
    public void replyToAnEventWithBlock(Event event, @NotNull MethodsClient client, LayoutBlock... blockElements) throws SlackApiException, IOException;
}
