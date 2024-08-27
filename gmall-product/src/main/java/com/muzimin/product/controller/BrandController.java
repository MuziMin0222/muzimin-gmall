package com.muzimin.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.muzimin.common.valid.AddGroup;
import com.muzimin.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muzimin.product.entity.BrandEntity;
import com.muzimin.product.service.BrandService;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 17:45:22
 *
 * JSR303：
 *  1、给Bean添加校验注解，并定义自己的message消息
 *  2、开启校验功能@Valid
 *      效果：校验错误以后会有一个默认的反应
 *  3、给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果
 *  4、分组校验（多场景的复杂校验）
 *      1、给校验注解标注什么情况下需要进行校验
 *      2、controller中需要添加@Validated的注解，表示该方法在什么时候需要进行校验
 *      3、默认没有指定分组的校验注解，在分组校验情况下不生效
 *  5、自定义校验
 *      1、编写一个自定义的校验注解
 *      2、编写一个自定义的校验器
 *      3、
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions(":brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions(":brand:info")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions(":brand:save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand/*, BindingResult result*/) {
        /*HashMap<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            return R.error(400, "提交的数据不合法").put("data", map);
        } else {
            brandService.save(brand);
        }*/
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions(":brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions(":brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
