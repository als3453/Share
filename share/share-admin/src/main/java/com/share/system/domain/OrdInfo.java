package com.share.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 订单管理对象 ord_info
 * 
 * @author share
 * @date 2025-04-09
 */
public class OrdInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date overTime;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private Long expressId;

    /** 租用时间 */
    @Excel(name = "租用时间")
    private Long time;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 价格 */
    @Excel(name = "价格")
    private String price;

    /** 商家 */
    @Excel(name = "商家")
    private String busName;

    /** 用户返回单号
 */
    @Excel(name = "用户返回单号")
    private Long userExpressId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setGoodId(Long goodId) 
    {
        this.goodId = goodId;
    }

    public Long getGoodId() 
    {
        return goodId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBuildTime(Date buildTime) 
    {
        this.buildTime = buildTime;
    }

    public Date getBuildTime() 
    {
        return buildTime;
    }
    public void setOverTime(Date overTime) 
    {
        this.overTime = overTime;
    }

    public Date getOverTime() 
    {
        return overTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setExpressId(Long expressId) 
    {
        this.expressId = expressId;
    }

    public Long getExpressId() 
    {
        return expressId;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setBusName(String busName) 
    {
        this.busName = busName;
    }

    public String getBusName() 
    {
        return busName;
    }
    public void setUserExpressId(Long userExpressId) 
    {
        this.userExpressId = userExpressId;
    }

    public Long getUserExpressId() 
    {
        return userExpressId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("goodId", getGoodId())
            .append("userId", getUserId())
            .append("buildTime", getBuildTime())
            .append("overTime", getOverTime())
            .append("status", getStatus())
            .append("sendTime", getSendTime())
            .append("expressId", getExpressId())
            .append("time", getTime())
            .append("address", getAddress())
            .append("price", getPrice())
            .append("busName", getBusName())
            .append("userExpressId", getUserExpressId())
            .toString();
    }
}
