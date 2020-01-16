package com.wws.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MybatisBaseCrudApplication.class)
@RunWith(SpringRunner.class)
public class MybatisBaseCrudApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(userMapper.selectById(1));
    }

}
