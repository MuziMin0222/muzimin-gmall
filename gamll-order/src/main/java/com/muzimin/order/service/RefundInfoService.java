package com.muzimin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:33:23
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

