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

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

@Component
public class MentionHandler extends SlackHandler implements EventHandler {

    @Autowired
    private DefinitionService definitionService;

    @Override
    public void handle(Event event, String eventContent, MethodsClient client) throws SlackApiException, IOException {
        String[] content = eventContent.split("<+@+\\w+>+");
        List<LayoutBlock> layoutBlockAnswer = new ArrayList<>();
        if (!ArrayUtils.isEmpty(content)) {
            String mentionContent = content[0].trim();
            if (Pattern.matches("\\p{L}+(\\s\\p{L}+)*:", mentionContent)) {
                String word = Splitter.on(":").split(mentionContent).iterator().next();
                layoutBlockAnswer = List.copyOf(handleDefinition(word));
                replyToAnEventWithBlockMessage(event, client, layoutBlockAnswer.toArray(LayoutBlock[]::new));
            } else if (eventContent.equals("opciones")) {
                List<String> availableDefinitions = StreamEx.of(definitionService.getAllWords()).map(Definition::getWord).toList();
                replyToAnEventWithMessage(event, client, handleMultipleOptions(availableDefinitions, AnswerHelper.getOptionsHeaderText()));
            } else {
                replyToAnEventWithMessage(event, client, AnswerHelper.getHelpText());
            }
        }
    }

    private List<LayoutBlock> handleDefinition(String word) {
        List<Definition> possibleDefinitions =definitionService.findWordsThatContains(word);
        List<LayoutBlock> blockElements = new ArrayList<>();
        if (!possibleDefinitions.isEmpty()) {
            if (possibleDefinitions.size() > 5) {
                String header = String.format(AnswerHelper.getMultipleOptionsHeader(), word);
                List<String> multipleDefinitions = StreamEx.of(possibleDefinitions).map(Definition::getWord).toList();
                blockElements.add(section(section -> section.text(markdownText(handleMultipleOptions(multipleDefinitions, header)))));
                blockElements.add(section(section -> section.text(markdownText(AnswerHelper.getMultipleOptionsFooter()))));
                return blockElements;
            }
            return buildBlockElements(AnswerHelper.getDefinitionsHeader(), possibleDefinitions);
        }
        blockElements.add(section(section -> section.text(markdownText(AnswerHelper.getNoDefinitionsText()))));
        return blockElements;

    }

    private String handleMultipleOptions(List<String> availableDefinitions, String header) {
        StringBuilder answer = new StringBuilder();
        answer.append(header);
        for (var definition : availableDefinitions) {
            answer.append(":small_orange_diamond: ").append(definition).append("\n");
        }
        return answer.toString();
    }
}
