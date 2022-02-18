package com.example.norberbot.app;

import com.example.norberbot.handler.MentionHandler;
import com.example.norberbot.handler.ReactionHandler;
import com.slack.api.bolt.App;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.Event;
import com.slack.api.model.event.ReactionAddedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {
    @Autowired
    private MentionHandler mentionHandler;

    @Autowired
    private ReactionHandler reactionHandler;

    @Bean
    public App initSlackApp() {
        App app = new App();

        app.command("/hello", (req, ctx) -> {
            return ctx.ack("What's up?");
        });

        app.event(AppMentionEvent.class, (payload, ctx) -> {
            AppMentionEvent incomingEvent = payload.getEvent();
            String[] mention = incomingEvent.getText().split("<+@+\\w+>+");
            if(mention.length > 1) {
                String content = mention[1].replaceAll("\\s","");
                mentionHandler.handle(incomingEvent, content, ctx.client());
            }
            return ctx.ack();
        });

        return app;
    }

}