package org.fdu.awt.minifduuser.service;

import org.fdu.awt.minifduuser.bo.user.req.ModifyReq;
import org.fdu.awt.minifduuser.entity.User;

public interface IUserInfoService {

    User getUserInfo(String token);
    User modifyUserInfo(String token, ModifyReq modifyReq);
    int modifyUserPassword(String token, String oldPassword, String newPassword);

}
