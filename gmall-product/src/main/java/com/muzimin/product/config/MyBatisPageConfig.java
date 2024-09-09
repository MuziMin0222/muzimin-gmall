package com.muzimin.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: 李煌民
 * @date: 2024-09-09 16:57
 * 分页插件
 **/
@Configuration
@EnableTransactionManagement //开启事务
@MapperScan("com.muzimin.product.dao")
public class MyBatisPageConfig {

    //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        //设置请求的页面大于最大页后操作，true返回到首页，false继续请求，默认是false
        paginationInterceptor.setOverflow(true);
        //设置最大单页限制数量，默认500条，-1不受限制
        paginationInterceptor.setLimit(500);

        return paginationInterceptor;
    }
}
