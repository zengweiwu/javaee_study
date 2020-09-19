package cn.itcast.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器快速入门
 */
@WebFilter("/*")//过滤所有请求
public class FilterDemo2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //放行前一般用于增强请求
        System.out.println("过滤器被执行了");
        chain.doFilter(req, resp);//放行
        //放行后一半增强响应
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
