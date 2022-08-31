package com.lk.mall.service.impl;

import com.lk.mall.entity.Carousel;
import com.lk.mall.entity.IndexConfig;
import com.lk.mall.entity.vo.IndexInfoVo;
import com.lk.mall.entity.vo.MallCarouselVo;
import com.lk.mall.mapper.IndexConfigMapper;
import com.lk.mall.service.ICarouselService;
import com.lk.mall.service.IIndexConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lak
 * @since 2022-08-31
 */
@Service
public class IndexConfigServiceImpl extends ServiceImpl<IndexConfigMapper, IndexConfig> implements IIndexConfigService {
    @Autowired
    private ICarouselService  carouselService;

    @Override
    public IndexInfoVo getIndexInfo() {
        IndexInfoVo indexInfoVo = new IndexInfoVo();


        List<Carousel> carouselList = carouselService.list(null);
        List<MallCarouselVo> carouselVoList = new ArrayList<>();
        for (Carousel carousel : carouselList) {
            MallCarouselVo carouselVo = new MallCarouselVo();
            BeanUtils.copyProperties(carousel,carouselVo);
            carouselVoList.add(carouselVo);
        }

        indexInfoVo.setCarousels(carouselVoList);
        return indexInfoVo;
    }
}
