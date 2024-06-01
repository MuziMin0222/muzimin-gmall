package com.muzimin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:33:24
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

