package com.example.norberbot.app;

import com.example.norberbot.handler.BlockActionHandler;
import com.example.norberbot.handler.MentionHandler;
import com.slack.api.bolt.App;
import com.slack.api.model.Message;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class SlackApp {
    @Autowired
    private MentionHandler mentionHandler;

    @Autowired
    private BlockActionHandler blockActionHandler;

    @Bean
    public App initSlackApp() {
        App app = new App();

        app.command("/hello", (req, ctx) -> {
            return ctx.ack("What's up?");
        });

        app.event(AppMentionEvent.class, (payload, ctx) -> {
            AppMentionEvent incomingEvent = payload.getEvent();
            String[] mention = incomingEvent.getText().split("<+@+\\w+>+");
            if (mention.length > 1) {
                String content = mention[1];
                mentionHandler.handle(incomingEvent, content, ctx.client());
            }
            return ctx.ack();
        });

        app.blockAction(Pattern.compile("[\\w\\s]+"), (req, ctx) -> {
            String buttonsValue = req.getPayload().getActions().get(0).getValue();
            if (req.getPayload().getResponseUrl() != null) {
                blockActionHandler.handle(ctx, buttonsValue, ctx.client());
            }
            return ctx.ack();
        });

        return app;
    }

}