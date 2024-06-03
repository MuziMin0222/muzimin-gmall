package com.muzimin.member.feign;

import com.muzimin.common.utils.R;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 李煌民
 * @date: 2024-06-03 21:58
 **/
@FeignClient("gmall-coupon")
public interface CouponService {
    @LoadBalanced
    @RequestMapping("/coupon/test")
    public R test();
}
