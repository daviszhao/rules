package com.github.daviszhao.rules.easyrule.pojorules;

import com.github.daviszhao.rules.data.Message;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.BasicRule;

import java.util.concurrent.TimeUnit;

public class MyBasicRule extends BasicRule {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getName() {
        return "base rule";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Message message = facts.get("message");
        boolean b = message != null && message.getStatus() == 1;
        System.out.println("baserule evaluate :" + facts + ":" + b);
        return b;
    }

    @Override
    public void execute(Facts facts) throws Exception {
        System.out.println("baserule execute:" + facts);
        TimeUnit.MILLISECONDS.sleep(2);
    }
}
