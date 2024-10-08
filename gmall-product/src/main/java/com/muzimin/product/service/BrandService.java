package com.muzimin.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 17:45:22
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);
}

