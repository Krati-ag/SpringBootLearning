package com.redis.pubsub.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.pubsub.model.Message;

@RestController
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String ONLINE_USERS_KEY = "online_users";

    @MessageMapping("/chat")
    public void processMessage(@Payload Message chatMessage) {
        logger.info("Received message: {}", chatMessage);
        try {
            messagingTemplate.convertAndSend("/topic/" + chatMessage.getTo(), chatMessage);
            logger.info("Message sent to /topic/{}", chatMessage.getTo());
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage(), e);
        }
    }

    @PostMapping("/online/{username}")
    public void markOnline(@PathVariable String username) {
        logger.info("User {} is online", username);
        redisTemplate.opsForSet().add(ONLINE_USERS_KEY, username);
    }

    @DeleteMapping("/online/{username}")
    public void markOffline(@PathVariable String username) {
        logger.info("User {} is offline", username);
        redisTemplate.opsForSet().remove(ONLINE_USERS_KEY, username);
    }

    @GetMapping("/online")
    public Set<String> getOnlineUsers() {
        Set<String> users = redisTemplate.opsForSet().members(ONLINE_USERS_KEY);
        logger.info("Online users: {}", users);
        return users;
    }
}
