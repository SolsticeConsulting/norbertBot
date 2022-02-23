package com.example.norberbot.handler;

import com.example.norberbot.helper.AnswerHelper;
import com.example.norberbot.model.Definition;
import com.example.norberbot.service.DefinitionService;
import com.google.common.base.Splitter;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.event.Event;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class MentionHandler extends SlackHandler implements EventHandler{

    @Autowired
    private DefinitionService definitionService;

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
                List<String> availableDefinitions = StreamEx.of(definitionService.getAllWords()).map(Definition::getWord).toList();
                layoutBlockAnswer = List.copyOf(buildBlockElements(AnswerHelper.getOptionsHeaderText(), availableDefinitions));
            }
        }
        try {
            replyToAnEventWithBlock(event, client, layoutBlockAnswer.toArray(LayoutBlock[]::new));
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<LayoutBlock> handleDefinition(String word) {
        List<String> possibleDefinitions = StreamEx.of(definitionService.findWordsThatContains(word)).map(Definition::getWord).toList();
        if (possibleDefinitions.isEmpty()) {
            List<String> availableDefinitions = StreamEx.of(definitionService.getAllWords()).map(Definition::getWord).toList();
            return buildBlockElements(AnswerHelper.getNoDefinitionsText(), availableDefinitions);
        }
        return buildBlockElements(AnswerHelper.getDefinitionsHeader(), possibleDefinitions);
    }
}
