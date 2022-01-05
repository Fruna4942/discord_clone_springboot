package com.toyproject.discord_clone.controller;

import com.toyproject.discord_clone.common.DefaultResponse;
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
    public DefaultResponse signUp(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    @RequestMapping(value = "/mail-certification", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponse mailCertification(@RequestBody UserDto userDto) {
        return mailService.mailCertification(userDto);
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody()
    public DefaultResponse signIn(@RequestBody UserDto userDto, HttpServletRequest httpServletRequest) {
        return userService.signIn(userDto, httpServletRequest);
    }
}
