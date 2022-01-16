package com.macoloco.sheepy.account.entity;

import com.macoloco.sheepy.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Data
@ApiModel("用户信息")
public class Account extends BaseEntity {

    @ApiModelProperty("账户")
    private String account;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像地址")
    private String avatar;


}
