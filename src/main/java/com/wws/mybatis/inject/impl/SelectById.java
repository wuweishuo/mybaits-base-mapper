package com.wws.mybatis.inject.impl;

import com.wws.mybatis.BaseMapper;
import com.wws.mybatis.User;
import com.wws.mybatis.enums.SqlMethod;
import com.wws.mybatis.inject.Method;
import com.wws.mybatis.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 14:32
 **/
public class SelectById implements Method {

    private SqlMethod sqlMethod = SqlMethod.SelectById;

    @Override
    public MappedStatement build(Configuration configuration, Class<? extends BaseMapper> mapper, Class model, TableInfo tableInfo) {
        String sql = String.format(sqlMethod.getSql(), tableInfo.getFields(), tableInfo.getTableName(), tableInfo.getId().getColumn());
        SqlSource sqlSource = new RawSqlSource(configuration, sql, Serializable.class);

        String statementId = mapper.getName() +"." + sqlMethod.getMethod();
        MappedStatement.Builder msBuilder = new MappedStatement.Builder(configuration, statementId, sqlSource, SqlCommandType.SELECT);

        ResultMap inlineResultMap = new ResultMap.Builder(
                configuration,
                statementId + "-inline",
                model,
                new ArrayList<>(),
                null).build();
        List<ResultMap> resultMaps = Collections.singletonList(inlineResultMap);
        msBuilder.resultMaps(resultMaps);

        return msBuilder.build();
    }
}
