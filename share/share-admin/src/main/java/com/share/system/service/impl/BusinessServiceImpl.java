package com.share.system.service.impl;

import java.util.List;

import com.share.common.constant.UserConstants;
import com.share.common.core.domain.entity.SysUser;
import com.share.common.exception.ServiceException;
import com.share.common.utils.SecurityUtils;
import com.share.common.utils.StringUtils;
import com.share.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.share.system.mapper.BusinessMapper;
import com.share.system.domain.Business;
import com.share.system.service.IBusinessService;

/**
 * 商家信息表Service业务层处理
 *
 * @author ruoyi
 * @date 2025-02-09
 */

@Service
public class BusinessServiceImpl implements IBusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private ISysUserService userService;

    //同步新增usr表
    private void registerBusiness(Business business) {
        String username = business.getBusPhone(), password = business.getPassword();
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

            sysUser.setPhonenumber(business.getBusPhone());
            sysUser.setNickName(business.getBusName());
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            userService.registerUser(sysUser);

            userService.insertUserRole(sysUser.getUserId(), new Long[]{100L});


        }
    }

    //同步修改usr表
    private void upBusiness(Business business) {
        String username = business.getBusPhone(), password = business.getPassword();
        SysUser sysUser =  userService.selectUserByUserName(business.getBusPhone());
        System.out.println(sysUser.toString());
        sysUser.setUserName(username);
        sysUser.setPhonenumber(business.getBusPhone());
        sysUser.setNickName(business.getBusName());
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        userService.updateUser(sysUser);

    }

    //同步删除usr表
    private void deBusiness(String businessId) {
        System.out.println();

        System.out.println("id是" + businessId);
        Business business = selectBusinessByBusId(businessId);
        System.out.println(business.toString());
        System.out.println("找到了");
        SysUser sysUserID = userService.selectUserByUserName(business.getBusPhone());
        System.out.println(sysUserID.toString());

        userService.deleteUserById(sysUserID.getUserId());
    }


    /**
     * 查询商家信息表
     *
     * @param busId 商家信息表主键
     * @return 商家信息表
     */
    @Override
    public Business selectBusinessByBusId(String busId) {
        return businessMapper.selectBusinessByBusId(busId);
    }

    /**
     * 查询商家信息表列表
     *
     * @param business 商家信息表
     * @return 商家信息表
     */
    @Override
    public List<Business> selectBusinessList(Business business) {
        return businessMapper.selectBusinessList(business);
    }

    /**
     * 新增商家信息表
     *
     * @param business 商家信息表
     * @return 结果
     */
    @Override
    public int insertBusiness(Business business) {

        System.out.println("新增商家成功");
        return businessMapper.insertBusiness(business);
    }

    /**
     * 修改商家信息表
     *
     * @param business 商家信息表
     * @return 结果
     */
    @Override
    public int updateBusiness(Business business) {
        upBusiness(business);
        return businessMapper.updateBusiness(business);
    }

    /**
     * 批量删除商家信息表
     *
     * @param busIds 需要删除的商家信息表主键
     * @return 结果
     */
    @Override
    public int deleteBusinessByBusIds(String[] busIds) {
        System.out.println("sahnchuzzzz1");
        for (int i = 0; i < busIds.length; i++) {
            deBusiness(busIds[i]);
        }
        return businessMapper.deleteBusinessByBusIds(busIds);
    }

    /**
     * 删除商家信息表信息
     *
     * @param busId 商家信息表主键
     * @return 结果
     */
    @Override
    public int deleteBusinessByBusId(String busId) {
        System.out.println("sahnchuzzzz");
        System.out.println("这是商家的id" + busId);
        System.out.println(busId);
        deBusiness(busId);
        return businessMapper.deleteBusinessByBusId(busId);
    }
}
