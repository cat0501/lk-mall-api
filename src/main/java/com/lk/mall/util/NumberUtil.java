package com.lk.mall.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lemonade
 * @description
 * @updateTime 2022/9/1 13:22
 */
public class NumberUtil {

    /**
     * @description 判断是否为11位电话号码
     * @updateTime 2022/9/1 13:23
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static void main(String[] args) {
        boolean isPhone = NumberUtil.isPhone("17782975312");
        boolean isPhone2 = NumberUtil.isPhone("19229246362");
        boolean isPhone3 = NumberUtil.isPhone("5229246362");
        System.out.println(isPhone); // true
        System.out.println(isPhone2);// false
        System.out.println(isPhone3);// false
    }
}
