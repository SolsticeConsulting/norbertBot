package com.example.norberbot.handler;

import com.example.norberbot.helper.AnswerHelper;
import com.example.norberbot.helper.EmojiHelper;
import com.example.norberbot.model.Definition;
import com.example.norberbot.service.WordService;
import com.google.common.base.Splitter;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.Event;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

@Component
public class MentionHandler implements EventHandler {

    @Autowired
    private WordService wordService;

    @Override
    public void handle(Event event, String eventContent, MethodsClient client) {
        String[] content = eventContent.split("<+@+\\w+>+");
        String answer = "";
        if (!ArrayUtils.isEmpty(content)) {
            String mentionContent = content[0].replaceAll("\\s", "");
            if (Pattern.matches("\\w+(:+)", mentionContent)) {
                String word = Splitter.on(":").split(mentionContent).iterator().next();
                answer = handleDefinition(word);
            } else if (mentionContent.equals("opciones")) {
                answer = handleOptions();
            } else {
                answer = AnswerHelper.getHelpText();
            }
        }
        try {
            replyToAnEvent(event, client, answer);
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }

    private String handleDefinition(String word) {
        List<String> possibleDefinitions = StreamEx.of(wordService.findWordsThatContains(word)).map(Definition::getWord).toList();
        StringJoiner answer = new StringJoiner("");
        if (possibleDefinitions.isEmpty()) {
            answer.add(AnswerHelper.getNoDefinitionsText());
            answer.add("_" + word + "_");
            answer.add(AnswerHelper.getOptionsHeaderText());
            answer.add(handleOptions());
        } else {
            int counter = 1;
            for (String possibleDefinition : possibleDefinitions) {
                answer.add(EmojiHelper.getEmojiNumber(counter)).add(possibleDefinition).add("\n\n\n");
                counter++;
            }
        }
        return answer.toString();
    }

    private String handleOptions() {
        StringJoiner answer = new StringJoiner("");
        answer.add(AnswerHelper.getOptionsHeaderText());
        List<String> options = StreamEx.of(wordService.getAllWords()).map(Definition::getWord).toList();
        for (String option : options) {
            answer.add("*").add(option).add("\n\n");
        }
        return answer.toString();
    }

    @Override
    public void replyToAnEvent(Event event, MethodsClient client, String answer) throws SlackApiException, IOException {
        AppMentionEvent appMentionEvent = (AppMentionEvent) event;
        client.chatPostMessage(r -> r
                .channel(appMentionEvent.getChannel())
                .threadTs(appMentionEvent.getTs())
                .text(answer));
    }
}
