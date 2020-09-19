package cn.itcast.web.servlet;

import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SuccessfulServlet")
public class SuccessfulServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //给浏览器回写一句话
        //获取request共享域中的user对象
        Object user = (User) request.getAttribute("user");
        if (user != null) {//防止空指针异常
            //设置字符集
            response.setContentType("text/html;charset=utf-8");//请求头设置什么字符集编码浏览器会自动以这个编码进行解码
            //获取输出流会写数据
            PrintWriter pw = response.getWriter();
            pw.write("你好" + ((User) user).getUsername() + "登录成功");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
