package com.lk.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*
首页商品配置vo
 */
@Data
public class MallIndexConfigGoodsVo {
    @ApiModelProperty("商品图片地址")
    private String goodsCoverImg;

    @ApiModelProperty("商品id")
    private String goodsId;

    @ApiModelProperty("商品信息")
    private String goodsIntro;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品价格")
    private String sellingPrice;


}
