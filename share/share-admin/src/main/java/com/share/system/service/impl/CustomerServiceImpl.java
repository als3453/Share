package com.share.system.service.impl;

import java.util.List;

import com.share.common.constant.UserConstants;
import com.share.common.core.domain.entity.SysUser;
import com.share.common.exception.ServiceException;
import com.share.common.utils.SecurityUtils;
import com.share.common.utils.StringUtils;
import com.share.system.domain.Business;
import com.share.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.CustomerMapper;
import com.share.system.domain.Customer;
import com.share.system.service.ICustomerService;

/**
 * 用户信息表Service业务层处理
 *
 * @author ruoyi
 * @date 2025-02-09
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ISysUserService userService;


    //同步新增usr表
    private void register(Customer business) {
        String username = business.getPhone(), password = business.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            throw new ServiceException("用户密码不能为空");
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        } else if (!userService.checkUserNameUnique(sysUser)) {
            throw new ServiceException("用户已存在");
        } else {

            sysUser.setPhonenumber(business.getPhone());
            sysUser.setNickName(business.getName());
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            userService.registerUser(sysUser);
            userService.insertUserRole(sysUser.getUserId().longValue(), new Long[]{2L});

        }
    }

    //同步修改usr表
    private void up(Customer business) {
        String username = business.getPhone(), password = business.getPassword();
        SysUser sysUser = userService.selectUserByUserName(business.getPhone());
        System.out.println(sysUser.toString());
        sysUser.setUserName(username);
        sysUser.setPhonenumber(business.getPhone());
        sysUser.setNickName(business.getName());
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        userService.updateUser(sysUser);

    }

    //同步删除usr表
    private void de(String businessId) {


        Customer business = selectCustomerByCusId(businessId);
        System.out.println(business.toString());
        SysUser sysUserID = userService.selectUserByUserName(business.getPhone());
        System.out.println(sysUserID.toString());
        userService.deleteUserById(sysUserID.getUserId());
    }


    /**
     * 查询用户信息表
     *
     * @param cusId 用户信息表主键
     * @return 用户信息表
     */
    @Override
    public Customer selectCustomerByCusId(String cusId) {
        return customerMapper.selectCustomerByCusId(cusId);
    }

    /**
     * 查询用户信息表列表
     *
     * @param customer 用户信息表
     * @return 用户信息表
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增用户信息表
     *
     * @param customer 用户信息表
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer) {
        register(customer);
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改用户信息表
     *
     * @param customer 用户信息表
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer) {
        up(customer);
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除用户信息表
     *
     * @param cusIds 需要删除的用户信息表主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCusIds(String[] cusIds) {
        for (String cusId : cusIds) {
            de(cusId);
        }
        return customerMapper.deleteCustomerByCusIds(cusIds);
    }

    /**
     * 删除用户信息表信息
     *
     * @param cusId 用户信息表主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCusId(String cusId) {
        de(cusId);
        return customerMapper.deleteCustomerByCusId(cusId);
    }
}
