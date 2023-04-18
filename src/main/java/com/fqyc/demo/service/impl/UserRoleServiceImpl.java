package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.entity.UserRole;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.repository.UserInfoRepository;
import com.fqyc.demo.repository.UserRoleRepository;
import com.fqyc.demo.service.UserRoleService;
import com.fqyc.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lck
 * @date 2020-03-16 16:13
 * @since 1.0
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleRepository, UserRole> implements UserRoleService {

    @Override
    public UserRole getRoleInfoByCode(String roleCode) {
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(UserRole::getRoleCode, roleCode);
        UserRole userRole = this.baseMapper.selectOne(queryWrapper);
        return userRole;
    }
}
