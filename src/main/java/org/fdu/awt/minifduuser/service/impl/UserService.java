package org.fdu.awt.minifduuser.service.impl;

import org.fdu.awt.minifduuser.bo.user.LoginReq;
import org.fdu.awt.minifduuser.bo.user.RegisterReq;
import org.fdu.awt.minifduuser.dao.UserDAO;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.exception.RepeatedEntityException;
import org.fdu.awt.minifduuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Violette
 * @date 2024/4/16 11:28
 */
@Service
public class UserService implements IUserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(RegisterReq registerReq) throws RepeatedEntityException {
        User user = userDAO.findByName(registerReq.getName());
        if (user != null) {
            throw RepeatedEntityException.RepeatEntityName("User", registerReq.getName());
        }
        userDAO.save(User.fromRegisterReq(registerReq));
    }

    @Override
    public void login(LoginReq loginReq) {

    }


}
