package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.dao.ServerDao;
import com.toyproject.discord_clone.dao.UserDao;
import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.ServerDto;
import com.toyproject.discord_clone.dto.UserDto;
import com.toyproject.discord_clone.module.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ServerService {
    @Autowired
    public ServerDao serverDao;

    @Autowired
    public HttpSession httpSession;

    public DefaultResponseDto createServer(HttpSession httpSession, ServerDto serverDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("서버 생성이 완료되었습니다.");

        //Authenticate check
        UserDto userDto;
        userDto = (UserDto)httpSession.getAttribute("user");

        if (userDto == null) {
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("인증 정보가 없습니다.");
            return defaultResponseDto;
        }


        // insert server
        try {
            serverDao.insertServer(serverDto.getName(),serverDto.getAvatar(), userDto.get_id());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto updateServer(HttpSession httpSession, ServerDto serverDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("서버 변경이 완료되었습니다.");

        //Authenticate check
        UserDto userDto;
        userDto = (UserDto)httpSession.getAttribute("user");

        if (userDto == null) {
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("인증 정보가 없습니다.");
            return defaultResponseDto;
        }

        ServerDto selectedServerDto = serverDao.selectServer(serverDto.get_id());

        if(userDto.get_id() != selectedServerDto.getHost()){
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 변경은 호스트만 가능합니다.");
            return defaultResponseDto;
        }

        //update server
        try {
            serverDao.updateServer(serverDto.get_id(), serverDto.getName(),serverDto.getAvatar());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto deleteServer(HttpSession httpSession, ServerDto serverDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("서버 삭제가 완료되었습니다.");

        //Authenticate check
        UserDto userDto;
        userDto = (UserDto)httpSession.getAttribute("user");

        if (userDto == null) {
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("인증 정보가 없습니다.");
            return defaultResponseDto;
        }
        ServerDto selectedServerDto = serverDao.selectServer(serverDto.get_id());
        if(userDto.get_id() != selectedServerDto.getHost()){
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 삭제는 호스트만 가능합니다.");
            return defaultResponseDto;
        }

        //update server
        try {
            serverDao.deleteServer(serverDto.get_id());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }
        return defaultResponseDto;
    }
}
