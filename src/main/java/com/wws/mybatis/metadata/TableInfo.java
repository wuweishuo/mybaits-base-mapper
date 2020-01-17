package com.wws.mybatis.metadata;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 11:36
 **/
@Data
@ToString
public class TableInfo {

    private String tableName;

    private FieldInfo id;

    private List<FieldInfo> fieldInfoList = new ArrayList<>();

    public String getAllSetSql() {
        return fieldInfoList.stream().map(FieldInfo::getSetSql).collect(Collectors.joining());
    }

    public String getAllColumns() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldInfo fieldInfo : fieldInfoList) {
            stringBuilder.append(fieldInfo.getColumn()).append(",");
        }
        stringBuilder.append(id.getColumn());

        return stringBuilder.toString();
    }

    public String getInsertColumns() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            stringBuilder.append(fieldInfo.getColumn());
            if (i != fieldInfoList.size() - 1) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

    public String getInsertProperties() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            stringBuilder.append("#{").append(fieldInfo.getColumn()).append("}");
            if (i != fieldInfoList.size() - 1) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

}
