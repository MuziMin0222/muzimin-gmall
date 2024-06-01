package com.muzimin.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 23:12:37
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

