package com.toyproject.discord_clone.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDto {
    private int _id;
    private String content;
    private int from_user;
    private int channel;
    private Timestamp created_at;
}
