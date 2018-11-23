package com.github.daviszhao.rules.easyrule.rules.mvelindb.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class RuleDefine {
    private String rulename, memo;
    private int prority = Integer.MAX_VALUE - 1;
    private List<Condition> conditions;
    private List<Action> actions;
}
