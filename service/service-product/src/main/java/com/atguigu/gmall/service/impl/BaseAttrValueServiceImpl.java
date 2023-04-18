package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.model.productDto.AttrInfoRequest;
import com.atguigu.gmall.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service实现
* @createDate 2023-04-15 15:29:33
*/
@Service
public class BaseAttrValueServiceImpl extends ServiceImpl<BaseAttrValueMapper, BaseAttrValue>
    implements BaseAttrValueService{
    private final Logger log = LoggerFactory.getLogger(BaseAttrValueServiceImpl.class);
    @Autowired
    private BaseAttrValueMapper attrValueMapper;

    @Autowired
    private BaseAttrInfoMapper attrInfoMapper;
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        LambdaQueryWrapper<BaseAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseAttrValue::getAttrId,attrId);
        return attrValueMapper.selectList(wrapper);
    }

    @Override
    public Result saveAttrInfo(AttrInfoRequest attrInfoRequest) {
        // 保存属性信息到 base_attr_info
        Result result = new Result();
        BaseAttrInfo attrInfo = new BaseAttrInfo();
        Long id = -1L;
        if (null != attrInfoRequest){
            attrInfo.setAttrName(attrInfoRequest.getAttrName());
            attrInfo.setCategoryId(attrInfoRequest.getCategoryId());
            attrInfo.setCategoryLevel(attrInfoRequest.getCategoryLevel());
            attrInfoMapper.insert(attrInfo);
            id = attrInfo.getId();
        }else {
            result.setMessage("请求参数为空");
            result.setCode(ResultCodeEnum.FAIL.getCode());
            log.info("保存属性信息报错----");
        }
        Long key = id;
        List<BaseAttrValue> attrValueList = attrInfoRequest.getAttrValueList();
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        if (attrValueList.size() != 0 && attrValueList != null){
            // 保存属性值到base_attr_value
            attrValueList.forEach(value -> {
                baseAttrValue.setValueName(value.getValueName());
                baseAttrValue.setAttrId(key);
                attrValueMapper.insert(baseAttrValue);
            });
        }else {
            log.info("保存属性值错误--");
        }
        result.setMessage("保存属性信息成功");
        result.setCode(ResultCodeEnum.SUCCESS.getCode());



        return result;
    }
}




