package com.messaging.chat.model;

public interface User {

    public void subscribe(String channel);
    public void publish (String channel, Message message);
    public void unsubscribe(String channel);
    public void readMessage(String message, String channel);
    
} 
