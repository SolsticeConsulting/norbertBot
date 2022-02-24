package com.example.norberbot.handler;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.Event;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.Blocks.actions;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.button;

public class SlackHandler {

    protected void replyToAnEventWithBlock(Event event, @NotNull MethodsClient client, LayoutBlock... blockElements) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(req -> req
                .channel(appMentionEvent.getChannel())
                .blocks(asBlocks(
                        blockElements
                ))
        );
    }

    protected List<LayoutBlock> buildBlockElements(String header, List<String> actionables) {
        List<LayoutBlock> blockElements = new ArrayList<>();
        blockElements.add(section(section -> section.text(markdownText(header))));
        blockElements.add(divider());
        List<BlockElement> buttonElements = new ArrayList<>();
        for (String actionable : actionables) {
            buttonElements.add(button(b -> b.text(plainText(pt -> pt.text(actionable))).value(actionable).actionId(actionable)));
        }
        blockElements.add(actions(buttonElements));
        return blockElements;
    }

    protected void postMessageToChannel(Event event, @NotNull MethodsClient client, LayoutBlock... blockElements) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(req -> req
                .channel(appMentionEvent.getChannel())
                .blocks(asBlocks(
                        blockElements
                ))
        );
    }
}
