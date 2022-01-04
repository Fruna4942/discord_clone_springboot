package com.toyproject.discord_clone.dto;

import lombok.Data;

@Data
public class UserDto {
    int _id;
    String email;
    String password;
    String name;
    String avatar;
    boolean is_enabled;
    String certified_key;
}
