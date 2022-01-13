package com.toyproject.discord_clone.dao;

import com.toyproject.discord_clone.dto.ChannelDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ChannelDao {
    void createChannel(String name, int type, int server) throws DataAccessException;
    void updateChannelName(String name, int _id) throws DataAccessException;
    void deleteChannel(int _id) throws DataAccessException;
    ChannelDto selectChannel(int _id) throws DataAccessException;
}
