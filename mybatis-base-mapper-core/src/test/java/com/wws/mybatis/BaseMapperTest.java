package com.wws.mybatis;

import com.wws.mybatis.inject.Injector;
import com.wws.mybatis.inject.impl.DefaultInjector;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-17 15:00
 **/
public class BaseMapperTest {

    UserMapper userMapper;

    @Before
    public void before(){
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        DataSourceFactory dataSourceFactory = new PooledDataSourceFactory();
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        dataSourceFactory.setProperties(properties);
        DataSource dataSource = dataSourceFactory.getDataSource();

        Environment.Builder builder = new Environment.Builder("dev");
        Environment env = builder.transactionFactory(transactionFactory).dataSource(dataSource).build();

        Configuration config = new Configuration(env);
        config.addMapper(UserMapper.class);

        Injector injector = new DefaultInjector(config);
        injector.inject(UserMapper.class);

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(config);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

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
