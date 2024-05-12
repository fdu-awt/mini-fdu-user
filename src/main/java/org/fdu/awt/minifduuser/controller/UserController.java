package org.fdu.awt.minifduuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.req.LoginReq;
import org.fdu.awt.minifduuser.bo.user.req.RegisterReq;
import org.fdu.awt.minifduuser.bo.user.resp.LoginResp;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.exception.NotExistsException;
import org.fdu.awt.minifduuser.exception.RepeatedException;
import org.fdu.awt.minifduuser.result.Result;
import org.fdu.awt.minifduuser.result.ResultFactory;
import org.fdu.awt.minifduuser.service.IUserService;
import org.fdu.awt.minifduuser.token.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Violette
 * @description 用户登录和注册管理
 * @date 2024/4/16 10:31
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/user-service")
public class UserController {
    private final IUserService userService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public Result register(@Validated @RequestBody RegisterReq registerReq) {
        try {
            userService.register(registerReq);
            return ResultFactory.buildSuccessResult();
        } catch (RepeatedException e) {
            log.error("register error:{}", e.getMessage(), e);
            return ResultFactory.buildFailResult("用户名已存在");
        } catch (RuntimeException e) {
            log.error("register error:{}", e.getMessage(), e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }

    @PostMapping("/log-in")
    public Result login(@Validated @RequestBody LoginReq loginReq) {
        try {
            User user = userService.login(loginReq);

            // 根据用户信息，生成token
            String token = JWTUtils.getToken(user);
            Date expireTime = JWTUtils.getExpirationTime(token);

            LoginResp loginResp = new LoginResp(user.getName(), token, sdf.format(expireTime));
            return ResultFactory.buildSuccessResult(loginResp);
        } catch (NotExistsException e) {
            log.error("login error:{}", e.getMessage(), e);
            return ResultFactory.buildFailResult(e.getMessage());
        } catch (RuntimeException e) {
            log.error("login error:{}", e.getMessage(), e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }


}
