package com.wws.mybatis.metadata;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 11:37
 **/
public class FieldInfo {

    private String property;

    private String column;

    private static final String setSql = "<if test=\"%s != null\">%s = #{%s},</if>";

    public String getSetSql() {
        return String.format(setSql, property, column, property);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
