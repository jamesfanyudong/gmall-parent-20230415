package com.atguigu.gmall.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: fyd20
 * @Date: 2023/4/15 15:17
 * @Description: AttrInfoController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/product")
public class AttrInfoController {

    @Autowired
    private BaseAttrInfoService attrInfoService;
    /**
     * 根据分类id获取商品的属性信息
     * @return
     */
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrInfo(@PathVariable("category1Id") String category1Id,
                              @PathVariable("category2Id") String category2Id,
                              @PathVariable("category3Id") String category3Id){

        List<BaseAttrInfo> list = attrInfoService.getAttrInfoList(category1Id,
                category2Id,
                category3Id);

        return Result.ok(list);
    }


}
