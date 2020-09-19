package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cookie快速入门
 */
@WebServlet("/cookieServletDemo2")
public class CookieServletDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3 获取cookie
        Cookie[] cs = request.getCookies();
        //4 获取cookie内容
        if(cs != null){
            for (Cookie c:cs) {
                String name = c.getName();
                String value = c.getValue();
                System.out.println(name+":"+value);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
