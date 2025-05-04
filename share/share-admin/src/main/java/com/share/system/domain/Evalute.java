package com.share.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 评价信息表对象 evalute
 * 
 * @author share
 * @date 2025-02-23
 */
public class Evalute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价id */
    private String id;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 好评中评差评 */
    @Excel(name = "好评中评差评")
    private Long status;

    /** 评价用户 */
    @Excel(name = "评价用户")
    private Long userId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setGoodId(Long goodId) 
    {
        this.goodId = goodId;
    }

    public Long getGoodId() 
    {
        return goodId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("goodId", getGoodId())
            .toString();
    }
}
