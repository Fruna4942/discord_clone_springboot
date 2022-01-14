package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.dao.MessageDao;
import com.toyproject.discord_clone.dto.ChannelDto;
import com.toyproject.discord_clone.dto.DefaultResponseDto;
import com.toyproject.discord_clone.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    public DefaultResponseDto insertMessage(MessageDto messageDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("메시지 생성이 완료되었습니다.");

        try {
            messageDao.insertMessage(messageDto.getFrom_user(), messageDto.getChannel(), messageDto.getContent());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto deleteMessage(MessageDto messageDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("메시지 삭제가 완료되었습니다.");

        try {
            messageDao.deleteMessage(messageDto.get_id());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto selectMessageByChannel(ChannelDto channelDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("메시지 조회가 완료되었습니다.");

        try {
            defaultResponseDto.setResult(messageDao.selectMessagesByChannel(channelDto.get_id()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }
}
