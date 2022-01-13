package com.toyproject.discord_clone.service;

import com.toyproject.discord_clone.dao.ChannelDao;
import com.toyproject.discord_clone.dto.ChannelDto;
import com.toyproject.discord_clone.dto.DefaultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

//TODO : 채널 관련 권한에 관한 내용 필요
@Service
public class ChannelService {
    @Autowired
    ChannelDao channelDao;

    public DefaultResponseDto createChannel(ChannelDto channelDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("채널 생성이 완료되었습니다.");

        try {
            channelDao.createChannel(channelDto.getName(), channelDto.getType(), channelDto.getServer());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto updateChannelName(ChannelDto channelDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("채널이름 변경이 완료되었습니다.");

        try {
            channelDao.updateChannelName(channelDto.getName(), channelDto.get_id());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg(("서버 내부 오류(DB)"));
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto deleteChannel(ChannelDto channelDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("채널 삭제가 완료되었습니다.");

        try {
            channelDao.deleteChannel(channelDto.get_id());
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }

    public DefaultResponseDto selectChannel(ChannelDto channelDto) {
        DefaultResponseDto defaultResponseDto = new DefaultResponseDto();
        defaultResponseDto.setSuccess(true);
        defaultResponseDto.setMsg("채널 조회가 완료되었습니다.");

        try {
            defaultResponseDto.setResult(channelDao.selectChannel(channelDto.get_id()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            defaultResponseDto.setSuccess(false);
            defaultResponseDto.setMsg("서버 내부 오류(DB)");
        }

        return defaultResponseDto;
    }
}
