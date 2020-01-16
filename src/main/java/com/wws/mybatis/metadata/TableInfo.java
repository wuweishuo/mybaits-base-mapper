package com.wws.mybatis.metadata;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    private String fields;

}
