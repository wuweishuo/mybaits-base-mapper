package com.wws.mybatis;

import java.io.Serializable;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-15 15:48
 **/
public interface BaseMapper<T> {

    T findById(Serializable id);

    int deleteById(Serializable id);

}
