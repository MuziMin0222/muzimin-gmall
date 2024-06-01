package com.muzimin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.order.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:33:24
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

