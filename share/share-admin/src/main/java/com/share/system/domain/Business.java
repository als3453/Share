package com.share.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 商家信息表对象 business
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
public class Business extends BaseEntity
{


    private static final long serialVersionUID = 1L;

    /** 商家id */
    private String busId;

    /** 商家姓名 */
    @Excel(name = "商家姓名")
    private String busName;

    /** 性别 */
    @Excel(name = "性别")
    private Integer busSex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String busPhone;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    public void setBusId(String busId) 
    {
        this.busId = busId;
    }

    public String getBusId() 
    {
        return busId;
    }
    public void setBusName(String busName) 
    {
        this.busName = busName;
    }

    public String getBusName() 
    {
        return busName;
    }
    public void setBusSex(Integer busSex) 
    {
        this.busSex = busSex;
    }

    public Integer getBusSex() 
    {
        return busSex;
    }
    public void setBusPhone(String busPhone) 
    {
        this.busPhone = busPhone;
    }

    public String getBusPhone() 
    {
        return busPhone;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("busId", getBusId())
            .append("busName", getBusName())
            .append("busSex", getBusSex())
            .append("busPhone", getBusPhone())
            .append("password", getPassword())
            .toString();
    }
}
