package com.toyproject.discord_clone.dto;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChannelDto {
    private int _id;
    private String name;
    private int type;
    private int server;
    private Set<WebSocketSession> webSocketSessionSet = new HashSet<>();
}
