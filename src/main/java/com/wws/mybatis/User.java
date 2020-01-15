package com.wws.mybatis;

import lombok.Data;
import lombok.ToString;

/**
 * @author wws
 * @version 1.0.0
 * @date 2019-11-06 10:53
 **/
@Data
@ToString
public class User {

    private Long id;

    private String username;

    private String password;

}
