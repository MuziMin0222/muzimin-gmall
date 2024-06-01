package com.muzimin.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 23:04:42
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

