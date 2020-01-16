package com.wws.mybatis;

import com.wws.mybatis.annotation.Column;
import com.wws.mybatis.annotation.Id;
import com.wws.mybatis.annotation.Table;
import lombok.Data;
import lombok.ToString;

/**
 * @author wws
 * @version 1.0.0
 * @date 2019-11-06 10:53
 **/
@Data
@ToString
@Table(value = "user")
public class User {

    @Id(value = "id")
    private Long id;

    @Column(value = "username")
    private String username;

    @Column(value = "password")
    private String password;

}
