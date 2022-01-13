package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/message/send")
    public void sendMessage(MessageDto messageDto) {
        simpMessagingTemplate.convertAndSend("/sub/channel/" + messageDto.getChannel(), messageDto);
    }
}
