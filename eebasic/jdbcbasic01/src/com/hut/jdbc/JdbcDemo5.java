package com.hut.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * executeUpdate()执行DDL语句创建一张表
 */
public class JdbcDemo5 {
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
            String sql = "CREATE table student(id int,name varchar (20))";
            //获取执行sql对象
            stat = conn.createStatement();
            //执行sql
            int count = stat.executeUpdate(sql);//执行ddl语句不返回结果
            System.out.println(count);//返回0
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
