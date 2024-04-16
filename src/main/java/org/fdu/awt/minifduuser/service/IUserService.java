package org.fdu.awt.minifduuser.service;

import org.fdu.awt.minifduuser.bo.user.LoginReq;
import org.fdu.awt.minifduuser.bo.user.RegisterReq;
import org.fdu.awt.minifduuser.exception.RepeatedEntityException;

public interface IUserService {
    void register(RegisterReq registerReq) throws RepeatedEntityException;

    void login(LoginReq loginReq);

}
