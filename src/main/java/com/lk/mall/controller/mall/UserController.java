package com.lk.mall.controller.mall;

import cn.hutool.core.bean.BeanUtil;
import com.lk.mall.common.SystemConstant;
import com.lk.mall.config.annotation.TokenToMallUser;
import com.lk.mall.controller.mall.param.UserLoginParam;
import com.lk.mall.controller.mall.param.UserRegisterParam;
import com.lk.mall.entity.User;
import com.lk.mall.entity.vo.MallUserVO;
import com.lk.mall.service.IUserService;
import com.lk.mall.util.NumberUtil;
import com.lk.mall.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

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
    public R<String> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        String loginName = userLoginParam.getLoginName();
        String passwordMd5 = userLoginParam.getPasswordMd5();

        if (StringUtils.isBlank(loginName) && StringUtils.isBlank(passwordMd5)) {
            return new R<String>().fail(SystemConstant.INPUT_CORRECT_PHONE_PASSWORD);
        }
        // 用户名（手机号）校验
        if (!NumberUtil.isPhone(loginName)) {
            return new R<String>().fail(SystemConstant.PHONE_NUMBER_FORMAT_ERROR);
        }
        // 登录
        String loginResult = userService.login(loginName, passwordMd5);
        // 登陆结果判断
        log.info("login-------------------> loginName = {}, loginResult = {}", loginName, loginResult);
        if (StringUtils.isNotBlank(loginResult)) {
            return new R<String>().ok(loginResult);
        }
        return new R<String>().fail(SystemConstant.LOGIN_ERROR);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册", notes = "")
    public R register(@RequestBody UserRegisterParam userRegisterParam) {
        if (!NumberUtil.isPhone(userRegisterParam.getLoginName())) {
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


    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public R<MallUserVO> getUserDetail(@TokenToMallUser User loginMallUser) {
        //已登录则直接返回

        MallUserVO mallUserVO = new MallUserVO();
        BeanUtil.copyProperties(loginMallUser, mallUserVO);
        return new R<MallUserVO>().ok(mallUserVO);
    }

}

