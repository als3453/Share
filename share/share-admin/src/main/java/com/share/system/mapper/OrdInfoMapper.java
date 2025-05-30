package com.share.system.mapper;

import java.util.List;
import com.share.system.domain.OrdInfo;

/**
 * 订单管理Mapper接口
 * 
 * @author share
 * @date 2025-04-09
 */
public interface OrdInfoMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    public OrdInfo selectOrdInfoById(String id);

    /**
     * 查询订单管理列表
     * 
     * @param ordInfo 订单管理
     * @return 订单管理集合
     */
    public List<OrdInfo> selectOrdInfoList(OrdInfo ordInfo);

    /**
     * 新增订单管理
     * 
     * @param ordInfo 订单管理
     * @return 结果
     */
    public int insertOrdInfo(OrdInfo ordInfo);

    /**
     * 修改订单管理
     * 
     * @param ordInfo 订单管理
     * @return 结果
     */
    public int updateOrdInfo(OrdInfo ordInfo);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteOrdInfoById(String id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrdInfoByIds(String[] ids);
}
