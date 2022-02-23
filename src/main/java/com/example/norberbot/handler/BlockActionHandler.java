package com.example.norberbot.handler;

import com.example.norberbot.helper.AnswerHelper;
import com.example.norberbot.model.Definition;
import com.example.norberbot.service.WordService;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

@Component
public class BlockActionHandler extends SlackHandler implements ActionHandler {
    @Autowired
    private WordService wordService;

    public void handle(ActionContext actionContext, String content, MethodsClient client) {
        Definition definition = wordService.findWord(content);
        try {
            actionContext.respond(buildBlockElements(definition));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<LayoutBlock> buildBlockElements(Definition definition) {
        List<LayoutBlock> blockElements = new ArrayList<>();
        blockElements.add(section(section -> section.text(markdownText(AnswerHelper.getDefinitionHeader() + definition.getDefinition()))));
        blockElements.add(divider());
        blockElements.add(section(section -> section.text(markdownText(AnswerHelper.getResourceHeader() + definition.getLink()))));
        return blockElements;
    }
}
