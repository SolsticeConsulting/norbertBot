package com.example.norberbot.app;

import com.example.norberbot.handler.BlockActionHandler;
import com.example.norberbot.handler.MentionHandler;
import com.slack.api.bolt.App;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.AppMentionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
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

        app.message("hello", (req, ctx) -> {
            var logger = ctx.logger;
            try {
                var event = req.getEvent();
                // Call the chat.postMessage method using the built-in WebClient
                var result = ctx.client().chatPostMessage(r -> r
                        // The token you used to initialize your app is stored in the `context` object
                        .token(ctx.getBotToken())
                        // Payload message should be posted in the channel where original message was heard
                        .channel(event.getChannel())
                        .text("world")
                );
                logger.info("result: {}", result);
            } catch (IOException | SlackApiException e) {
                logger.error("error: {}", e.getMessage(), e);
            }
            return ctx.ack();
        });

        app.event(AppMentionEvent.class, (payload, ctx) -> {
            AppMentionEvent incomingEvent = payload.getEvent();
            ctx.client().chatPostEphemeral(r -> r
                    .token(System.getenv("SLACK_BOT_TOKEN"))
                    .channel(incomingEvent.getChannel())
                    .text("hOLAAASDFASD")
            );
            return ctx.ack();
        });

        app.blockAction(Pattern.compile("[\\w\\s]+"),(req, ctx) -> {
            String buttonsValue = req.getPayload().getActions().get(0).getValue();
            if (req.getPayload().getResponseUrl() != null) {
                blockActionHandler.handle(ctx, buttonsValue, ctx.client());
            }
            return ctx.ack();
        });

        return app;
    }

}