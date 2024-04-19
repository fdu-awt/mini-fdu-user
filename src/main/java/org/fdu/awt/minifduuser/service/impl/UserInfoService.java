package org.fdu.awt.minifduuser.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.req.ModifyReq;
import org.fdu.awt.minifduuser.dao.UserDAO;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.result.ResultFactory;
import org.fdu.awt.minifduuser.service.IUserInfoService;
import org.fdu.awt.minifduuser.token.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author zouzouyi
 * @date 2024/4/19 11:28
 */
@Slf4j
@Service
public class UserInfoService implements IUserInfoService {
    private final UserDAO userDAO;

    @Autowired
    public UserInfoService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserInfo(String authorizationHeader) {
        User user = findUserByToken(authorizationHeader);
        if (user == null) {
            return null;
        }
        //设置密码保密不返回
        user.setPassword(null);
        return user;

    }

    @Override
    public User modifyUserInfo(String authorizationHeader, ModifyReq modifyReq) {
        User user = findUserByToken(authorizationHeader);
        if (user == null) {
            return null;
        }
        user.setName(modifyReq.getUsername());
        user.setEmail(modifyReq.getEmail());
        user.setSelfImage(modifyReq.getSelfImage());
        return save(user);
    }

    @Override
    public int modifyUserPassword(String authorizationHeader, String oldPassword, String newPassword) {
        User user = findUserByToken(authorizationHeader);
        //非法修改密码
        if (user == null) {
            return -1;
        }
        //密码不正确
        if(!oldPassword.equals(user.getPassword())){
            return -2;
        }
        user.setPassword(newPassword);
        save(user);
        return 0;
    }


    private User findByUserId(Long userId) {
        return userDAO.getById(userId);
    }

    private User save(User user) {
        return userDAO.save(user);
    }

    private User findUserByToken(String authorizationHeader) {

        // 解析token
        String token = authorizationHeader.substring("Bearer ".length());
        //获取用户id
        Optional<Long> userIdOptional = JWTUtils.getUserIdByToken(token);
        Long userId = userIdOptional.get();

        return findByUserId(userId);
    }


}
