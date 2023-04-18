package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.base.AppResponseBase;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.dto.UserLoginReqDTO;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录的处理controller，包括从企业微信管理后台和手机端小程序两个访问入口
 *
 * @author lck
 * @date 2020-03-16 15:36
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/user")
@Api(value = "用户登录控制器", produces = "application/json", tags = {"用户登录"})
public class LoginController extends BaseController {

    @Resource
    private UserService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseBase<UserInfo> login(@RequestBody UserLoginReqDTO reqDTO) {
        log.debug("用户登录，reqDTO={}", reqDTO);
        UserInfo login = loginService.login(reqDTO.getUsername(), reqDTO.getPassword());
        return super.generateSuccess(login);
    }

    @ApiOperation("用户登录")
    @PostMapping("/appLogin")
    public AppResponseBase<UserInfo> appLogin(@RequestBody UserLoginReqDTO reqDTO) {
        log.debug("用户登录，reqDTO={}", reqDTO);
        UserInfo login = loginService.login(reqDTO.getUsername(), reqDTO.getPassword());
        AppResponseBase<UserInfo> base = AppResponseBase.success();
        base.setData(login);
        return base;
    }
}