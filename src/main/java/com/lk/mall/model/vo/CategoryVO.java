package com.lk.mall.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author cat
 * @description 分类页面VO
 * @date 2022/9/2 8:23 PM
 */
@Data
@TableName("mall_goods_category")
public class CategoryVO implements Serializable {
    @ApiModelProperty("当前一级分类ID")
    private Long categoryId;

    @ApiModelProperty("当前一级分类名称")
    private String categoryName;

    @ApiModelProperty("当前分类级别")
    private Byte categoryLevel;

    @TableField(exist = false)
    @ApiModelProperty("二级分类列表")
    private List<SecondLevelCategoryVO> secondLevelCategoryVOS;
}
