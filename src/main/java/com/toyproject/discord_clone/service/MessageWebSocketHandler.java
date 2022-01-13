package com.toyproject.discord_clone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
public class MessageWebSocketHandler extends TextWebSocketHandler{
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws IOException {
        System.out.println(textMessage.getPayload());
        TextMessage textMessageToClient = new TextMessage("Hello client");
        webSocketSession.sendMessage(textMessageToClient);
    }
}
