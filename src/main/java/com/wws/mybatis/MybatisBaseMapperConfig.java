package com.wws.mybatis;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-15 15:38
 **/
@Configuration
public class MybatisBaseMapperConfig implements ApplicationListener<ContextRefreshedEvent> {

    public void initBaseMapper(SqlSessionFactory sqlSessionFactory) {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        MapperRegistry mapperRegistry = configuration.getMapperRegistry();
        Collection<Class<?>> mappers = mapperRegistry.getMappers();
        for (Class<?> mapper : mappers) {
            if (!BaseMapper.class.isAssignableFrom(mapper)) {
                continue;
            }

            Class modelClass = getModelClass(mapper);

            SqlSource sqlSource = new RawSqlSource(configuration, "select * from User where id = #{id}", Serializable.class);
            MappedStatement.Builder msBuilder = new MappedStatement.Builder(configuration, mapper.getName() + ".findById", sqlSource, SqlCommandType.SELECT);

            ResultMap inlineResultMap = new ResultMap.Builder(
                    configuration,
                    mapper.getName() + ".selectById-Inline",
                    modelClass,
                    new ArrayList<>(),
                    null).build();
            List resultMaps = new ArrayList();
            resultMaps.add(inlineResultMap);
            msBuilder.resultMaps(resultMaps);

            configuration.addMappedStatement(msBuilder.build());
        }
    }

    private Class getModelClass(Class mapper) {
        Type[] genericInterfaces = mapper.getGenericInterfaces();
        for (Type interfaces : genericInterfaces) {
            if (interfaces instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) interfaces).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    return (Class) actualTypeArguments[0];
                }
            }
        }

        return null;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        initBaseMapper(sqlSessionFactory);
    }
}
