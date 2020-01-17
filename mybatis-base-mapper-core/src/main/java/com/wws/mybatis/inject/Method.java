package com.wws.mybatis.inject;

import com.wws.mybatis.BaseMapper;
import com.wws.mybatis.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 14:30
 **/
public interface Method {

    MappedStatement build(Configuration configuration, Class<? extends BaseMapper> mapper, Class model, TableInfo tableInfo);

}
