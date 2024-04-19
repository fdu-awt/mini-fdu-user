package org.fdu.awt.minifduuser.bo.user.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifduuser.entity.User;

/**
 * @author ZMark
 * @date 2024/4/19 下午6:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResp {
    private String username;
    private String email;
    private String selfImage;

    public static UserInfoResp fromEntity(User user) {
        return UserInfoResp.builder()
                .username(user.getName())
                .email(user.getEmail())
                .selfImage(user.getSelfImage())
                .build();
    }
}
