package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.mapper.Category1Mapper;
import com.atguigu.gmall.mapper.Category2Mapper;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.service.Category2Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: fyd20
 * @Date: 2023/4/15 09:58
 * @Description: Category2ServiceImpl
 * @Version 1.0.0
 */
@Service
public class Category2ServiceImpl extends ServiceImpl<Category2Mapper, BaseCategory2>  implements Category2Service {
    @Autowired
    private Category2Mapper category2Mapper;
    @Override
    public List<BaseCategory2> getCategory2(String id) {
        LambdaQueryWrapper<BaseCategory2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseCategory2::getCategory1Id,id);
        return category2Mapper.selectList(wrapper);
    }
}
