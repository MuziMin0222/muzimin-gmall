package com.muzimin.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muzimin.common.utils.PageUtils;
import com.muzimin.common.utils.Query;

import com.muzimin.product.dao.CategoryDao;
import com.muzimin.product.entity.CategoryEntity;
import com.muzimin.product.service.CategoryService;


@Service("categoryService")
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        System.out.println("开始调用listTree方法: " + baseMapper.getClass().getName());
        List<CategoryEntity> entities = baseMapper.selectList(null);

        return entities
                .stream()
                .filter(new Predicate<CategoryEntity>() {
                    @Override
                    public boolean test(CategoryEntity categoryEntity) {
                        return categoryEntity.getParentCid() == 0;
                    }
                })
                .map(new Function<CategoryEntity, CategoryEntity>() {
                    @Override
                    public CategoryEntity apply(CategoryEntity categoryEntity) {
                        categoryEntity.setChildren(getChildrens(categoryEntity, entities));
                        return categoryEntity;
                    }
                })
                .sorted(new Comparator<CategoryEntity>() {
                    @Override
                    public int compare(CategoryEntity o1, CategoryEntity o2) {
                        int i1 = o1.getSort() == null ? 0 : o1.getSort();
                        int i2 = o2.getSort() == null ? 0 : o2.getSort();
                        return i1 - i2;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 找到category的完整路径
     * [父，子，孙]
     *
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        log.info("parentPath: {}", parentPath);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        log.info("findParentPath:catelogId: {}, paths: {}",catelogId, paths);
        CategoryEntity categoryEntity = this.getById(catelogId);
        if (categoryEntity.getParentCid() != 0) {
            findParentPath(categoryEntity.getParentCid(), paths);
        }
        return paths;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all
                .stream()
                .filter(new Predicate<CategoryEntity>() {
                    @Override
                    public boolean test(CategoryEntity categoryEntity) {
                        return categoryEntity.getParentCid() == root.getCatId();
                    }
                })
                .map(new Function<CategoryEntity, CategoryEntity>() {
                    @Override
                    public CategoryEntity apply(CategoryEntity categoryEntity) {
                        categoryEntity.setChildren(getChildrens(categoryEntity, all));
                        return categoryEntity;
                    }
                })
                .sorted(new Comparator<CategoryEntity>() {
                    @Override
                    public int compare(CategoryEntity o1, CategoryEntity o2) {
                        int i1 = o1.getSort() == null ? 0 : o1.getSort();
                        int i2 = o2.getSort() == null ? 0 : o2.getSort();
                        return i1 - i2;
                    }
                })
                .collect(Collectors.toList());
    }

}