package com.muzimin.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 23:12:37
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

