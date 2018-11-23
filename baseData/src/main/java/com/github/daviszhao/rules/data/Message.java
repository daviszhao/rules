package com.github.daviszhao.rules.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;

    private int status;

}
