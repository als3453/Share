package com.share.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.share.common.annotation.Log;
import com.share.common.core.controller.BaseController;
import com.share.common.core.domain.AjaxResult;
import com.share.common.enums.BusinessType;
import com.share.system.domain.Evalute;
import com.share.system.service.IEvaluteService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 评价信息表Controller
 * 
 * @author share
 * @date 2025-02-23
 */
@RestController
@RequestMapping("/system/evalute")
public class EvaluteController extends BaseController
{
    @Autowired
    private IEvaluteService evaluteService;

    /**
     * 查询评价信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:list')")
    @GetMapping("/list")
    public TableDataInfo list(Evalute evalute)
    {
        startPage();
        List<Evalute> list = evaluteService.selectEvaluteList(evalute);
        return getDataTable(list);
    }

    /**
     * 导出评价信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:export')")
    @Log(title = "评价信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Evalute evalute)
    {
        List<Evalute> list = evaluteService.selectEvaluteList(evalute);
        ExcelUtil<Evalute> util = new ExcelUtil<Evalute>(Evalute.class);
        util.exportExcel(response, list, "评价信息表数据");
    }

    /**
     * 获取评价信息表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(evaluteService.selectEvaluteById(id));
    }

    /**
     * 新增评价信息表
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:add')")
    @Log(title = "评价信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Evalute evalute)
    {
        return toAjax(evaluteService.insertEvalute(evalute));
    }

    /**
     * 修改评价信息表
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:edit')")
    @Log(title = "评价信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Evalute evalute)
    {
        return toAjax(evaluteService.updateEvalute(evalute));
    }

    /**
     * 删除评价信息表
     */
    @PreAuthorize("@ss.hasPermi('system:evalute:remove')")
    @Log(title = "评价信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(evaluteService.deleteEvaluteByIds(ids));
    }
}
