package com.example.norberbot.app;

import com.slack.api.bolt.App;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.ReactionAddedEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {

    // private static final Logger logger = LoggerFactory.getLogger(SlackApp.class);

    // private String testChannel = "idea-bot";

    @Bean
    public App initSlackApp() {
        App app = new App();

        app.command("/hello", (req, ctx) -> {
            return ctx.ack("What's up? :two_hearts:");
        });

        app.event(AppMentionEvent.class, (payload, ctx) -> {

            AppMentionEvent mm = payload.getEvent();
            if (mm.getText().contains("Hola Norber")) {
                ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                        .channel(mm.getChannel())
                        .threadTs(mm.getTs())
                        .text("<@" + mm.getUser() + "> Hola me dicen Bot,NorberBot :robot_face:"));
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
                        .text("<@" + event.getUser() + "> Thank you! We greatly appreciate your efforts :two_hearts:"));
                if (!message.isOk()) {
                    ctx.logger.error("chat.postMessage failed: {}", message.getError());
                }
            }
            return ctx.ack();
        });

        return app;
    }

}