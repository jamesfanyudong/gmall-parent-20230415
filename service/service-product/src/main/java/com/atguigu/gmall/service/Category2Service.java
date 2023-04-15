package com.atguigu.gmall.service;


import com.atguigu.gmall.model.product.BaseCategory2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface Category2Service  extends IService<BaseCategory2> {
    List<BaseCategory2> getCategory2(String id);
}
