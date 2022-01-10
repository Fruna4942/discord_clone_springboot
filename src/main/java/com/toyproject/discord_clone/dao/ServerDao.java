package com.toyproject.discord_clone.dao;

import com.toyproject.discord_clone.dto.ServerDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ServerDao {
    ServerDto selectServer(int _id) throws DataAccessException;
    void insertServer(String name, String avatar, int host) throws DataAccessException;
    void updateServer(int _id, String name, String avatar) throws DataAccessException;
    void deleteServer(int _id) throws DataAccessException;
}
