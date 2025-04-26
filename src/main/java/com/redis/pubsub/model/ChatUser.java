package com.messaging.chat.model;

public class ChatUser implements User{
    int id;
    String name;
    public ChatUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void subscribe(String channel){

    }

    @Override
    public void publish (String channel, Message message){

    }

    @Override
    public void unsubscribe(String channel){

    }

    @Override
    public void readMessage(String message, String channel){
        
    }

} 