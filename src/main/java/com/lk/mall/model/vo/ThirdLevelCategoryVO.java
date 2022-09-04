package com.lk.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cat
 * @description 分类页面VO（第三级）
 * @date 2022/9/2 8:30 PM
 */
@Data
public class ThirdLevelCategoryVO {

    @ApiModelProperty("当前三级分类ID")
    private Long categoryId;

    @ApiModelProperty("当前三级分类名称")
    private String categoryName;

    @ApiModelProperty("当前分类级别")
    private Byte categoryLevel;

    @ApiModelProperty("父级分类ID")
    private Long parentId;
}
