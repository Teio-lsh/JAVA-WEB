package org.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // 用户已登录，显示用户信息
            String username = (String) session.getAttribute("user");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/welcome.html").forward(request, response);
        } else {
            // 用户未登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }
}