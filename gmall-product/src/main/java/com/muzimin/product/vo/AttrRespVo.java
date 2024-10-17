package com.muzimin.product.vo;

import lombok.Data;

/**
 * @author: 李煌民
 * @date: 2024-10-12 16:35
 **/
@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}
