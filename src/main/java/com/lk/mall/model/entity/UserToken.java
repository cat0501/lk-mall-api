package com.lk.mall.model.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author Lemonade
 * @description
 * @updateTime 2022/9/1 14:34
 */
@Data
public class UserToken {
    private Long userId;

    private String token;

    private Date updateTime;

    private Date expireTime;
}
