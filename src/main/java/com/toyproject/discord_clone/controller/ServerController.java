package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.ServerDto;
import com.toyproject.discord_clone.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/server")
public class ServerController {
    @Autowired
    ServerService serverService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto create(HttpServletRequest httpServletRequest, @RequestBody ServerDto serverDto) {
        return serverService.createServer(httpServletRequest.getSession(), serverDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto update(HttpServletRequest httpServletRequest, @RequestBody ServerDto serverDto) {
        return serverService.updateServer(httpServletRequest.getSession(), serverDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto delete(HttpServletRequest httpServletRequest, @RequestBody ServerDto serverDto) {
        return serverService.deleteServer(httpServletRequest.getSession(), serverDto);
    }
}
