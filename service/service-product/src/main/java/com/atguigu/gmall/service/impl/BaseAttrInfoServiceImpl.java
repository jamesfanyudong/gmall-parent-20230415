package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.service.BaseAttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author fyd20
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2023-04-15 15:28:38
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{
    @Autowired
    private BaseAttrInfoMapper attrInfoMapper;
    @Override
    public List<BaseAttrInfo> getAttrInfoList(String category1Id, String category2Id, String category3Id) {
        // 查找以及分类的属性
        if ("0".equals(category2Id) && "0".equals(category3Id)){
            // 查一级分类的平台属性
            return getBaseAttrInfoList(category1Id, "1");
        } else if ("0".equals(category3Id)){
            // 查二级分类的平台属性
            return getBaseAttrInfoList(category2Id, "2");

        }else {
            // 查三级分类的平台属性
            return getBaseAttrInfoList(category3Id, "3");
        }

    }

    /**
     * 获取一二三级分类的平台属性
     * @param categoryId 分类id
     * @param val 分类级别
     * @return
     */
    private List<BaseAttrInfo> getBaseAttrInfoList(String categoryId, String val) {
        LambdaQueryWrapper<BaseAttrInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseAttrInfo::getCategoryId, categoryId).
                eq(BaseAttrInfo::getCategoryLevel, val);
        return attrInfoMapper.selectList(wrapper);
    }
}




