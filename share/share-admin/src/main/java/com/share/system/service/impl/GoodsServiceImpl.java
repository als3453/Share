package com.share.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.GoodsMapper;
import com.share.system.domain.Goods;
import com.share.system.service.IGoodsService;

/**
 * 商品信息表Service业务层处理
 * 
 * @author share
 * @date 2025-02-19
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品信息表
     * 
     * @param id 商品信息表主键
     * @return 商品信息表
     */
    @Override
    public Goods selectGoodsById(String id)
    {
        return goodsMapper.selectGoodsById(id);
    }

    /**
     * 查询商品信息表列表
     * 
     * @param goods 商品信息表
     * @return 商品信息表
     */
    @Override
    public List<Goods> selectGoodsList(Goods goods)
    {
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 新增商品信息表
     * 
     * @param goods 商品信息表
     * @return 结果
     */
    @Override
    public int insertGoods(Goods goods)
    {
        return goodsMapper.insertGoods(goods);
    }

    /**
     * 修改商品信息表
     * 
     * @param goods 商品信息表
     * @return 结果
     */
    @Override
    public int updateGoods(Goods goods)
    {
        return goodsMapper.updateGoods(goods);
    }

    /**
     * 批量删除商品信息表
     * 
     * @param ids 需要删除的商品信息表主键
     * @return 结果
     */
    @Override
    public int deleteGoodsByIds(String[] ids)
    {
        return goodsMapper.deleteGoodsByIds(ids);
    }

    /**
     * 删除商品信息表信息
     * 
     * @param id 商品信息表主键
     * @return 结果
     */
    @Override
    public int deleteGoodsById(String id)
    {
        return goodsMapper.deleteGoodsById(id);
    }
}
