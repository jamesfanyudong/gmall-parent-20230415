package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.mapper.Category1Mapper;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.service.Category1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Category1ServiceImpl extends ServiceImpl<Category1Mapper,BaseCategory1> implements Category1Service {
    @Autowired
    private Category1Mapper category1Mapper;
    @Override
    public List<BaseCategory1> getCategory1List() {
        return category1Mapper.selectList(null);
    }
}
