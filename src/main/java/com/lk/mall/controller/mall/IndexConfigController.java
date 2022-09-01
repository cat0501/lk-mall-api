package com.lk.mall.controller.mall;

import com.lk.mall.entity.vo.IndexInfoVo;
import com.lk.mall.entity.vo.MallCarouselVo;
import com.lk.mall.service.IIndexConfigService;
import com.lk.mall.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lak
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/api/v1")
public class IndexConfigController {

    @Autowired
    private IIndexConfigService indexConfigService;
    /*
    获取首页信息
     */
    @GetMapping("index-infos")
    private R getIndexInfo() {
        IndexInfoVo indexInfoVos=indexConfigService.getIndexInfo();


        R r=new R();
        r.setData(indexInfoVos);
        return r;
    }


}

