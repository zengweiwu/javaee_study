package cn.itcast.web;

import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import cn.itcast.service.imp.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service层方法返回list集合
        ProvinceService provinceService = new ProvinceServiceImpl();
        //List<Province> pros = provinceService.findAllProvince();
        String provinces_json = provinceService.findAll_json();
        //将返回的java对象用jackjson核心对象 封装成json 如果直接返回json则不用封装
        //ObjectMapper objectMapper = new ObjectMapper();
        //设置响应内容
        response.setContentType("application/json;charset=utf-8");
        //响应结果
        response.getWriter().write(provinces_json);
        //objectMapper.writeValue(response.getWriter(),pros);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
