package com.share.system.service;

import java.util.List;
import com.share.system.domain.Business;

/**
 * 商家信息表Service接口
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
public interface IBusinessService 
{
    /**
     * 查询商家信息表
     * 
     * @param busId 商家信息表主键
     * @return 商家信息表
     */
    public Business selectBusinessByBusId(String busId);

    /**
     * 查询商家信息表列表
     * 
     * @param business 商家信息表
     * @return 商家信息表集合
     */
    public List<Business> selectBusinessList(Business business);

    /**
     * 新增商家信息表
     * 
     * @param business 商家信息表
     * @return 结果
     */
    public int insertBusiness(Business business);

    /**
     * 修改商家信息表
     * 
     * @param business 商家信息表
     * @return 结果
     */
    public int updateBusiness(Business business);

    /**
     * 批量删除商家信息表
     * 
     * @param busIds 需要删除的商家信息表主键集合
     * @return 结果
     */
    public int deleteBusinessByBusIds(String[] busIds);

    /**
     * 删除商家信息表信息
     * 
     * @param busId 商家信息表主键
     * @return 结果
     */
    public int deleteBusinessByBusId(String busId);
}
