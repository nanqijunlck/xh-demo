package com.fqyc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lck
 * @Date 2023/3/16 18:45
 * @Version 1.0
 * @Desc
 */
@Data
public class UserInfoReqDTO implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("检测工位")
    private String roleCode;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;
}
