package com.github.daviszhao.rules.easyrule.rules.mvelindb.models;

import lombok.Data;

import java.io.Serializable;

/**
 * conditions
 */
@Data
public class Condition implements Serializable {
    private Integer id;

    private String rulename;

    private String condition;

    private String memo;


}