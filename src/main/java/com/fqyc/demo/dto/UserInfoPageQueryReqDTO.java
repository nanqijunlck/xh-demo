package com.fqyc.demo.dto;

import com.fqyc.demo.dto.base.PageBase;
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
public class UserInfoPageQueryReqDTO extends PageBase implements Serializable {

    @ApiModelProperty("检测工位")
    private String roleCode;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String phone;
}
