package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.UserDto;
import com.toyproject.discord_clone.service.MailService;
import com.toyproject.discord_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto signUp(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    @RequestMapping(value = "/mail-certification", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto mailCertification(@RequestBody UserDto userDto) {
        return mailService.mailCertification(userDto);
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponseDto logIn(@RequestBody UserDto userDto) {
        return userService.logIn(userDto);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResponseBody()
    public DefaultResponseDto auth(HttpServletRequest httpServletRequest) {
        return userService.auth(httpServletRequest.getSession());
    }

    @RequestMapping(value = "/log-out", method = RequestMethod.GET)
    @ResponseBody()
    public DefaultResponseDto logOut(HttpServletRequest httpServletRequest) {
        return userService.logOut(httpServletRequest.getSession());
    }
}
