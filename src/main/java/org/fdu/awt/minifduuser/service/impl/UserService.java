package org.fdu.awt.minifduuser.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.req.LoginReq;
import org.fdu.awt.minifduuser.bo.user.req.RegisterReq;
import org.fdu.awt.minifduuser.dao.UserDAO;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.exception.NotExistsException;
import org.fdu.awt.minifduuser.exception.RepeatedException;
import org.fdu.awt.minifduuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Violette
 * @date 2024/4/16 11:28
 */
@Slf4j
@Service
public class UserService implements IUserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(RegisterReq registerReq) throws RepeatedException {
        User user = userDAO.findByName(registerReq.getUsername());
        if (user != null) {
            throw RepeatedException.RepeatEntity("User", registerReq.getUsername());
        }
        // TODO: 密码加密？
        userDAO.save(User.fromRegisterReq(registerReq));
    }

    @Override
    public User login(LoginReq loginReq) throws NotExistsException {
        User user = userDAO.findByNameAndPassword(loginReq.getUsername(), loginReq.getPassword());
        if (user == null) {
            throw new NotExistsException("用户名或密码错误");
        }
        log.info("登录成功");
        return user;
    }

    @Override
    public User findByUserId(Long userId) {
        return userDAO.getById(userId);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }


}
