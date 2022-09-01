package com.lk.mall.controller.mall.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Lemonade
 * @description 登录名和密码
 * @updateTime 2022/9/1 13:11
 */
//@Data
public class UserLoginParam implements Serializable {

    @ApiModelProperty("用户名")
    //@NotEmpty(message = "用户名不能为空")
    @JsonProperty(value = "loginName")
    private String loginName;

    @ApiModelProperty("密码（Md5加密）")
    //@NotEmpty(message = "密码不能为空")
    @JsonProperty(value = "passwordMd5")
    private String passwordMd5;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }
}
