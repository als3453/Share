package com.share.system.mapper;

import java.util.List;
import com.share.system.domain.Customer;

/**
 * 用户信息表Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
public interface CustomerMapper 
{
    /**
     * 查询用户信息表
     * 
     * @param cusId 用户信息表主键
     * @return 用户信息表
     */
    public Customer selectCustomerByCusId(String cusId);

    /**
     * 查询用户信息表列表
     * 
     * @param customer 用户信息表
     * @return 用户信息表集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增用户信息表
     * 
     * @param customer 用户信息表
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改用户信息表
     * 
     * @param customer 用户信息表
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除用户信息表
     * 
     * @param cusId 用户信息表主键
     * @return 结果
     */
    public int deleteCustomerByCusId(String cusId);

    /**
     * 批量删除用户信息表
     * 
     * @param cusIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByCusIds(String[] cusIds);
}
