package com.example.norberbot.handler;

import com.example.norberbot.model.Definition;
import com.slack.api.bolt.App;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.Event;
import com.slack.api.model.event.MessageEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.Blocks.actions;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.button;

public class SlackHandler {

    protected void replyToAnEventWithBlockMessage(Event event, @NotNull MethodsClient client, LayoutBlock... blockElements) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(req -> req
                .channel(appMentionEvent.getChannel())
                .blocks(asBlocks(
                        blockElements
                ))
        );
    }

    protected List<LayoutBlock> buildBlockElements(String header, List<Definition> definitions) {
        List<LayoutBlock> blockElements = new ArrayList<>();
        blockElements.add(section(section -> section.text(markdownText(header))));
        blockElements.add(divider());
        List<BlockElement> buttonElements = new ArrayList<>();
        for (Definition definition : definitions) {
            buttonElements.add(button(b -> b.text(plainText(pt -> pt.text(definition.getWord()))).value(definition.getWord()).actionId(definition.getId().toString())));
        }
        blockElements.add(actions(buttonElements));
        return blockElements;
    }

    protected void replyToAnEventWithMessage(Event event, @NotNull MethodsClient client, String text) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(req -> req
                .channel(appMentionEvent.getChannel())
                .text(text)
        );
    }

    protected void replyToAnEventWithEphemeral(Event event, @NotNull MethodsClient client, String text) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostEphemeral(req -> req
                .user(appMentionEvent.getUser())
                .channel(appMentionEvent.getChannel())
                .text(text)
        );
    }

    protected void postCalendarMessage(@NotNull MethodsClient client, @NotNull String id, @NotNull String text) throws SlackApiException, IOException {
        var logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            client.chatPostMessage(r -> r
                    .token(System.getenv("SLACK_BOT_TOKEN"))
                    .channel(id)
                    .text(text)
            );
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }
}
