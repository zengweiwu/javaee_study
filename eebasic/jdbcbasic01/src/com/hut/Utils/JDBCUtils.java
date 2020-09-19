package com.hut.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    //静态代码块加载资源 加载类到内存就执行 这里相当于给类的成员变量赋初值
    static {
        //创建properties集合
        Properties pro = new Properties();
        //获取类加载器
        ClassLoader classLoader = JDBCUtils.class.getClassLoader();
        //获取url
        URL res = classLoader.getResource("jdbc.properties");
        //获取path对象
        String path = res.getPath();
        //加载配置文件到内存
        try {
            pro.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //取出集合元素给成员变量赋值
        url = pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");
        driver = pro.getProperty("driver");
        //注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     * @return 连接对象
     */

    public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源
     * @param Statement stat
     * @param  Connection conn
     */
     public static void close(Statement stat,Connection conn){
         if (null != stat) {//防止空指针异常
             try {
                 stat.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         if (null != conn) {
             try {
                 stat.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }

    /**
     * 释放资源重载
     * @param res
     * @param stat
     * @param conn
     */
    public static void close(ResultSet res,Statement stat, Connection conn){
        if (null != res) {//res最后创建最先关闭
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != stat) {//防止空指针异常
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
