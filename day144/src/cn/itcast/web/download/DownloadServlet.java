package cn.itcast.web.download;

import cn.itcast.web.util.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取文件名
        String filename = request.getParameter("filename");
        //2 用servletcontext获取文件正式路径 判断是什么类型
        ServletContext servletContext = this.getServletContext();
        String mimeType = servletContext.getMimeType(filename);
        String realPath = servletContext.getRealPath("/img/" + filename);
        //3 设置文件类型 和打开时的展示方式
        response.setHeader("Content-type",mimeType);
        //注意获取user-agent是用getHeader方法
        String agent = request.getHeader("user-agent");
        //解决文件名中文乱码问题
        filename = DownLoadUtils.getFileName(agent, filename);
        response.setHeader("content-disposition","attachment;filename="+filename);
        //3 加载文件到内存 并向浏览器会写数据
        InputStream is =new FileInputStream(realPath);
        ServletOutputStream nos = response.getOutputStream();
        byte[] arrbuf = new byte[1024 * 8];
        int len = 0;
        while ((len = is.read(arrbuf))!=-1){
            nos.write(arrbuf,0,len);
        }
        is.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
