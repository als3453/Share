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
import com.share.system.domain.OrdInfo;
import com.share.system.service.IOrdInfoService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author share
 * @date 2025-04-09
 */
@RestController
@RequestMapping("/system/info")
public class OrdInfoController extends BaseController
{
    @Autowired
    private IOrdInfoService ordInfoService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrdInfo ordInfo)
    {
        startPage();
        List<OrdInfo> list = ordInfoService.selectOrdInfoList(ordInfo);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrdInfo ordInfo)
    {
        List<OrdInfo> list = ordInfoService.selectOrdInfoList(ordInfo);
        ExcelUtil<OrdInfo> util = new ExcelUtil<OrdInfo>(OrdInfo.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(ordInfoService.selectOrdInfoById(id));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrdInfo ordInfo)
    {
        return toAjax(ordInfoService.insertOrdInfo(ordInfo));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrdInfo ordInfo)
    {
        return toAjax(ordInfoService.updateOrdInfo(ordInfo));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ordInfoService.deleteOrdInfoByIds(ids));
    }
}
