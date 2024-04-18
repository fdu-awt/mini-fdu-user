package org.fdu.awt.minifduuser.token;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.result.Result;
import org.fdu.awt.minifduuser.result.ResultFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Violette
 * @date 2024/4/16 19:59
 */
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String msg;

        // 获取请求头中的令牌
        String authorizationHeader = request.getHeader("Authorization");
        log.info("请求头中的Authorization是:{}", authorizationHeader);

        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new Exception("Missing or invalid Authorization header.");
            }
            // 提取令牌
            String token = authorizationHeader.substring("Bearer ".length());

            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            msg = "无效签名";
        } catch (TokenExpiredException e) {
            msg = "token过期";
        } catch (AlgorithmMismatchException e) {
            msg = "token算法不一致";
        } catch (Exception e) {
            msg = "token无效";
        }

        Result result = ResultFactory.buildInsufficientPermissionsResult(msg);
        // 将result写入响应体
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result.toString());

        return false; // 返回false表示不继续执行后续的controller逻辑
    }
}
