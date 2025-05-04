package com.share.system.mapper;

import java.util.List;
import com.share.system.domain.TypeGoods;

/**
 * 商品种类表Mapper接口
 * 
 * @author share
 * @date 2025-02-17
 */
public interface TypeGoodsMapper 
{
    /**
     * 查询商品种类表
     * 
     * @param id 商品种类表主键
     * @return 商品种类表
     */
    public TypeGoods selectTypeGoodsById(String id);

    /**
     * 查询商品种类表列表
     * 
     * @param typeGoods 商品种类表
     * @return 商品种类表集合
     */
    public List<TypeGoods> selectTypeGoodsList(TypeGoods typeGoods);

    /**
     * 新增商品种类表
     * 
     * @param typeGoods 商品种类表
     * @return 结果
     */
    public int insertTypeGoods(TypeGoods typeGoods);

    /**
     * 修改商品种类表
     * 
     * @param typeGoods 商品种类表
     * @return 结果
     */
    public int updateTypeGoods(TypeGoods typeGoods);

    /**
     * 删除商品种类表
     * 
     * @param id 商品种类表主键
     * @return 结果
     */
    public int deleteTypeGoodsById(String id);

    /**
     * 批量删除商品种类表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTypeGoodsByIds(String[] ids);
}
