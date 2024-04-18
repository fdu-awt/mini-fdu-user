package org.fdu.awt.minifduuser.controller;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.bo.user.req.LoginReq;
import org.fdu.awt.minifduuser.bo.user.req.ModifyReq;
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
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
            return ResultFactory.buildFailResult(e.getMessage());
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

    @GetMapping("/get-user-info")
    public Result getUserInfo(@RequestHeader(name = "Authorization", required = false) String authorizationHeader) {
        try {
            // 解析token
            String token = authorizationHeader.substring("Bearer ".length());

            // 使用修改后的getUserIdByToken方法获取用户ID
            Optional<Long> userIdOptional = JWTUtils.getUserIdByToken(token);

            // 如果用户ID不存在，返回401 Unauthorized
            if (!userIdOptional.isPresent()) {
                return ResultFactory.buildInsufficientPermissionsResult("您的token无效或已过期");
            }

            // 获取用户ID
            Long userId = userIdOptional.get();

            // 根据userId查询用户信息
            User user = userService.findByUserId(userId);

            // 如果用户不存在，返回404 Not Found
            if (user == null) {
                return ResultFactory.buildNotFoundResult("用户不存在");
            }

            // 获取有效用户信息并移除敏感信息
            user.setPassword(null);

            // 构建成功的响应
            return ResultFactory.buildSuccessResult(user);
        } catch (Exception e) {
            // 记录异常信息
            log.error("Error getting user info: {}", e.getMessage(), e);
            // 出现异常情况，返回服务器内部错误
            return ResultFactory.buildInternalServerErrorResult();
        }
    }

    @PostMapping("/modify-user-info")
    public Result modifyUserInfo(@Validated @RequestBody ModifyReq modifyReq){
        Long userId = modifyReq.getId();
        User user = userService.findByUserId(userId);
        if (user == null) {
            return ResultFactory.buildNotFoundResult("id有错误");
        }
        user.setName(modifyReq.getUsername());
        user.setEmail(modifyReq.getEmail());
        user.setSelfImage(modifyReq.getSelfImage());
        return ResultFactory.buildSuccessResult("修改信息成功",userService.save(user));
    }

    @PostMapping("/modify-user-password")
    public Result modifyUserPassword(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, String password){
        // 解析token
        String token = authorizationHeader.substring("Bearer ".length());

        // 使用修改后的getUserIdByToken方法获取用户ID
        Optional<Long> userIdOptional = JWTUtils.getUserIdByToken(token);
        // 获取用户ID
        Long userId = userIdOptional.get();
        // 根据userId查询用户信息
        User user = userService.findByUserId(userId);
        user.setPassword(password);
        return ResultFactory.buildSuccessResult("修改密码成功");

    }



}
