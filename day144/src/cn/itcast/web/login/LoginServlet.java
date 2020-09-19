package cn.itcast.web.login;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");
       /* //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装成User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

       //用beanUtils封装对象
        //获取请求参数map
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建空的user对象
        User loginUser = new User();
        //调用BeanUtils.populate() 注意是用apache下面的类
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将封装的User对象传递给Loginservlet查询
        UserDao userdao = new UserDao();
        User user = userdao.login(loginUser);
        if(user != null){
            //存储数据
            request.setAttribute("user",user);
            //转发到successfulservlet
            request.getRequestDispatcher("/SuccessfulServlet").forward(request,response);
        }else {
           //转发到failservlet
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
