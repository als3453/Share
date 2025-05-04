package com.share.system.service;

import java.util.List;
import com.share.system.domain.Goods;

/**
 * 商品信息表Service接口
 * 
 * @author share
 * @date 2025-02-19
 */
public interface IGoodsService 
{
    /**
     * 查询商品信息表
     * 
     * @param id 商品信息表主键
     * @return 商品信息表
     */
    public Goods selectGoodsById(String id);

    /**
     * 查询商品信息表列表
     * 
     * @param goods 商品信息表
     * @return 商品信息表集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品信息表
     * 
     * @param goods 商品信息表
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品信息表
     * 
     * @param goods 商品信息表
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 批量删除商品信息表
     * 
     * @param ids 需要删除的商品信息表主键集合
     * @return 结果
     */
    public int deleteGoodsByIds(String[] ids);

    /**
     * 删除商品信息表信息
     * 
     * @param id 商品信息表主键
     * @return 结果
     */
    public int deleteGoodsById(String id);
}
