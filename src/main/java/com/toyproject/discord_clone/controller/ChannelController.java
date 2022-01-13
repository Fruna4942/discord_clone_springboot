package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.ChannelDto;
import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/channel")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto createChannel(@RequestBody ChannelDto channelDto) {
        return channelService.createChannel(channelDto);
    }

    @RequestMapping(value = "/change-name", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto updateChannelName(@RequestBody ChannelDto channelDto) {
        return channelService.updateChannelName(channelDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto deleteChannel(@RequestBody ChannelDto channelDto) {
        return channelService.deleteChannel(channelDto);
    }
}
