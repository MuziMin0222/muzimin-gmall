package com.muzimin.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 17:45:22
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long categlogId);
}

