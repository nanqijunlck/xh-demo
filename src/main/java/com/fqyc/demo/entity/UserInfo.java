package com.fqyc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体表
 *
 * @author lck
 * @date 2020/06/17  17:18
 * @since 1.0
 */
@Data
@TableName("t_user_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("检测工位")
    private String roleCode;

    @ApiModelProperty("最后登陆时间")
    private String phone;

    @ApiModelProperty("是否删除")
    private int isDeleted;

    private Date createTime;

    private Date updateTime;

}
