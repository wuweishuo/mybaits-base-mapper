package com.wws.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MybatisBaseMapperExampleApplication.class)
@RunWith(SpringRunner.class)
public class MybatisBaseMapperExampleApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectById() {
        System.out.println(userMapper.selectById(18));
    }

    @Test
    public void testDeleteById() {
        System.out.println(userMapper.deleteById(19));
    }

    @Test
    public void testInset() {
        User user = User.builder().username("aa").password("aa").build();
        System.out.println(userMapper.insert(user));
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        User user = User.builder().id(18L).username("bb").password("aa").build();
        System.out.println(userMapper.updateById(user));
    }


}
