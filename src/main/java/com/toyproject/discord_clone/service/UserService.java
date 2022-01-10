package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dao.UserDao;
import com.toyproject.discord_clone.dto.UserDto;
import com.toyproject.discord_clone.module.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    public UserDao userDao;

    @Autowired
    public HttpSession httpSession;

    public DefaultResponseDto signUp(UserDto userDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        // pw encryption
        String encryptedPassword = Encrypt.SHA256Util(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        System.out.println();

        // insert user
        try {
            userDao.insertUser(userDto.getEmail(), userDto.getPassword(), userDto.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto logIn(UserDto userDto) throws DataAccessException {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        // pw encryption
        String encryptedPassword = Encrypt.SHA256Util(userDto.getPassword());
        userDto.setPassword(encryptedPassword);

        // match with DB
        UserDto loginUserDto = new UserDto();
        try {
            loginUserDto = userDao.logIn(userDto.getEmail(), userDto.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }
        if (loginUserDto == null) defaultResponseDto.setSuccess(false);
        else httpSession.setAttribute("user", loginUserDto);

        return defaultResponseDto;
    }

    public DefaultResponseDto auth(HttpSession httpSession) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        UserDto loginUserDto;
        loginUserDto = (UserDto)httpSession.getAttribute("user");

        if (loginUserDto == null) defaultResponseDto.setSuccess(false);
        else defaultResponseDto.setResult(loginUserDto);

        return defaultResponseDto;
    }

    public DefaultResponseDto logOut(HttpSession httpSession) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        try {
            httpSession.removeAttribute("user");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto deleteUser(UserDto userDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);

        try {
            userDao.deleteUser(userDto.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
        }

        return defaultResponseDto;
    }
}
