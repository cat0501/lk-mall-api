package com.lk.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
首页轮播图vo
 */
@Data
public class MallCarouselVo {

    @ApiModelProperty("轮播图图片地址")
    private String carouselUrl;
    @ApiModelProperty("轮播图点击后的跳转路径")
    private String redirectUrl;
}
