package com.wws.mybatis.inject.impl;

import com.wws.mybatis.inject.Method;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 15:00
 **/
public class DefaultInjector extends AbstractInjector {

    private List<Method> methodList;

    public DefaultInjector(Configuration configuration){
        super(configuration);
        methodList = Stream.of(new DeleteById(),
                new SelectById()
                ).collect(Collectors.toList());
    }

    @Override
    public List<Method> getMethodList() {
        return methodList;
    }
}
