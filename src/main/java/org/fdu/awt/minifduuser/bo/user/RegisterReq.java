package org.fdu.awt.minifduuser.bo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/4/16 10:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {
    @NotNull(message = "用户名必填")
    @Size(min = 3, message = "用户名长度至少为3")  // 相当于检查了not empty
    @Size(max = 10, message = "用户名长度不能超过10")
    private String name;

    @NotNull(message = "邮箱必填")
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Size(max = 30, message = "邮箱长度不能超过30")

    private String email;

    @NotNull(message = "密码必填")
    @Size(min = 5, message = "密码长度至少为5")   // 相当于检查了not empty
    @Size(max = 10, message = "密码长度不能超过10")
    private String password;
}
