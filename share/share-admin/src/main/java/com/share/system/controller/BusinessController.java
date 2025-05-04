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
import com.share.system.domain.Business;
import com.share.system.service.IBusinessService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 商家信息表Controller
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
@RestController
@RequestMapping("/system/business")
public class BusinessController extends BaseController
{
    @Autowired
    private IBusinessService businessService;

    /**
     * 查询商家信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:business:list')")
    @GetMapping("/list")
    public TableDataInfo list(Business business)
    {
        startPage();
        List<Business> list = businessService.selectBusinessList(business);
        return getDataTable(list);
    }

    /**
     * 导出商家信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:business:export')")
    @Log(title = "商家信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Business business)
    {
        List<Business> list = businessService.selectBusinessList(business);
        ExcelUtil<Business> util = new ExcelUtil<Business>(Business.class);
        util.exportExcel(response, list, "商家信息表数据");
    }

    /**
     * 获取商家信息表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:business:query')")
    @GetMapping(value = "/{busId}")
    public AjaxResult getInfo(@PathVariable("busId") String busId)
    {
        return success(businessService.selectBusinessByBusId(busId));
    }

    /**
     * 新增商家信息表
     */
    @PreAuthorize("@ss.hasPermi('system:business:add')")
    @Log(title = "商家信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Business business)
    {
        return toAjax(businessService.insertBusiness(business));
    }

    /**
     * 修改商家信息表
     */
    @PreAuthorize("@ss.hasPermi('system:business:edit')")
    @Log(title = "商家信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Business business)
    {
        return toAjax(businessService.updateBusiness(business));
    }

    /**
     * 删除商家信息表
     */
    @PreAuthorize("@ss.hasPermi('system:business:remove')")
    @Log(title = "商家信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busIds}")
    public AjaxResult remove(@PathVariable String[] busIds)
    {
        return toAjax(businessService.deleteBusinessByBusIds(busIds));
    }
}
