package com.share.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.OrdInfoMapper;
import com.share.system.domain.OrdInfo;
import com.share.system.service.IOrdInfoService;

/**
 * 订单管理Service业务层处理
 * 
 * @author share
 * @date 2025-04-09
 */
@Service
public class OrdInfoServiceImpl implements IOrdInfoService 
{
    @Autowired
    private OrdInfoMapper ordInfoMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public OrdInfo selectOrdInfoById(String id)
    {
        return ordInfoMapper.selectOrdInfoById(id);
    }

    /**
     * 查询订单管理列表
     * 
     * @param ordInfo 订单管理
     * @return 订单管理
     */
    @Override
    public List<OrdInfo> selectOrdInfoList(OrdInfo ordInfo)
    {
        return ordInfoMapper.selectOrdInfoList(ordInfo);
    }

    /**
     * 新增订单管理
     * 
     * @param ordInfo 订单管理
     * @return 结果
     */
    @Override
    public int insertOrdInfo(OrdInfo ordInfo)
    {
        return ordInfoMapper.insertOrdInfo(ordInfo);
    }

    /**
     * 修改订单管理
     * 
     * @param ordInfo 订单管理
     * @return 结果
     */
    @Override
    public int updateOrdInfo(OrdInfo ordInfo)
    {
        return ordInfoMapper.updateOrdInfo(ordInfo);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrdInfoByIds(String[] ids)
    {
        return ordInfoMapper.deleteOrdInfoByIds(ids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrdInfoById(String id)
    {
        return ordInfoMapper.deleteOrdInfoById(id);
    }
}
