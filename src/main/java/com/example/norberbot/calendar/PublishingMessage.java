package com.example.norberbot.calendar;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PublishingMessage {

    /**
     * Post a message to a channel your app is in using ID and message text
     */
    static void publishMessage(String id, String text) {
        // you can get this instance via ctx.client() in a Bolt app
        var client = Slack.getInstance().methods();
        var logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            // Call the chat.postMessage method using the built-in WebClient
            var result = client.chatPostMessage(r -> r
                    // The token you used to initialize your app
                    .token("xoxb-3069065446835-3060123218567-oha4XT5xAejvLASQ3lKrl87l")
                    .channel(id)
                    .text(text)
            // You could also use a blocks[] array to send richer content
            );
            // Print result, which includes information about the message (like TS)
            logger.info("result {}", result);
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }
}