package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.service.BaseCategory3Service;
import com.atguigu.gmall.mapper.BaseCategory3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
* @createDate 2023-04-15 10:22:37
*/
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
    implements BaseCategory3Service{

    @Autowired
    private BaseCategory3Mapper category3Mapper;
    @Override
    public List<BaseCategory3> getCategory3(String id) {
        LambdaQueryWrapper<BaseCategory3> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseCategory3::getCategory2Id,id);
        return category3Mapper.selectList(wrapper);
    }
}




