package com.muzimin.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @author: 李煌民
 * @date: 2024-08-27 15:18
 **/
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private final HashSet<Integer> set = new HashSet<Integer>();

    //初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    //判断是否校验成功
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
