package com.lk.mall.service;

import com.lk.mall.entity.IndexConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.mall.entity.vo.IndexInfoVo;
import com.lk.mall.entity.vo.MallCarouselVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lak
 * @since 2022-08-31
 */
public interface IIndexConfigService extends IService<IndexConfig> {

    //获取首页信息
    IndexInfoVo getIndexInfo();
}
