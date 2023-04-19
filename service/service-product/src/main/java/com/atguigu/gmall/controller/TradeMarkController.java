package com.atguigu.gmall.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 品牌controller
 * @Auther: fyd20
 * @Date: 2023/4/19 14:21
 * @Description: TradeMarkController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/product")
public class TradeMarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;


    @GetMapping("/baseTrademark/{pageNo}/{pageSize}")
    public Result getBaseTrademark(@PathVariable("pageNo") long pageNo,
                                   @PathVariable("pageSize") long pageSize){

        Page<BaseTrademark> p = new Page<>(pageNo,pageSize);
        Page<BaseTrademark> page = baseTrademarkService.page(p);
        return Result.ok(page);
    }

    /**
     * 保存品牌信息
     * @param baseTrademark
     * @return
     */
    @PostMapping("/baseTrademark/save")
    public Result saveTradeMark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }
    ///baseTrademark/update

    @PutMapping("/baseTrademark/update")
    public Result updateTrademark(@RequestBody BaseTrademark baseTrademark){
        boolean flag = baseTrademarkService.updateById(baseTrademark);
        if (flag){
            return Result.ok();
        }else {
            return Result.ok(ResultCodeEnum.FAIL);
        }
    }

    // /baseTrademark/remove/{id}
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result deleTrademark(@PathVariable("id") String id){
        boolean flag = baseTrademarkService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.ok(ResultCodeEnum.FAIL);
        }
    }
    ///baseTrademark/get/{id}
    @GetMapping("/baseTrademark/get/{id}")
    public Result getTrademark(@PathVariable("id") String id){

        BaseTrademark byId = baseTrademarkService.getById(id);
        return Result.ok(byId);
    }

}
