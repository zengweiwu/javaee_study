package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.imp.UserServiceImp;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 设置字符集
        request.setCharacterEncoding("utf-8");
        //2 获取验证码请求参数
        String verifycode = request.getParameter("verifycode");
        //3 判断验证码是否正确
        HttpSession session = request.getSession();
        String checkcode = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode.equalsIgnoreCase(verifycode)){
            //显示登录失败
            //1将提示信息存入request中
            request.setAttribute("login_msg","验证码错误");
            //2转发到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //4 调用service层方法判断User是否存在
        //获取请求参数 封装user对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImp();
        //调用logiin方法
        User loginuser = userService.login(user);
        if (loginuser != null){
            //如果查询到结果 将返回的user对象存入session中
            session.setAttribute("user",loginuser);
            //重定向到首页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            //显示错误信息
            //1将提示信息存入request中
            request.setAttribute("login_msg","用户名或密码错误");
            //2转发到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
