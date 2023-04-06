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
@TableName("t_user_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty("用户编码")
    private String roleName;

    @ApiModelProperty("用户编码")
    private String roleCode;

    @ApiModelProperty("前置角色code")
    private String beforeRoleCode;

    @ApiModelProperty("角色id")
    private int isDeleted;

    private Date createTime;

    private Date updateTime;

}
