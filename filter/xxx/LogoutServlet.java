package org.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 清除session中的用户信息，实现退出登录
            session.invalidate();
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
