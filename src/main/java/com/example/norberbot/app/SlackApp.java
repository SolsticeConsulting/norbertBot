package com.example.norberbot.app;

import com.slack.api.bolt.App;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.ReactionAddedEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {

    @Bean
    public App initSlackApp() {
        App app = new App();

        app.command("/hello", (req, ctx) -> {
            return ctx.ack("What's up?");
        });

        app.event(AppMentionEvent.class, (payload, ctx) -> {

            AppMentionEvent incomingEvent = payload.getEvent();
            if (incomingEvent.getText().contains("Hola Norber")) {
                ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                        .channel(incomingEvent.getChannel())
                        .threadTs(incomingEvent.getTs())
                        .text("<@" + incomingEvent.getUser() + "> Hola me dicen Bot,NorberBot :robot_face:"));
                if (!message.isOk()) {
                    ctx.logger.error("chat.postMessage failed: {}", message.getError());
                }
            }
            return ctx.ack();
        });

        app.event(ReactionAddedEvent.class, (payload, ctx) -> {
            ReactionAddedEvent event = payload.getEvent();
            if (event.getReaction().equals("white_check_mark")) {
                ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                        .channel(event.getItem().getChannel())
                        .threadTs(event.getItem().getTs())
                        .text("<@" + event.getUser() + "> Que nadie se atreva, a tocar a mi vieja"));
                if (!message.isOk()) {
                    ctx.logger.error("chat.postMessage failed: {}", message.getError());
                }
            }
            return ctx.ack();
        });

        return app;
    }

}