package com.github.daviszhao;

import com.github.daviszhao.ruletest.Message;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;

import javax.inject.Named;

@Named
public class TestBean {
    @KSession("ksession-dtables")
    private KieSession kSession;
    @KSession("ksession-process")
    private KieSession kSessionp;
    @KSession("ksession-rules")
    private KieSession kSessionr;

    public void testDt(ApplicationContext context) {

        // go !
        com.github.daviszhao.dttest.Message dtMessage = new com.github.daviszhao.dttest.Message();
        dtMessage.setMessage("Hello World");
        dtMessage.setStatus(com.github.daviszhao.dttest.Message.HELLO);
        kSession.insert(dtMessage);
        kSession.fireAllRules();
        dtMessage = new com.github.daviszhao.dttest.Message();
        dtMessage.setMessage("Hello World");
        dtMessage.setStatus(com.github.daviszhao.dttest.Message.GOODBYE);
        kSession.insert(dtMessage);
        kSession.fireAllRules();
    }

    public void testProcess(ApplicationContext context) {
        kSessionp = context.getBean("ksession-process", KieSession.class);

        // start a new process instance
        kSessionp.startProcess("com.sample.bpmn.hello");
    }

    public void testRule(ApplicationContext context) {
        // go !
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSessionr.insert(message);
        kSessionr.fireAllRules();
    }
}
