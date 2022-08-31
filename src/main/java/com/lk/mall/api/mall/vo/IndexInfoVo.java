package com.lk.mall.api.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class IndexInfoVo {
    @ApiModelProperty("首页轮播图列表")
    private List<MallCarouselVo> carousels;

    @ApiModelProperty("首页热门商品列表")
    private List<MallIndexConfigGoodsVo>  hotGoodses;

    @ApiModelProperty("首页新品列表")
    private List<MallIndexConfigGoodsVo>  newGoodses;

    @ApiModelProperty("首页推荐商品列表")
    private  List<MallIndexConfigGoodsVo>  recommendGoodses;

}
