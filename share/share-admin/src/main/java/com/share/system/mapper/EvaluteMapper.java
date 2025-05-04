package com.share.system.mapper;

import java.util.List;
import com.share.system.domain.Evalute;

/**
 * 评价信息表Mapper接口
 * 
 * @author share
 * @date 2025-02-23
 */
public interface EvaluteMapper 
{
    /**
     * 查询评价信息表
     * 
     * @param id 评价信息表主键
     * @return 评价信息表
     */
    public Evalute selectEvaluteById(String id);

    /**
     * 查询评价信息表列表
     * 
     * @param evalute 评价信息表
     * @return 评价信息表集合
     */
    public List<Evalute> selectEvaluteList(Evalute evalute);

    /**
     * 新增评价信息表
     * 
     * @param evalute 评价信息表
     * @return 结果
     */
    public int insertEvalute(Evalute evalute);

    /**
     * 修改评价信息表
     * 
     * @param evalute 评价信息表
     * @return 结果
     */
    public int updateEvalute(Evalute evalute);

    /**
     * 删除评价信息表
     * 
     * @param id 评价信息表主键
     * @return 结果
     */
    public int deleteEvaluteById(String id);

    /**
     * 批量删除评价信息表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEvaluteByIds(String[] ids);
}
