package com.share.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 商品种类表对象 type_goods
 * 
 * @author share
 * @date 2025-02-17
 */
public class TypeGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品种类id */
    private String id;

    /** 商品种类 */
    @Excel(name = "商品种类")
    private String goodType;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setGoodType(String goodType) 
    {
        this.goodType = goodType;
    }

    public String getGoodType() 
    {
        return goodType;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodType", getGoodType())
            .append("img", getImg())
            .toString();
    }
}
