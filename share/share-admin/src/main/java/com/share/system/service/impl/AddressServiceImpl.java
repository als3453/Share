package com.share.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.AddressMapper;
import com.share.system.domain.Address;
import com.share.system.service.IAddressService;

/**
 * 地址Service业务层处理
 * 
 * @author share
 * @date 2025-04-16
 */
@Service
public class AddressServiceImpl implements IAddressService 
{
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询地址
     * 
     * @param addressId 地址主键
     * @return 地址
     */
    @Override
    public Address selectAddressByAddressId(Long addressId)
    {
        return addressMapper.selectAddressByAddressId(addressId);
    }

    /**
     * 查询地址列表
     * 
     * @param address 地址
     * @return 地址
     */
    @Override
    public List<Address> selectAddressList(Address address)
    {
        return addressMapper.selectAddressList(address);
    }

    /**
     * 新增地址
     * 
     * @param address 地址
     * @return 结果
     */
    @Override
    public int insertAddress(Address address)
    {
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改地址
     * 
     * @param address 地址
     * @return 结果
     */
    @Override
    public int updateAddress(Address address)
    {
        return addressMapper.updateAddress(address);
    }

    /**
     * 批量删除地址
     * 
     * @param addressIds 需要删除的地址主键
     * @return 结果
     */
    @Override
    public int deleteAddressByAddressIds(Long[] addressIds)
    {
        return addressMapper.deleteAddressByAddressIds(addressIds);
    }

    /**
     * 删除地址信息
     * 
     * @param addressId 地址主键
     * @return 结果
     */
    @Override
    public int deleteAddressByAddressId(Long addressId)
    {
        return addressMapper.deleteAddressByAddressId(addressId);
    }
}
