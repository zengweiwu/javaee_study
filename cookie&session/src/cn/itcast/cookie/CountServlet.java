package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/countServlet")
public class CountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        boolean flag = true;//设置标记 如果是第一次访问没有cookie默认设置为true
        //获取cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies) {
            if(c.getName().equals("lasttime")){
                String time = c.getValue();
                time = URLDecoder.decode(time, "utf-8");//输出之前记得解码
                PrintWriter nw = response.getWriter();
                nw.write("欢迎访问，上一次访问时间为："+time);
                //获取当前时间并格式化
                String ntime = getTime();
                //URL编码 不设置编码谷歌浏览器解析不了
                ntime = URLEncoder.encode(ntime, "utf-8");
                c.setValue(ntime);
                ntime = URLDecoder.decode(ntime, "utf-8");
                response.addCookie(c);
                flag = false;
                break;
            }
        }
        //第一次访问没有cookie 则创建cookie
        if (cookies.length == 0 || flag){
            String time = getTime();
            //URL编码 不设置编码谷歌浏览器解析不了
            time = URLEncoder.encode(time, "utf-8");
            Cookie timecookie = new Cookie("lasttime", time);
            //设置cookie的缓存时间
            timecookie.setMaxAge(60 * 60 * 24);
            response.addCookie(timecookie);
            //展示前URL解码
            time = URLDecoder.decode(time, "utf-8");
            PrintWriter nw = response.getWriter();
            nw.write("第一次访问,欢迎光临。访问时间为："+time);
        }
        /*//设置cookie 原来的请求中有两cookie
        if(request.getCookies().length == 2){
            String time = getTime();
            Cookie travetime = new Cookie("time", time);
            response.addCookie(travetime);
            //回写 你好首次登陆欢迎光临
            PrintWriter nw = response.getWriter();
            nw.write("欢迎第一次访问，访问时间为："+time);
        }else {
            //如果不是第一次访问则获取cookie的值 更新cookie
            String time = getTime();
            Cookie[] cookies = request.getCookies();
            for (Cookie c:cookies) {
                if (c.getName().equals("time")){
                    c.setValue(time);
                }
            }
            PrintWriter nw = response.getWriter();
            nw.write("欢迎访问，上一次访问时间为："+time);
        }*/
    }

    public static String getTime(){
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        return time;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
