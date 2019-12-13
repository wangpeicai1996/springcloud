package com.pcwang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pcwang.service.TestService;

/**
 * 服务接口的实现类
 */

/**
 * @Service 此处的注解为dubbo包下的
 * version：指定服务版本
 * application:指定应用服务名字
 * registry:指定注册中心
 */
@Service(version = "1.0")
public class TestServiceImpl implements TestService {
    @Override
    public String getUser(String userName) {
        return "你要的用户是："+userName;
    }
}
