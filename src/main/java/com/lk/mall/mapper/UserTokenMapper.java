package com.lk.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lk.mall.model.entity.UserToken;

/**
 * @author Lemonade
 * @description
 * @updateTime 2022/9/1 14:33
 */
public interface UserTokenMapper extends BaseMapper<UserToken> {

    UserToken selectByPrimaryKey(Long userId);

    UserToken selectByToken(String token);

}
