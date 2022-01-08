package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dao.UserDao;
import com.toyproject.discord_clone.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender javaMailSender;

    public DefaultResponseDto mailCertification(UserDto userDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        // certified_key generation & update
        Random random = new Random();
        String certifiedKey = Integer.toString(random.nextInt(1000000));
        userDto.setCertified_key(certifiedKey);

        try {
            userDao.updateCertifiedKey(userDto.getCertified_key(), userDto.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }

        // mail setting & send
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("discord_clone@gmail.com");
        simpleMailMessage.setTo(userDto.getEmail());
        simpleMailMessage.setSubject("discord_clone 이메일 인증");
        simpleMailMessage.setText("인증 번호 : " + certifiedKey);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }

        return defaultResponseDto;
    }
}
