package com.muzimin.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.muzimin.common.valid.AddGroup;
import com.muzimin.common.valid.ListValue;
import com.muzimin.common.valid.UpdateGroup;
import com.muzimin.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author muzimin
 * @email 1367930183@qq.com
 * @date 2024-06-01 17:45:22
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    //处理精度丢失问题
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    @NotNull(message = "修改必须制定品牌ID", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定ID", groups = {AddGroup.class})
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @URL(message = "logo必须是一个合法的URL地址", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(groups = {AddGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是a-z或者A-Z的字母", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(groups = {AddGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @Min(value = 0, message = "排序字段最小的值是0", groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(groups = {AddGroup.class})
    private Integer sort;

}
