package org.fdu.awt.minifduuser.service;

import org.fdu.awt.minifduuser.bo.user.req.LoginReq;
import org.fdu.awt.minifduuser.bo.user.req.RegisterReq;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.exception.RepeatedException;

public interface IUserService {
    void register(RegisterReq registerReq) throws RepeatedException;

    User login(LoginReq loginReq);

}
