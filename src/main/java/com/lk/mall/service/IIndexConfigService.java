package com.lk.mall.service;

import com.lk.mall.model.entity.IndexConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.mall.model.vo.IndexInfoVo;

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
