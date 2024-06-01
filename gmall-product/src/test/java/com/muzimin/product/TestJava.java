package com.muzimin.product;

import com.muzimin.product.entity.BrandEntity;
import com.muzimin.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: 李煌民
 * @date: 2024-06-01 18:05
 **/
@Slf4j
@SpringBootTest
public class TestJava {
    @Autowired
    private BrandService brandService;
    @Test
    public void test() {
        BrandEntity entity = new BrandEntity();
        entity.setName("huawei");
        brandService.save(entity);
    }
}
