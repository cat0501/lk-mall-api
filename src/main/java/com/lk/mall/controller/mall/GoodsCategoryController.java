package com.lk.mall.controller.mall;


import com.lk.mall.common.MallException;
import com.lk.mall.common.SystemConstant;
import com.lk.mall.model.entity.GoodsCategory;
import com.lk.mall.model.vo.CategoryVO;
import com.lk.mall.model.vo.SecondLevelCategoryVO;
import com.lk.mall.model.vo.ThirdLevelCategoryVO;
import com.lk.mall.service.ICategoryService;
import com.lk.mall.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author lak
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/api/v1")
public class GoodsCategoryController {
    @Resource
    private ICategoryService categoryService;

    @GetMapping("/categories")
    @ApiOperation("获取分类页面数据")
    public R<List<CategoryVO>> getCategories() throws InstantiationException, IllegalAccessException {
        //--------------------------获取所有数据--------------------------------
        List<GoodsCategory> categoryVOS = categoryService.list(null);
        if (CollectionUtils.isEmpty(categoryVOS)){
            throw new MallException(SystemConstant.DATA_NOT_FOUND);
        }
        //--------------------------获取分级数据--------------------------------
        List<CategoryVO> vos = getCategories(CategoryVO.class, categoryVOS, 1);
        List<SecondLevelCategoryVO> sos = getCategories(SecondLevelCategoryVO.class, categoryVOS, 2);
        List<ThirdLevelCategoryVO> tos = getCategories(ThirdLevelCategoryVO.class, categoryVOS, 3);

        //--------------------------封装 children--------------------------------
        Map<Long, List<SecondLevelCategoryVO>> longListMap1 = sos.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
        Set<Long> longs1 = longListMap1.keySet();
        for (CategoryVO vo : vos) {
            for (Long aLong : longs1) {
                List<SecondLevelCategoryVO> secondLevelCategoryVOS = longListMap1.get(aLong);
                if (Objects.equals(vo.getCategoryId(), aLong)){
                    vo.setSecondLevelCategoryVOS(secondLevelCategoryVOS);
                }
            }
        }

        Map<Long, List<ThirdLevelCategoryVO>> longListMap2 = tos.stream().collect(groupingBy(ThirdLevelCategoryVO::getParentId));
        Set<Long> longs2 = longListMap2.keySet();
        for (SecondLevelCategoryVO so : sos) {
            for (Long aLong : longs2) {
                List<ThirdLevelCategoryVO> thirdLevelCategoryVOS = longListMap2.get(aLong);
                if (Objects.equals(so.getCategoryId(), aLong)){
                    so.setThirdLevelCategoryVOS(thirdLevelCategoryVOS);
                }
            }
        }

        return new R<List<CategoryVO>>().ok(vos);
    }


    private <T> List<T> getCategories(Class<T> t, List<GoodsCategory> categories, int level) throws InstantiationException, IllegalAccessException {
        List<GoodsCategory> categoryVOS = categories.stream()
                .filter(e -> e.getCategoryLevel() == level).collect(Collectors.toList());

        List<T> vos = new ArrayList<>();
        for (GoodsCategory goodsCategory : categoryVOS) {
            T categoryVO = t.newInstance();
            BeanUtils.copyProperties(goodsCategory, categoryVO);
            vos.add(categoryVO);
        }
        return vos;
    }

}



//List<GoodsCategory> categoryVOS1 = categoryVOS.stream()
//        .filter(e -> e.getCategoryLevel() == 1).collect(Collectors.toList());
//List<CategoryVO> vos = new ArrayList<>();
//for (GoodsCategory goodsCategory : categoryVOS1) {
//    CategoryVO categoryVO = new CategoryVO();
//    BeanUtils.copyProperties(goodsCategory, categoryVO);
//    vos.add(categoryVO);
//}

//List<GoodsCategory> categoryVOS2 = categoryVOS.stream()
//        .filter(e -> e.getCategoryLevel() == 2).collect(Collectors.toList());
//List<SecondLevelCategoryVO> sos = new ArrayList<>();
//for (GoodsCategory goodsCategory : categoryVOS2) {
//    SecondLevelCategoryVO svo = new SecondLevelCategoryVO();
//    BeanUtils.copyProperties(goodsCategory, svo);
//    sos.add(svo);
//}
//
//List<GoodsCategory> categoryVOS3 = categoryVOS.stream()
//        .filter(e -> e.getCategoryLevel() == 3).collect(Collectors.toList());
//List<ThirdLevelCategoryVO> tos = new ArrayList<>();
//for (GoodsCategory goodsCategory : categoryVOS3) {
//    ThirdLevelCategoryVO categoryVO = new ThirdLevelCategoryVO();
//    BeanUtils.copyProperties(goodsCategory, categoryVO);
//    tos.add(categoryVO);
//}

