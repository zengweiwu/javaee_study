package cn.itcast.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤敏感词汇
 */
@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取代理对象 增强getparameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //如果是getparameter方法则增强
                if (method.getName().equals("getParameter")) {
                    //取出参数值
                    String value = (String) method.invoke(req, args);
                    //遍历敏感词汇集合
                    if (value != null) {
                        for (String str : sensitivelist) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req, args);
            }
        });
        //放行 传入的是代理对象 在servlet中是操作代理对象
        chain.doFilter(proxy_req, resp);
    }

    private List<String> sensitivelist = new ArrayList<>();//装敏感词汇的list

    public void init(FilterConfig config) throws ServletException {
        /*
        初始化 将敏感字符加载到内存中
         */
        //获取文件路径
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
        try {
            //建立到该文件的缓冲流
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //读取文件内容
            String line;
            while ((line = br.readLine()) != null) {//这里返回的是字符串
                sensitivelist.add(line);//将读出的字符存入集合
            }
            //释放资源
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
