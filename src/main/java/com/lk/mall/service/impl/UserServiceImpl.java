package com.lk.mall.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lk.mall.common.SystemConstant;
import com.lk.mall.model.entity.User;
import com.lk.mall.model.entity.UserToken;
import com.lk.mall.mapper.UserMapper;
import com.lk.mall.mapper.UserTokenMapper;
import com.lk.mall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mall.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author lak
 * @since 2022-08-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserTokenMapper userTokenMapper;

    @Override
    public String login(String loginName, String passwordMD5) {
        /** 官方说明--------------------------------------------------------------
         @ https://baomidou.com/pages/10c804/

         警告：不支持以及不赞成在 RPC 调用中把 Wrapper 进行传输
         1.wrapper 很重
         2.传输 wrapper 可以类比为你的 controller 用 map 接收值(开发一时爽,维护火葬场)
         3.正确的 RPC 调用姿势是写一个 DTO 进行传输,被调用方再根据 DTO 执行相应的操作
         4.我们拒绝接受任何关于 RPC 传输 Wrapper 报错相关的 issue 甚至 pr
         */

        /** 一些观点--------------------------------------------------------------
         @ https://blog.csdn.net/lisheng0947/article/details/122359565

         为什么 MyBatis-Plus 这么好用，反而用的不多？
         对会用的人来说，MyBatis-Plus 的 wrapper 非常好用，不再需要去关注dao层了，
         但是这需要一定的学习成本，而且不太符合经典的<三层架构思维>，对一些老前辈来说完全是违反常识的，很别扭。
         对他们来说，dao层还是拿在自己手里更踏实，给第三方封装起来有点不踏实，

         另一方面，现在代码生成器的框架很多，用起来很方便，老前辈们宁愿用“mybatis+代码生成器”的组合，不愿意颠覆自己的思维习惯；
         当然这样的代价就是自己维护xml文件，很麻烦。

         mp<不能很方便地多表联查>，然后映射成一个vo对象，这样的话service层的业务逻辑会变复杂，多次查询导致性能也会有所下降
         */

        /** 关于使用
         @ https://blog.csdn.net/lt326030434/article/details/106571670

         */
        //LambdaQueryWrapper<Object> lambdaQueryWrapper = new QueryWrapper<>().lambda();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getLoginName, loginName).eq(User::getPasswordMd5, passwordMD5)
                .eq(User::getIsDeleted, 1);
        User user = userMapper.selectOne(lambdaQueryWrapper);

        if (user == null){
            return SystemConstant.USER_NOT_FOUND;
        }

        if (user.getLockedFlag() == 1){
            return SystemConstant.LOGIN_USER_LOCKED_ERROR;
        }

        // 查询 token
        UserToken userToken = userTokenMapper.selectByPrimaryKey(user.getUserId());

        // 生成 token
        String token = System.currentTimeMillis() + "" + user.getUserId() + RandomUtil.randomNumbers(4);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(token.getBytes());
            token = new BigInteger(1, md5.digest()).toString(16);

            token = token.length() == 31 ? token += "-" : token;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        Date now = new Date();
        Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000); // 48小时过期

        if (userToken == null){
            userToken = new UserToken();
            userToken.setUserId(user.getUserId());
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            // 新增一条 token 数据
            if (userTokenMapper.insert(userToken) > 0) {
                return token;
            }
        } else {
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //更新
            if (userTokenMapper.updateById(userToken) > 0) {
                //修改成功后返回
                return token;
            }
        }

        return SystemConstant.USER_NOT_FOUND;
    }

    @Override
    public String register(String loginName, String password) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getLoginName, loginName);
        if(userMapper.selectOne(lambdaQueryWrapper) != null){
            return "用户名已存在";
        }
        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setPasswordMd5(password);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign("加油");
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (userMapper.insert(registerUser) > 0){
            return "success";
        }
        return "database error";
    }

    private static String getNewToken(String timeStr, Long userId) {
        String token = timeStr + userId + RandomUtil.randomNumbers(5);
        return token;
    }

    public static void main(String[] args) {
        // RandomUtil 生成随机整数
        System.out.println(RandomUtil.randomNumbers(5));
    }
}
