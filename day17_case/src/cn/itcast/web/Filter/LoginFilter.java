package cn.itcast.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证
 *      如果没有登录则不能访问一些页面
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取请求路径
        HttpServletRequest request = (HttpServletRequest)req;//强转为httpservletrequest
        String requestURI = request.getRequestURI();
        //如果请求路径中包含登录有关资源则放行
        if (requestURI.contains("/loginServlet") || requestURI.contains("/login.jsp") || requestURI.contains("/js/") || requestURI.contains("/css/") || requestURI.contains("/fonts/") ||requestURI.contains("/checkCodeServlet")){
            chain.doFilter(request, resp);
        }else {//如果请求了除登录相关的资源则判断是否登录
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                //user不为空则说明已经登录 放行
                chain.doFilter(req,resp);
            }else {
                //没有登录则存入提示信息 转发到登录页面
                request.setAttribute("msg","您未登录，请登录！！");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
