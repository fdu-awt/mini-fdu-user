package org.fdu.awt.minifduuser.bo.user.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZMark
 * @date 2024/4/19 下午6:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModifyReq {
    @NotNull(message = "密码必填")
    private String oldPassword;

    @NotNull(message = "密码必填")
    @Size(min = 5, message = "密码长度至少为5")
    @Size(max = 15, message = "密码长度不能超过15")
    private String newPassword;
}
