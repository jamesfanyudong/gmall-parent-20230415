package com.atguigu.gmall.service;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2023-04-15 10:22:37
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {

    List<BaseCategory3> getCategory3(String id);
}
