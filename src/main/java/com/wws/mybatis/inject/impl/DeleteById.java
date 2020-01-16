package com.wws.mybatis.inject.impl;

import com.wws.mybatis.BaseMapper;
import com.wws.mybatis.enums.SqlMethod;
import com.wws.mybatis.inject.Method;
import com.wws.mybatis.metadata.TableInfo;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 14:32
 **/
public class DeleteById implements Method {

    private SqlMethod sqlMethod = SqlMethod.DeleteById;

    @Override
    public MappedStatement build(Configuration configuration, Class<? extends BaseMapper> mapper, Class model, TableInfo tableInfo) {
        String statementId = mapper.getName() +"." + sqlMethod.getMethod();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getId().getColumn());
        SqlSource sqlSource = new RawSqlSource(configuration, sql, Serializable.class);

        MappedStatement.Builder msBuilder = new MappedStatement.Builder(configuration, statementId, sqlSource, SqlCommandType.DELETE);

        ResultMap inlineResultMap = new ResultMap.Builder(
                configuration,
                statementId + "-inline",
                Integer.class,
                new ArrayList<>(),
                null).build();
        List resultMaps = new ArrayList();
        resultMaps.add(inlineResultMap);
        msBuilder.resultMaps(resultMaps);

        return msBuilder.build();
    }
}
