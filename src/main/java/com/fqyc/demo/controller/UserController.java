package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.UserInfoPageQueryReqDTO;
import com.fqyc.demo.dto.UserInfoReqDTO;
import com.fqyc.demo.dto.UserLoginReqDTO;
import com.fqyc.demo.dto.base.AppResponseBase;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "用户控制器", produces = "application/json", tags = {"用户管理"})
public class UserController extends BaseController {

    @Resource
    private UserService loginService;

    @ApiOperation("add/update用户")
    @PostMapping("/addOrUpdate")
    public ResponseBase<Boolean> addOrUpdate(@RequestBody UserInfoReqDTO reqDTO) {
        log.debug("addOrUpdate 用户，requestDTO={}", reqDTO);
        Boolean aBoolean = loginService.addOrUpdate(reqDTO);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("删除")
    @GetMapping("/deleteUser")
    public ResponseBase<Boolean> deleteUser(@RequestParam Integer id) {
        log.debug("删除用户，requestDTO={}", id);
        Boolean aBoolean = loginService.deleteUser(id);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("用户查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<UserInfo>> pageQuery(@RequestBody UserInfoPageQueryReqDTO reqDTO) {
        log.debug("用户查询，reqDTO={}", reqDTO);
        PageDTO<UserInfo> pageQuery = loginService.pageQuery(reqDTO);
        return super.generateSuccess(pageQuery);
    }
}