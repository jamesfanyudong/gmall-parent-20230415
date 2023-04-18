package com.atguigu.gmall.model.productDto;

import com.atguigu.gmall.model.product.BaseAttrValue;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Auther: fyd20
 * @Date: 2023/4/17 11:06
 * @Description: AttrInfoRequest
 * @Version 1.0.0
 */
@Data
public class AttrInfoRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String attrName;
    private List<BaseAttrValue> attrValueList;
    private Long categoryId;
    private Integer categoryLevel;
}
