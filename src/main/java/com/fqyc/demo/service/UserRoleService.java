package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.entity.UserRole;

/**
 * 首页访问Service
 *
 * @author lck
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface UserRoleService extends IService<UserRole> {

    UserRole getRoleInfoByCode(String roleCode);
}
