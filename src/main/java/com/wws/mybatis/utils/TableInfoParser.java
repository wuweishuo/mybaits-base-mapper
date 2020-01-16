package com.wws.mybatis.utils;

import com.wws.mybatis.annotation.Column;
import com.wws.mybatis.annotation.Id;
import com.wws.mybatis.annotation.Table;
import com.wws.mybatis.metadata.FieldInfo;
import com.wws.mybatis.metadata.TableInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 11:30
 **/
public class TableInfoParser {

    public static TableInfo parse(Class model){
        TableInfo tableInfo = new TableInfo();
        Table annotation = (Table) model.getAnnotation(Table.class);
        if(annotation == null){
            return null;
        }
        String tableName = annotation.value();
        tableInfo.setTableName(tableName);
        Field[] fields = model.getDeclaredFields();
        List<FieldInfo> fieldList = new ArrayList<>(fields.length);
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if(id != null) {
                FieldInfo idInfo = new FieldInfo();
                idInfo.setColumn(id.value());
                idInfo.setProperty(field.getName());

                tableInfo.setId(idInfo);
            }

            Column column = field.getAnnotation(Column.class);
            if(column != null){
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setColumn(column.value());
                fieldInfo.setProperty(field.getName());

                fieldList.add(fieldInfo);
            }
        }
        tableInfo.setFieldInfoList(fieldList);

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldInfo fieldInfo : fieldList) {
            stringBuilder.append(fieldInfo.getColumn()).append(",");
        }
        stringBuilder.append(tableInfo.getId().getColumn());

        tableInfo.setFields(stringBuilder.toString());

        return tableInfo;
    }

}
