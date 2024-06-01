package com.muzimin.order.dao;

import com.muzimin.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:33:24
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
