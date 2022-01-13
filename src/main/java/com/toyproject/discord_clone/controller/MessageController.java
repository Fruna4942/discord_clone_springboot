package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

@RestController
//@MessageMapping(value = "/message")
public class MessageController {
    @MessageMapping(value = "/send")
    @SendTo(value = "/topic/_id")
    public void sendMessage(TextMessage messageDto) {
        System.out.println("eeeee");
        //System.out.println(messageDto);
        //return messageDto;
    }
}
