package cn.itcast.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //设置字符编码和文件类型 如果没有设置则需要在前台指定以json格式解析服务器来的数据
        //response.setContentType("application/json;charset=utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        //创建map用于存储判断结果 以便于转换成json对象回写
        Map<String,Object> regiistertig = new HashMap<String,Object>();
        //判断是否存在该用户名
        if ("tom".equals(username)){
            regiistertig.put("usernameExist",true);
            regiistertig.put("msg","用户名被使用了");
        }else {
            regiistertig.put("usernameExist",false);
            regiistertig.put("msg","用户名没有被使用");
        }
        //创建jackjson核心对象ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //将regiistertig转换为json字符串格式 并填充到输出流中
        objectMapper.writeValue(response.getWriter(),regiistertig);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
