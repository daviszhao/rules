package com.github.daviszhao.rules.easyrule.rules.mvelindb.models;

import lombok.Data;

import java.io.Serializable;

/**
 * actions
 */
@Data
public class Action implements Serializable {
    private Integer id;

    private String rulename;

    private String action;

    private String memo;


}