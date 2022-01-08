package com.toyproject.discord_clone.dao;

import com.toyproject.discord_clone.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    void insertUser(String email, String password, String name) throws DataAccessException;
    void updateCertifiedKey(String certified_key, String email) throws DataAccessException;
    void deleteUser(String email) throws DataAccessException;
    UserDto logIn(String email, String password) throws DataAccessException;
}
