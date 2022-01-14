package com.toyproject.discord_clone.dao;

import com.toyproject.discord_clone.dto.MessageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
@Mapper
public interface MessageDao {
    void insertMessage(int from_user, int channel, String content) throws DataAccessException;
    void deleteMessage(int _id) throws DataAccessException;
    LinkedList<MessageDto> selectMessagesByChannel(int channel) throws DataAccessException;
}
