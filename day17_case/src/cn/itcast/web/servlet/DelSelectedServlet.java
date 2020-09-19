package cn.itcast.web.servlet;

import cn.itcast.service.UserService;
import cn.itcast.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 设置字符集
        request.setCharacterEncoding("utf-8");
        //2 获取请求参数
        String[] ids = request.getParameterValues("uid");
        //3 调用UserService方法
        if (ids != null && ids.length > 0){
            UserService userService = new UserServiceImp();
            userService.delselectedUserByids(ids);
        }
        //4 重定向
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
