package com.atguigu.gmall.service;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.model.productDto.AttrInfoRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service
* @createDate 2023-04-15 15:29:33
*/
public interface BaseAttrValueService extends IService<BaseAttrValue> {

    List<BaseAttrValue> getAttrValueList(String attrId);

    Result saveAttrInfo(AttrInfoRequest attrInfoRequest);

}
