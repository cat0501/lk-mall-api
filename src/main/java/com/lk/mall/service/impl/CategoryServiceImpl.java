package com.lk.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mall.model.entity.GoodsCategory;
import com.lk.mall.model.vo.CategoryVO;
import com.lk.mall.mapper.CategoryMapper;
import com.lk.mall.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lak
 * @since 2022-08-31
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, GoodsCategory> implements ICategoryService {

}
