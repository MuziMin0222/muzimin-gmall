package com.muzimin.coupon.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.muzimin.coupon.entity.CouponEntity;
import com.muzimin.coupon.service.CouponService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.common.utils.R;


/**
 * 优惠券信息
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 22:47:11
 */
@RestController
@RequestMapping("/coupon")
@RefreshScope
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Value("${coupon.name}")
    private String name;
    @Value("${coupon.age}")
    private String age;

    @RequestMapping("/test")
    public R test() {
        return R.ok().put("name", name).put("age", age);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions(":coupon:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions(":coupon:info")
    public R info(@PathVariable("id") Long id) {
        CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions(":coupon:save")
    public R save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions(":coupon:update")
    public R update(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions(":coupon:delete")
    public R delete(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
