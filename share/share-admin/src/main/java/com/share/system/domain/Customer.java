package com.share.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.share.common.annotation.Excel;
import com.share.common.core.domain.BaseEntity;

/**
 * 用户信息表对象 customer
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
public class Customer extends BaseEntity
{


    private static final long serialVersionUID = 1L;

    /** id */
    private String cusId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    public void setCusId(String cusId) 
    {
        this.cusId = cusId;
    }

    public String getCusId() 
    {
        return cusId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cusId", getCusId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("password", getPassword())
            .append("sex", getSex())
            .toString();
    }
}
