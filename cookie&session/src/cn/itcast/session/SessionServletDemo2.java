package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 获取session的值
 */
@WebServlet("/SessionServletDemo2")
public class SessionServletDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session 服务器会通过请求中的cookie获取session的id 然后找到相应的session对象
        HttpSession session = request.getSession();
        //获取session对象中的值
        Object msg = session.getAttribute("msg");
        System.out.println((String) msg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
