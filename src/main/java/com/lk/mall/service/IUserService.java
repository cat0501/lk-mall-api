package com.lk.mall.service;

import com.lk.mall.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lak
 * @since 2022-08-30
 */
public interface IUserService extends IService<User> {
    /**
     * @description 登录
     * @updateTime 2022/9/1 13:45
     * @return java.lang.String
     */
    String login(String loginName, String passwordMD5);

    String register(String loginName, String password);
}
