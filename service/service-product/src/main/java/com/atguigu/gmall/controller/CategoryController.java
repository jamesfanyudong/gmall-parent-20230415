package com.atguigu.gmall.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.service.BaseCategory3Service;
import com.atguigu.gmall.service.Category1Service;
import com.atguigu.gmall.service.Category2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class CategoryController {
    @Autowired
    private Category1Service category1Service;
    @Autowired
    private Category2Service category2Service;

    @Autowired
    private BaseCategory3Service category3Service;

    /**
     * 获取所有的一级分类
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getCategory1List(){
       List<BaseCategory1>  list = category1Service.getCategory1List();
       return Result.ok(list);
    }


    /**
     * 获取商品的二级分类
     * @param id
     * @return
     */
    @GetMapping("/getCategory2/{id}")
    public Result getCategory2(@PathVariable String id){
        List<BaseCategory2> list2 =  category2Service.getCategory2(id);
        return Result.ok(list2);
    }

    @GetMapping("/getCategory3/{id}")
    public Result getCategory3(@PathVariable String id){
        List<BaseCategory3> list3 =  category3Service.getCategory3(id);
        return Result.ok(list3);
    }
}
