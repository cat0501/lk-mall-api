package com.lk.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cat
 * @description 分类页面VO（第二级）
 * @date 2022/9/2 8:25 PM
 */
@Data
public class SecondLevelCategoryVO {

    @ApiModelProperty("当前二级分类ID")
    private Long categoryId;

    @ApiModelProperty("当前二级分类名称")
    private String categoryName;

    @ApiModelProperty("当前分类级别")
    private Byte categoryLevel;

    @ApiModelProperty("父级分类ID")
    private Long parentId;

    @ApiModelProperty("三级分类列表")
    private List<ThirdLevelCategoryVO> thirdLevelCategoryVOS;
}
