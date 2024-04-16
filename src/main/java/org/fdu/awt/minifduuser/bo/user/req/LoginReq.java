package org.fdu.awt.minifduuser.bo.user.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/4/16 10:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    @NotNull(message = "用户名必填")
    @Size(min = 3, message = "用户名长度至少为3")
    @Size(max = 10, message = "用户名长度不能超过10")
    private String name;

    @NotNull(message = "密码必填")
    @Size(min = 5, message = "密码长度至少为5")
    @Size(max = 15, message = "密码长度不能超过15")
    private String password;
}
