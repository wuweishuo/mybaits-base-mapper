package com.wws.mybatis.inject;

import org.apache.ibatis.session.Configuration;

/**
 * @author wws
 * @version 1.0.0
 * @date 2020-01-16 14:23
 **/
public interface Injector {

    void inject(Class mapper);

    void setConfig(Configuration config);

}
