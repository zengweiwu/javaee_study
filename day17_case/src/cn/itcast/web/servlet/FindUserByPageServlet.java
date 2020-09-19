package cn.itcast.web.servlet;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 设置字符集
        request.setCharacterEncoding("utf-8");
        //2 获取请求参数
        String currentpage = request.getParameter("currentpage");//当前页码
        String rows = request.getParameter("rows");//每页条数
        //如果请求的参数为空 则赋默认值
        if (currentpage == null || "".equals(currentpage)){
            currentpage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取查询的条件参数
        Map<String, String[]> condition = request.getParameterMap();
        //3 调用UserService方法
        UserService userService = new UserServiceImp();
        PageBean<User> pb = userService.findUserByPage(currentpage,rows,condition);//返回一个分页对象
        //4 将pb condition放入request中
        request.setAttribute("condition",condition);
        request.setAttribute("pb",pb);
        //5 转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
