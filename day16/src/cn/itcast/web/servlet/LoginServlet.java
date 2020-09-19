package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //获取生成的验证码 判断验证码是否正确
        HttpSession session = request.getSession();
        String checkcode_session = (String)session.getAttribute("checkcode_session");
        //获得checkcode后马上删除 防止重复使用验证码
        session.removeAttribute("checkcode_session");
        if (checkcode.equalsIgnoreCase(checkcode_session)){
            //如果验证码一致 接着判断用户名和密码是否正确
            if ("zhangsan".equals(username) && "12345".equals(password)){
                //将用户信息存入session
                session.setAttribute("user",username);
                //重定向到successsful页面
                response.sendRedirect(request.getContextPath()+"/successful.jsp");
            }else {
                //将错误信息存入session
                session.setAttribute("erro_seesion","用户名或密码错误");
                //转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }else {
            //将提示信息存入sessioon中
            session.setAttribute("cc_erro","验证码错误");
            //验证码不一致 转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
