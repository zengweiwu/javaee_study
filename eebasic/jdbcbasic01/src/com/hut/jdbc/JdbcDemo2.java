package com.hut.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
向account中insert一条记录
 */
public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection conn = null;//提升对象作用域
        Statement stat = null;
        try {
            //注册驱动
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            //定义sql语句
            String sql = "insert into account values(null,'王五',1000)";//这里的字符串要写单引号不然会报错
            //获取执行sql对象
            stat = conn.createStatement();
            //执行sql
            int count = stat.executeUpdate(sql);
            //处理结果
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
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
}
