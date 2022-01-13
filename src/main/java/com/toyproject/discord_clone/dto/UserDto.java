package com.toyproject.discord_clone.dto;

import lombok.Data;

@Data
public class UserDto {
    private int _id;
    private String email;
    private String password;
    private String name;
    private String avatar;
    private boolean is_enabled;
    private String certified_key;
}
