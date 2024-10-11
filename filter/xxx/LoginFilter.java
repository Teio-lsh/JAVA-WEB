package org.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {

    private static final List<String> EXCLUDE_PATHS = Arrays.asList("/login.html", "/login", "/css/*", "/js/*", "/images/*");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器时可以进行一些配置工作，例如从配置文件读取排除列表等
        // 这里可以留空，如果不需要初始化任何内容
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();

        if (isExcluded(requestURI)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                chain.doFilter(request, response);
            } else {
                String contextPath = req.getContextPath();
                res.sendRedirect(contextPath + "/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        // 销毁过滤器时可以进行一些清理工作，如果不需要可以留空
    }

    private boolean isExcluded(String requestURI) {
        for (String path : EXCLUDE_PATHS) {
            if (requestURI.endsWith(path) || requestURI.equals(path)) {
                return true;
            }
        }
        return false;
    }
}