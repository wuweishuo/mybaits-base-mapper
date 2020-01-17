package com.wws.mybatis.metadata;

import lombok.Data;
import lombok.ToString;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 11:37
 **/
@Data
@ToString
public class FieldInfo {

    private String property;

    private String column;

    private static final String setSql = "<if test=\"%s != null\">%s = #{%s},</if>";

    public String getSetSql() {
        return String.format(setSql, property, column, property);
    }

}
