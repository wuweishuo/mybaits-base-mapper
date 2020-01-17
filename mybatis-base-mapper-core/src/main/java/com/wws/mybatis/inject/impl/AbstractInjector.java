package com.wws.mybatis.inject.impl;

import com.wws.mybatis.inject.Injector;
import com.wws.mybatis.inject.Method;
import com.wws.mybatis.metadata.TableInfo;
import com.wws.mybatis.utils.TableInfoParser;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 14:24
 **/
public abstract class AbstractInjector implements Injector {

    private Configuration configuration;

    AbstractInjector(Configuration configuration) {
        setConfig(configuration);
    }

    @Override
    public void inject(Class mapper) {
        Class model = getModelClass(mapper);
        TableInfo tableInfo = TableInfoParser.parse(model);
        List<Method> methodList = getMethodList();
        for (Method method : methodList) {
            configuration.addMappedStatement(method.build(configuration, mapper, model, tableInfo));
        }
    }

    @Override
    public void setConfig(Configuration config) {
        this.configuration = config;
    }

    public abstract List<Method> getMethodList();

    private static Class getModelClass(Class mapper) {
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
}
