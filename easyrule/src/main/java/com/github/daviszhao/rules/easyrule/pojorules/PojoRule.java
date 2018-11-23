package com.github.daviszhao.rules.easyrule.pojorules;

import com.github.daviszhao.rules.data.Message;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "rule1", priority = 2)
public class PojoRule {
    @Condition
    public boolean check(@Fact("message") Message message) {
        boolean b = Message.GOODBYE == message.getStatus();
        System.out.println("rule1 evaluate:" + message + ":" + b);
        return b;
    }

    @Action
    public void run(Facts facts) {
        System.out.println("rule1 executed:" + facts);
    }
}
