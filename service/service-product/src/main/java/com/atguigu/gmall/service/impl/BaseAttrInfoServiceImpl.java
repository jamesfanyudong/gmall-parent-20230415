package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.service.BaseAttrInfoService;
import com.atguigu.gmall.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author fyd20
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2023-04-15 15:28:38
*/
@Service
@Slf4j
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{
    @Autowired
    private BaseAttrInfoMapper attrInfoMapper;
    @Autowired
    private BaseAttrValueService baseAttrValueService;

    @Override
    public List<BaseAttrInfo> getAttrInfoList(String category1Id, String category2Id, String category3Id) {
        // 查找以及分类的属性
        if ("0".equals(category2Id) && "0".equals(category3Id)){
            // 查一级分类的平台属性
            return getBaseAttrInfoList(category1Id, "1");
        } else if ("0".equals(category3Id)){
            // 查二级分类的平台属性
            return getTwoBaseAttrInfoList(category1Id,category2Id);

        }else {
            // 查三级分类的平台属性
            return getAllBaseAttrInfoList(category1Id,category2Id,category3Id);
        }

    }

    @Override
    public List<BaseAttrInfo> getAttrInfoList2(String category1Id, String category2Id, String category3Id) {

        return attrInfoMapper.getAttrInfoList2(category1Id,category2Id,category3Id);
    }

    @Override
    public void insertAttrInfo(BaseAttrInfo attrInfo) {
        log.info("新增属性信息");
        // 保存属性名
        attrInfoMapper.insert(attrInfo);
        Long id = attrInfo.getId();
        // 保存属性值
        List<BaseAttrValue> attrValueList = attrInfo.getAttrValueList();
        if (attrValueList.size() != 0 && attrValueList != null){
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setAttrId(id);
            }
        }
        // 批量保存
        baseAttrValueService.saveBatch(attrValueList);

    }

    @Override
    public void updateAttrInfoAndValue(BaseAttrInfo attrInfo) {
        // 修改属性名信息
        baseMapper.updateById(attrInfo);
        List<Long> vIds = new ArrayList<>();
        List<BaseAttrValue> attrValueList = attrInfo.getAttrValueList();
        attrValueList.forEach(attrValue -> {
            if (attrValue.getAttrId() != null){
                // 新增属性值
                attrValue.setId(attrInfo.getId());
                baseAttrValueService.save(attrValue);
            }else {
                // 修改属性值
                baseAttrValueService.updateById(attrValue);
                vIds.add(attrValue.getId());
            }
        });
        // 删除属性值
        if (vIds.size() > 0){
            // 删除部分属性值
            LambdaQueryWrapper<BaseAttrValue> deleWra = new LambdaQueryWrapper<>();
            deleWra.notIn(BaseAttrValue::getId,vIds).eq(BaseAttrValue::getAttrId,attrInfo.getId());
            baseAttrValueService.remove(deleWra);
        }else {
            // 删除全部
            LambdaQueryWrapper<BaseAttrValue> deleWra = new LambdaQueryWrapper<>();
            deleWra.eq(BaseAttrValue::getAttrId,attrInfo.getId());
            baseAttrValueService.remove(deleWra);
        }

    }

    private List<BaseAttrInfo> getTwoBaseAttrInfoList(String category1Id, String category2Id) {
        LambdaQueryWrapper<BaseAttrInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseAttrInfo::getCategoryId,category1Id).eq(BaseAttrInfo::getCategoryLevel, 1).or().
                eq(BaseAttrInfo::getCategoryId,category2Id).eq(BaseAttrInfo::getCategoryLevel, 2);
        return attrInfoMapper.selectList(wrapper);
    }

    private List<BaseAttrInfo> getAllBaseAttrInfoList(String category1Id, String category2Id, String category3Id) {
        LambdaQueryWrapper<BaseAttrInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseAttrInfo::getCategoryId,category1Id).eq(BaseAttrInfo::getCategoryLevel, 1).or().
                eq(BaseAttrInfo::getCategoryId,category2Id).eq(BaseAttrInfo::getCategoryLevel, 2).or().
                eq(BaseAttrInfo::getCategoryId,category3Id).eq(BaseAttrInfo::getCategoryLevel, 3);
        return attrInfoMapper.selectList(wrapper);
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




