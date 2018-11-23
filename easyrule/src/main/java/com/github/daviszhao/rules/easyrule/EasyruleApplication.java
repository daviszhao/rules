package com.github.daviszhao.rules.easyrule;

import com.github.daviszhao.rules.data.Message;
import com.github.daviszhao.rules.easyrule.pojorules.MyBasicRule;
import com.github.daviszhao.rules.easyrule.pojorules.PojoRule;
import com.github.daviszhao.rules.easyrule.rules.mvelindb.dao.RuledefineDao;
import com.github.daviszhao.rules.easyrule.rules.mvelindb.models.Action;
import com.github.daviszhao.rules.easyrule.rules.mvelindb.models.Condition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.StringReader;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EasyruleApplication {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EasyruleApplication.class, args);
//基于基类的规则
        Rule rule = new MyBasicRule();
        //基于注解的pojo规则
        PojoRule pojoRule = new PojoRule();
        //基本builder构建的规则
        Rule byBuilder = new RuleBuilder().
                name("bybuilder").priority(3).when(facts -> {
            Message message = facts.get("message");
            boolean b = message != null && message.getStatus() == 1;
            System.out.println(" bybuilder evaluate :" + facts + ":" + b);
            return b;
        }).then(facts -> {
            System.out.println("bybuilder execute:" + facts);
            TimeUnit.MILLISECONDS.sleep(2);
        }).build();
        //mvel规则
        MVELRule mvel = new MVELRule().name("mvel").priority(4)
                .when("message.getStatus()==1")
                .then("System.out.println(\"mvel executed \");");
        //整体装载的evel规则
        Rules loadedRules = MVELRuleFactory.createRulesFrom(new StringReader("name: loaded rule\n" +
                "prority: 5\n" +
                "condition: \"message.getStatus()==1\"\n" +
                "actions:\n" +
                "  - \"System.out.println(\\\"loaded rule executed\\\");\""));
        Rules rules = new Rules(/*pojoRule, rule, mvel, byBuilder*/);

//        loadedRules.forEach(rules::register);

        loadRulesFromDB(context, rules);

        Facts facts = new Facts();

        facts.put("message", new Message("test", 1));
        facts.put("date", new Date());
        System.out.println("default engine");
        new DefaultRulesEngine().fire(rules, facts);

    }

    private static void loadRulesFromDB(ApplicationContext context, Rules rules) {
        RuledefineDao dao = context.getBean(RuledefineDao.class);
        dao.loadall().stream().map(r -> {
            MVELRule rule = new MVELRule().name(r.getRulename()).priority(r.getPrority()).description(r.getMemo());
            r.getConditions().stream().map(Condition::getCondition).forEach(rule::when);
            r.getActions().stream().map(Action::getAction).forEach(rule::then);
            return rule;
        }).forEach(rules::register);

    }

}
