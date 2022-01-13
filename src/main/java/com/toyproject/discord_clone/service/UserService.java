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
        defaultResponseDto.setMsg("회원가입이 완료되었습니다.");

        // pw encryption
        String encryptedPassword = Encrypt.SHA256Util(userDto.getPassword());
        userDto.setPassword(encryptedPassword);

        // insert user
        try {
            userDao.insertUser(userDto.getEmail(), userDto.getPassword(), userDto.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto logIn(UserDto userDto) throws DataAccessException {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("로그인 되었습니다.");

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
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }
        if (loginUserDto == null) {
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("유효하지 않은 아이디 또는 비밀번호입니다.");
        }
        else httpSession.setAttribute("user", loginUserDto);

        return defaultResponseDto;
    }

    public DefaultResponseDto auth(HttpSession httpSession) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("유효한 세션입니다.");

        UserDto loginUserDto;
        loginUserDto = (UserDto)httpSession.getAttribute("user");

        if (loginUserDto == null) {
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("유효하지 않은 세션입니다.");
        }
        else defaultResponseDto.setResult(loginUserDto);

        return defaultResponseDto;
    }

    public DefaultResponseDto logOut(HttpSession httpSession) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("로그아웃 되었습니다.");

        try {
            httpSession.removeAttribute("user");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("유효하지 않은 세션입니다.");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto deleteUser(UserDto userDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("회원탈퇴가 완료되었습니다.");

        try {
            userDao.deleteUser(userDto.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }
}
