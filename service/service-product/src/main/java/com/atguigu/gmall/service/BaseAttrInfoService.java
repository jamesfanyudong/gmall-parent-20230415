package com.atguigu.gmall.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2023-04-15 15:28:38
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    List<BaseAttrInfo> getAttrInfoList(String category1Id, String category2Id, String category3Id);
}
