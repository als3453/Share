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
import com.share.system.domain.Address;
import com.share.system.service.IAddressService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 地址Controller
 * 
 * @author share
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/system/address")
public class AddressController extends BaseController
{
    @Autowired
    private IAddressService addressService;

    /**
     * 查询地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(Address address)
    {
        startPage();
        List<Address> list = addressService.selectAddressList(address);
        return getDataTable(list);
    }

    /**
     * 导出地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:export')")
    @Log(title = "地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Address address)
    {
        List<Address> list = addressService.selectAddressList(address);
        ExcelUtil<Address> util = new ExcelUtil<Address>(Address.class);
        util.exportExcel(response, list, "地址数据");
    }

    /**
     * 获取地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:address:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return success(addressService.selectAddressByAddressId(addressId));
    }

    /**
     * 新增地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:add')")
    @Log(title = "地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Address address)
    {
        return toAjax(addressService.insertAddress(address));
    }

    /**
     * 修改地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:edit')")
    @Log(title = "地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Address address)
    {
        return toAjax(addressService.updateAddress(address));
    }

    /**
     * 删除地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:remove')")
    @Log(title = "地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(addressService.deleteAddressByAddressIds(addressIds));
    }
}
