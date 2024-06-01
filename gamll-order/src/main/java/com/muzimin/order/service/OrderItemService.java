package com.muzimin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:33:24
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

