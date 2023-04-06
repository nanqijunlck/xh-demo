package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.dto.UserInfoPageQueryReqDTO;
import com.fqyc.demo.dto.UserInfoReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.SaleOrder;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.repository.UserInfoRepository;
import com.fqyc.demo.service.UserService;
import com.fqyc.demo.util.ModelConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author panyi
 * @date 2020-03-16 16:13
 * @since 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserInfoRepository, UserInfo> implements UserService {

    @Override
    public UserInfo login(String username, String password) {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getUsername, username);
        lambdaQueryWrapper.eq(UserInfo::getPassword, password);
        UserInfo userInfo = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(userInfo)) {
            throw new BizException(ExceptionCodeConstants.ARGUMENT_NULL, "用户名或密码错误");
        }
        return userInfo;
    }

    @Override
    public UserInfo getLoginUserInfo(Integer userId) {
        UserInfo userInfo = this.baseMapper.selectById(userId);
        if (Objects.isNull(userInfo)) {
            throw new BizException(ExceptionCodeConstants.ARGUMENT_NULL, "用户不存在");
        }
        return userInfo;
    }

    @Override
    public Boolean addOrUpdate(UserInfoReqDTO reqDTO) {
        UserInfo userInfo = ModelConvertUtils.convert(reqDTO, UserInfo.class);
        return this.saveOrUpdate(userInfo);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return this.baseMapper.deleteById(id) > 0;
    }

    @Override
    public PageDTO<UserInfo> pageQuery(UserInfoPageQueryReqDTO reqDTO) {
        Page page = new Page(reqDTO.getCurrentPage(), reqDTO.getPageSize());
        LambdaQueryWrapper<UserInfo> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(UserInfo::getUpdateTime);
        if (StringUtils.isNotEmpty(reqDTO.getUsername())) {
            queryWrapper.like(UserInfo::getUsername, reqDTO.getUsername());
        }
        if (StringUtils.isNotEmpty(reqDTO.getRoleCode())) {
            queryWrapper.eq(UserInfo::getRoleCode, reqDTO.getRoleCode());
        }
        if (StringUtils.isNotEmpty(reqDTO.getPhone())) {
            queryWrapper.eq(UserInfo::getPhone, reqDTO.getPhone());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, UserInfo.class);
    }
}
