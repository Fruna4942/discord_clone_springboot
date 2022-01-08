package com.toyproject.discord_clone.dto;

import lombok.Data;

@Data
public class DefaultResponseDto {
    private boolean success;
    private Object result;
    private String msg;
}