package com.lk.mall.controller.mall;

import com.lk.mall.common.SystemConstant;
import com.lk.mall.controller.mall.param.UserLoginParam;
import com.lk.mall.controller.mall.param.UserRegisterParam;
import com.lk.mall.service.IUserService;
import com.lk.mall.util.NumberUtil;
import com.lk.mall.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lak
 * @since 2022-08-30
 */
@Api(value = "用户相关接口", tags = {"登录、登出"})
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/login")
    //public R<String> login(@RequestBody @Valid UserLoginParam userLoginParam){
    public R<String> login(@RequestBody UserLoginParam userLoginParam){
        if (StringUtils.isBlank(userLoginParam.getLoginName()) && StringUtils.isBlank(userLoginParam.getPasswordMd5())){
            return new R<String>().fail(SystemConstant.INPUT_CORRECT_PHONE_PASSWORD);
        }
        // 用户名（手机号）校验
        if (NumberUtil.isPhone(userLoginParam.getLoginName())) {
            return new R<String>().fail(SystemConstant.PHONE_NUMBER_FORMAT_ERROR);
        }
        // 登录
        String loginResult = userService.login(userLoginParam.getLoginName(), userLoginParam.getPasswordMd5());
        log.info("login-------------------> loginName = {}, loginResult = {}", userLoginParam.getLoginName(),loginResult);
        if (StringUtils.isNotBlank(loginResult)){
            return new R<String>().ok(loginResult);
        }
        return new R<String>().fail(SystemConstant.LOGIN_ERROR);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册", notes = "")
    public R register(@RequestBody UserRegisterParam userRegisterParam) {
        if (!NumberUtil.isPhone(userRegisterParam.getLoginName())){
            return new R<>().fail("请输入正确的手机号！");
        }
        String registerResult = userService.register(userRegisterParam.getLoginName(), userRegisterParam.getPassword());

        log.info("register api,loginName={},loginResult={}", userRegisterParam.getLoginName(), registerResult);

        //注册成功
        if ("success".equals(registerResult)) {
            return new R<>().ok();
        }
        //注册失败
        return new R<>().fail("注册失败！");
    }

}

