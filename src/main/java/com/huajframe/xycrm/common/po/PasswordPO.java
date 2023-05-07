package com.huajframe.xycrm.common.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Hua JFarmer
 */
@Data
public class PasswordPO {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "请输入6-16位数字和字母组合的新密码")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
