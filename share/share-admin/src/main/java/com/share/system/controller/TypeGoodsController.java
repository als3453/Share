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
import com.share.system.domain.TypeGoods;
import com.share.system.service.ITypeGoodsService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 商品种类表Controller
 * 
 * @author share
 * @date 2025-02-17
 */
@RestController
@RequestMapping("/system/goodTpye")
public class TypeGoodsController extends BaseController
{
    @Autowired
    private ITypeGoodsService typeGoodsService;

    /**
     * 查询商品种类表列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:list')")
    @GetMapping("/list")
    public TableDataInfo list(TypeGoods typeGoods)
    {
        startPage();
        List<TypeGoods> list = typeGoodsService.selectTypeGoodsList(typeGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品种类表列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:export')")
    @Log(title = "商品种类表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TypeGoods typeGoods)
    {
        List<TypeGoods> list = typeGoodsService.selectTypeGoodsList(typeGoods);
        ExcelUtil<TypeGoods> util = new ExcelUtil<TypeGoods>(TypeGoods.class);
        util.exportExcel(response, list, "商品种类表数据");
    }

    /**
     * 获取商品种类表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(typeGoodsService.selectTypeGoodsById(id));
    }

    /**
     * 新增商品种类表
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:add')")
    @Log(title = "商品种类表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TypeGoods typeGoods)
    {
        return toAjax(typeGoodsService.insertTypeGoods(typeGoods));
    }

    /**
     * 修改商品种类表
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:edit')")
    @Log(title = "商品种类表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TypeGoods typeGoods)
    {
        return toAjax(typeGoodsService.updateTypeGoods(typeGoods));
    }

    /**
     * 删除商品种类表
     */
    @PreAuthorize("@ss.hasPermi('system:goodTpye:remove')")
    @Log(title = "商品种类表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(typeGoodsService.deleteTypeGoodsByIds(ids));
    }
}
