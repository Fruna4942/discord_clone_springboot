package com.toyproject.discord_clone.common;

import lombok.Data;

@Data
public class DefaultResponse {
    private boolean success;
    private Object result;
    private String msg;
}