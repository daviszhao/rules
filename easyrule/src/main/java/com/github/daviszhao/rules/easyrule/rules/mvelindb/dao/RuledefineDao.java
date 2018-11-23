package com.github.daviszhao.rules.easyrule.rules.mvelindb.dao;

import com.github.daviszhao.rules.easyrule.rules.mvelindb.models.RuleDefine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RuledefineDao {


    int deleteByPrimaryKey(String rulename);

    int insert(RuleDefine record);

    int insertSelective(RuleDefine record);


    RuleDefine selectByPrimaryKey(String rulename);


    int updateByPrimaryKeySelective(RuleDefine record);

    int updateByPrimaryKey(RuleDefine record);

    List<RuleDefine> loadall();
}