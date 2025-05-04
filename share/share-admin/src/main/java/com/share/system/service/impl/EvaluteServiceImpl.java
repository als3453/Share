package com.share.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.EvaluteMapper;
import com.share.system.domain.Evalute;
import com.share.system.service.IEvaluteService;

/**
 * 评价信息表Service业务层处理
 * 
 * @author share
 * @date 2025-02-23
 */
@Service
public class EvaluteServiceImpl implements IEvaluteService 
{
    @Autowired
    private EvaluteMapper evaluteMapper;

    /**
     * 查询评价信息表
     * 
     * @param id 评价信息表主键
     * @return 评价信息表
     */
    @Override
    public Evalute selectEvaluteById(String id)
    {
        return evaluteMapper.selectEvaluteById(id);
    }

    /**
     * 查询评价信息表列表
     * 
     * @param evalute 评价信息表
     * @return 评价信息表
     */
    @Override
    public List<Evalute> selectEvaluteList(Evalute evalute)
    {
        return evaluteMapper.selectEvaluteList(evalute);
    }

    /**
     * 新增评价信息表
     * 
     * @param evalute 评价信息表
     * @return 结果
     */
    @Override
    public int insertEvalute(Evalute evalute)
    {
        return evaluteMapper.insertEvalute(evalute);
    }

    /**
     * 修改评价信息表
     * 
     * @param evalute 评价信息表
     * @return 结果
     */
    @Override
    public int updateEvalute(Evalute evalute)
    {
        return evaluteMapper.updateEvalute(evalute);
    }

    /**
     * 批量删除评价信息表
     * 
     * @param ids 需要删除的评价信息表主键
     * @return 结果
     */
    @Override
    public int deleteEvaluteByIds(String[] ids)
    {
        return evaluteMapper.deleteEvaluteByIds(ids);
    }

    /**
     * 删除评价信息表信息
     * 
     * @param id 评价信息表主键
     * @return 结果
     */
    @Override
    public int deleteEvaluteById(String id)
    {
        return evaluteMapper.deleteEvaluteById(id);
    }
}
