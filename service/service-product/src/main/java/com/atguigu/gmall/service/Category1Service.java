package com.atguigu.gmall.service;

import com.atguigu.gmall.model.product.BaseCategory1;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Category1Service extends IService<BaseCategory1> {
    List<BaseCategory1> getCategory1List();
}
