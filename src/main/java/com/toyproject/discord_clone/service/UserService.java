package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.common.DefaultResponse;
import com.toyproject.discord_clone.dao.UserDao;
import com.toyproject.discord_clone.dto.UserDto;
import com.toyproject.discord_clone.module.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserDao userDao;

    public DefaultResponse signUp(UserDto userDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setSuccess(true);

        // pw encryption
        String encryptedPassword = Encrypt.SHA256Util(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        System.out.println();

        // insert user
        try {
            userDao.insertUser(userDto.getEmail(), userDto.getPassword(), userDto.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponse.setSuccess(false);
        }

        return defaultResponse;
    }

    public DefaultResponse signIn(UserDto userDto) throws DataAccessException {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setSuccess(true);

        // pw encryption
        String encryptedPassword = Encrypt.SHA256Util(userDto.getPassword());
        userDto.setPassword(encryptedPassword);

        // match with DB
        UserDto loginUserDto = new UserDto();
        try {
            loginUserDto = userDao.signIn(userDto.getEmail(), userDto.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponse.setSuccess(false);
        }
        if (loginUserDto == null) defaultResponse.setSuccess(false);

        return defaultResponse;
    }

    public DefaultResponse deleteUser(UserDto userDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setSuccess(true);

        try {
            userDao.deleteUser(userDto.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponse.setSuccess(false);
        }

        return defaultResponse;
    }
}
