package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.UserInfoPageQueryReqDTO;
import com.fqyc.demo.dto.UserInfoReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.UserInfo;

/**
 * 首页访问Service
 *
 * @author lck
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface UserService extends IService<UserInfo> {
    /**
     * 从app登录
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username, String password);

    /**
     * 获取登录用户信息
     * @param userId 用户ID
     * @return UserInfoDTO，没有获取到则抛出异常
     */
    UserInfo getLoginUserInfo(Integer userId);

    Boolean addOrUpdate(UserInfoReqDTO reqDTO);

    Boolean deleteUser(Integer id);

    PageDTO<UserInfo> pageQuery(UserInfoPageQueryReqDTO reqDTO);
}
