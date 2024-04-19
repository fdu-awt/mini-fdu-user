package org.fdu.awt.minifduuser.controller;


import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.req.ModifyReq;
import org.fdu.awt.minifduuser.bo.user.req.PasswordModifyReq;
import org.fdu.awt.minifduuser.entity.User;
import org.fdu.awt.minifduuser.result.Result;
import org.fdu.awt.minifduuser.result.ResultFactory;
import org.fdu.awt.minifduuser.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zouzouyi
 * @description 用户信息管理
 * @date 2024/4/19 10:31
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/user-service")
public class UserInfoController {
    private final IUserInfoService userInfoService;

    @Autowired
    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/get-user-info")
    public Result getUserInfo(@RequestHeader(name = "Authorization", required = false) String authorizationHeader) {
        User user = userInfoService.getUserInfo(authorizationHeader);
        if (user == null) {
            ResultFactory.buildInsufficientPermissionsResult();
        }
        return ResultFactory.buildSuccessResult(user);
    }

    @PostMapping("/modify-user-info")
    public Result modifyUserInfo(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, @Validated @RequestBody ModifyReq modifyReq) {
        User user = userInfoService.modifyUserInfo(authorizationHeader, modifyReq);
        if (user == null) {
            ResultFactory.buildInsufficientPermissionsResult();
        }
        return ResultFactory.buildSuccessResult("修改信息成功", user);
    }

    @PostMapping("/modify-user-password")
    public Result modifyUserPassword(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, @Validated @RequestBody PasswordModifyReq req) {
        int code = userInfoService.modifyUserPassword(authorizationHeader, req.getOldPassword(), req.getNewPassword());
        if (code == -1) {
            return ResultFactory.buildInsufficientPermissionsResult();
        }
        if (code == -2) {
            return ResultFactory.buildFailResult("输入密码错误");
        }
        return ResultFactory.buildSuccessResult("修改密码成功");
    }
}
