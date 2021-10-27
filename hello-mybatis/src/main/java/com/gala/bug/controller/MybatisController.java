package com.gala.bug.controller;

import com.gala.bug.dao.AccountMapper;
import com.gala.bug.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MybatisController {

    @Autowired
    AccountMapper accountMapper;

    @ResponseBody
    @RequestMapping(value="query")
    public Map<String,Object> queryById(@RequestParam String id){

//        //1.实例化SqlSessionFactory，加载数据库配置文件以及mapper.xml文件到configuration对象
//        SqlSessionFactory factory = new SqlSessionFactory();
//        //2.获取sqlsession对象
//        SqlSession session = factory.openSession();
//        System.out.println(session);
//
//        //3.通过动态代理跨越面向接口编程和ibatis编程模型的鸿沟
//        AccountMapper accountMapper = session.getMapper(AccountMapper.class);

        //4.遵循jdbc规范，通过底层的四大对象的合作完成数据查询和数据转化
        Account account = accountMapper.selectByPrimaryKey(1);
        System.out.println(account);

        System.out.println("----------------------------------");

        List<Account> selectAll = accountMapper.selectAll();
        if(selectAll !=null && selectAll.size()>0){
            for (Account acc : selectAll) {
                System.out.println(acc);
            }
        }

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put(id,account);
        returnMap.put("allList",selectAll);
        return returnMap;
    }
}
