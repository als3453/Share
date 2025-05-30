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
import com.share.system.domain.Customer;
import com.share.system.service.ICustomerService;
import com.share.common.utils.poi.ExcelUtil;
import com.share.common.core.page.TableDataInfo;

/**
 * 用户信息表Controller
 * 
 * @author ruoyi
 * @date 2025-02-09
 */
@RestController
@RequestMapping("/system/customer")
public class CustomerController extends BaseController
{
    @Autowired
    private ICustomerService customerService;


    /**
     * 查询用户信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 导出用户信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "用户信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        util.exportExcel(response, list, "用户信息表数据");
    }

    /**
     * 获取用户信息表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/{cusId}")
    public AjaxResult getInfo(@PathVariable("cusId") String cusId)
    {
        return success(customerService.selectCustomerByCusId(cusId));
    }

    /**
     * 新增用户信息表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "用户信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Customer customer)
    {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改用户信息表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "用户信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Customer customer)
    {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除用户信息表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "用户信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cusIds}")
    public AjaxResult remove(@PathVariable String[] cusIds)
    {
        return toAjax(customerService.deleteCustomerByCusIds(cusIds));
    }
}
