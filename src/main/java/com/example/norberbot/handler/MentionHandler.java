package com.example.norberbot.handler;

import com.example.norberbot.helper.AnswerHelper;
import com.example.norberbot.model.Definition;
import com.example.norberbot.service.WordService;
import com.google.common.base.Splitter;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.Event;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.button;

@Component
public class MentionHandler implements EventHandler {

    @Autowired
    private WordService wordService;

    @Override
    public void handle(Event event, String eventContent, MethodsClient client) {
        String[] content = eventContent.split("<+@+\\w+>+");
        List<LayoutBlock> layoutBlockAnswer = new ArrayList<>();
        if (!ArrayUtils.isEmpty(content)) {
            String mentionContent = content[0].replaceAll("\\s", "");
            if (Pattern.matches("\\w+(:+)", mentionContent)) {
                String word = Splitter.on(":").split(mentionContent).iterator().next();
                layoutBlockAnswer = List.copyOf(handleDefinition(word));
            } else if (mentionContent.equals("opciones")) {
                List<String> availableDefinitions = StreamEx.of(wordService.getAllWords()).map(Definition::getWord).toList();
                layoutBlockAnswer = List.copyOf(buildBlockElements(AnswerHelper.getOptionsHeaderText(), availableDefinitions));
            } else if (mentionContent.equals("crear")) {
                layoutBlockAnswer.add(section(section -> section.text(markdownText("hola"))));
                layoutBlockAnswer.add(divider());
                List<BlockElement> buttonElements = new ArrayList<>();
                buttonElements.add(button(b -> b.text(plainText(pt -> pt.text("hola"))).value("hola").actionId("hola"+1)));
                layoutBlockAnswer.add(actions(buttonElements));
            }
        }
        try {
            replyToAnEventWithBlock(event, client, layoutBlockAnswer.toArray(LayoutBlock[]::new));
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<LayoutBlock> handleDefinition(String word) {
        List<String> possibleDefinitions = StreamEx.of(wordService.findWordsThatContains(word)).map(Definition::getWord).toList();
        if (possibleDefinitions.isEmpty()) {
            List<String> availableDefinitions = StreamEx.of(wordService.getAllWords()).map(Definition::getWord).toList();
            return buildBlockElements(AnswerHelper.getNoDefinitionsText(), availableDefinitions);
        }
        return buildBlockElements(AnswerHelper.getDefinitionsHeader(), possibleDefinitions);
    }

    private List<LayoutBlock> buildBlockElements(String header, List<String> actionables) {
        List<LayoutBlock> blockElements = new ArrayList<>();
        blockElements.add(section(section -> section.text(markdownText(header))));
        blockElements.add(divider());
        List<BlockElement> buttonElements = new ArrayList<>();
        for (String actionable : actionables) {
            buttonElements.add(button(b -> b.text(plainText(pt -> pt.text(actionable))).value(actionable).actionId("hola"+Math.random())));
        }
        blockElements.add(actions(buttonElements));
        return blockElements;
    }

    @Override
    public void replyToAnEventWithBlock(Event event, @NotNull MethodsClient client, LayoutBlock... blockElements) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(req -> req
                .channel(appMentionEvent.getChannel())
                .blocks(asBlocks(
                        blockElements
                ))
        );
    }
}
