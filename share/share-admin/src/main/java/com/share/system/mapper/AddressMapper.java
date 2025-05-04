package com.share.system.mapper;

import java.util.List;
import com.share.system.domain.Address;

/**
 * 地址Mapper接口
 * 
 * @author share
 * @date 2025-04-16
 */
public interface AddressMapper 
{
    /**
     * 查询地址
     * 
     * @param addressId 地址主键
     * @return 地址
     */
    public Address selectAddressByAddressId(Long addressId);

    /**
     * 查询地址列表
     * 
     * @param address 地址
     * @return 地址集合
     */
    public List<Address> selectAddressList(Address address);

    /**
     * 新增地址
     * 
     * @param address 地址
     * @return 结果
     */
    public int insertAddress(Address address);

    /**
     * 修改地址
     * 
     * @param address 地址
     * @return 结果
     */
    public int updateAddress(Address address);

    /**
     * 删除地址
     * 
     * @param addressId 地址主键
     * @return 结果
     */
    public int deleteAddressByAddressId(Long addressId);

    /**
     * 批量删除地址
     * 
     * @param addressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAddressByAddressIds(Long[] addressIds);
}
