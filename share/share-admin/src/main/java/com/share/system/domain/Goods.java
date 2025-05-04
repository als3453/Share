package com.share.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 商品信息表对象 goods
 * 
 * @author share
 * @date 2025-02-19
 */
public class Goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private String id;

    /** 商品名 */
    @Excel(name = "商品名")
    private String name;

    /** 商家名 */
    @Excel(name = "商家名")
    private String busName;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBusName(String busName) 
    {
        this.busName = busName;
    }

    public String getBusName() 
    {
        return busName;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("busName", getBusName())
            .append("price", getPrice())
            .append("img", getImg())
            .append("status", getStatus())
            .append("type", getType())
            .toString();
    }
}
