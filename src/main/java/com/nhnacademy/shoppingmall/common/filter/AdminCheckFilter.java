package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리

        String requestURI = req.getRequestURI();

        if (requestURI.startsWith("/admin/")) {
            User user = (User) req.getSession().getAttribute("user");

            String userRole = String.valueOf(user.getUserAuth());

            if (userRole.equals("ROLE_USER")) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "do not permission to access this page.");
                return;
            }
        }

        // 권한이 있거나 관리자가 아닌 페이지 요청일 경우 계속 처리
        chain.doFilter(req, res);
    }

}
