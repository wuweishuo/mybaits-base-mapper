package com.wws.mybatis;

import com.wws.mybatis.inject.Injector;
import com.wws.mybatis.inject.impl.DefaultInjector;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Collection;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-15 15:38
 **/
@Configuration
public class MybatisBaseMapperConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        MapperRegistry mapperRegistry = configuration.getMapperRegistry();
        Collection<Class<?>> mappers = mapperRegistry.getMappers();
        for (Class<?> mapper : mappers) {
            if (!BaseMapper.class.isAssignableFrom(mapper)) {
                continue;
            }

            Injector injector = new DefaultInjector(configuration);

            injector.inject(mapper);
        }
    }
}
