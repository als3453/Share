package com.share.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.TypeGoodsMapper;
import com.share.system.domain.TypeGoods;
import com.share.system.service.ITypeGoodsService;

/**
 * 商品种类表Service业务层处理
 * 
 * @author share
 * @date 2025-02-17
 */
@Service
public class TypeGoodsServiceImpl implements ITypeGoodsService 
{
    @Autowired
    private TypeGoodsMapper typeGoodsMapper;

    /**
     * 查询商品种类表
     * 
     * @param id 商品种类表主键
     * @return 商品种类表
     */
    @Override
    public TypeGoods selectTypeGoodsById(String id)
    {
        return typeGoodsMapper.selectTypeGoodsById(id);
    }

    /**
     * 查询商品种类表列表
     * 
     * @param typeGoods 商品种类表
     * @return 商品种类表
     */
    @Override
    public List<TypeGoods> selectTypeGoodsList(TypeGoods typeGoods)
    {
        return typeGoodsMapper.selectTypeGoodsList(typeGoods);
    }

    /**
     * 新增商品种类表
     * 
     * @param typeGoods 商品种类表
     * @return 结果
     */
    @Override
    public int insertTypeGoods(TypeGoods typeGoods)
    {
        return typeGoodsMapper.insertTypeGoods(typeGoods);
    }

    /**
     * 修改商品种类表
     * 
     * @param typeGoods 商品种类表
     * @return 结果
     */
    @Override
    public int updateTypeGoods(TypeGoods typeGoods)
    {
        return typeGoodsMapper.updateTypeGoods(typeGoods);
    }

    /**
     * 批量删除商品种类表
     * 
     * @param ids 需要删除的商品种类表主键
     * @return 结果
     */
    @Override
    public int deleteTypeGoodsByIds(String[] ids)
    {
        return typeGoodsMapper.deleteTypeGoodsByIds(ids);
    }

    /**
     * 删除商品种类表信息
     * 
     * @param id 商品种类表主键
     * @return 结果
     */
    @Override
    public int deleteTypeGoodsById(String id)
    {
        return typeGoodsMapper.deleteTypeGoodsById(id);
    }
}
