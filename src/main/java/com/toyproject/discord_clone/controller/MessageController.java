package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.ChannelDto;
import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.MessageDto;
import com.toyproject.discord_clone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/insert")
    @ResponseBody()
    public DefaultResponseDto insertMessage(@RequestBody MessageDto messageDto) {
        return messageService.insertMessage(messageDto);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public DefaultResponseDto deleteMessage(@RequestBody MessageDto messageDto) {
        return messageService.deleteMessage(messageDto);
    }

    @RequestMapping(value = "/select-by-channel")
    @ResponseBody()
    public DefaultResponseDto selectMessageByChannel(@RequestBody ChannelDto channelDto) {
        return messageService.selectMessageByChannel(channelDto);
    }
}
