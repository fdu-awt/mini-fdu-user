package org.fdu.awt.minifduuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.RegisterReq;
import org.fdu.awt.minifduuser.exception.RepeatedEntityException;
import org.fdu.awt.minifduuser.result.Result;
import org.fdu.awt.minifduuser.result.ResultFactory;
import org.fdu.awt.minifduuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public Result register(@Validated @RequestBody RegisterReq registerReq) {
        try {
            userService.register(registerReq);
            return ResultFactory.buildSuccessResult();
        } catch (RepeatedEntityException e) {
            log.error("register error:" + e.getMessage(), e);
            return ResultFactory.buildFailResult(e.getMessage());
        } catch (RuntimeException e) {
            log.error("register error:" + e.getMessage(), e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }

}
