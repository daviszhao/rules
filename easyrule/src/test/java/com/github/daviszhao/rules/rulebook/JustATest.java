package com.github.daviszhao.rules.rulebook;

import com.github.daviszhao.rules.easyrule.EasyruleApplication;
import com.github.daviszhao.rules.easyrule.rules.mvelindb.dao.RuledefineDao;
import com.github.daviszhao.rules.easyrule.rules.mvelindb.models.RuleDefine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = EasyruleApplication.class)
@RunWith(SpringRunner.class)
public class JustATest {
    @Autowired
    private RuledefineDao dao;

    @Test
    public void test() {
        List<RuleDefine> loadall = dao.loadall();
        System.out.println(loadall);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        List<Object> list = sqlSession.selectList("com.github.daviszhao.rules.easyrule.rules.mvelindb.dao.RuledefineDao.loadall");
//        System.out.println(list);
    }
}
