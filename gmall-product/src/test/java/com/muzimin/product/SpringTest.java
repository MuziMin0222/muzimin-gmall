package com.muzimin.product;

import com.muzimin.product.entity.AttrGroupEntity;
import com.muzimin.product.service.AttrGroupService;
import com.muzimin.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author: 李煌民
 * @date: 2024-09-09 15:08
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    AttrGroupService attrGroupService;

    @Test
    public void testFindPath() {
        AttrGroupEntity attrGroup = attrGroupService.getById(1);
        Long catelogId = attrGroup.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(catelogPath);
        System.out.println(attrGroup);
    }
}
