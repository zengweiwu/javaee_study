package com.hut.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/*
jdbc快速入门
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //注册驱动 Driver类中静态代码块自己创建了一个Driver对象，作为传入DriverManager.register()方法中注册一个驱动
         Class.forName("com.mysql.jdbc.Driver");//注册驱动的代码放到com.mysql.jdbc.Driver的静态代码块里面
         //获取连接 如果是连接本地的mysql服务器可以简写为jdbc:mysql:///test
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        //定义sql语句
        String sql = "delete from dept where id = 5";
        //获取执行sql语句的对象
        Statement statement = connection.createStatement();
        //执行sql语句
        int count = statement.executeUpdate(sql);//返回的是受影响的行数 executeUpdate(sql)执行对数据增删改的sql 也可以执行DDL
        System.out.println(count);//如果返回值大于0则执行成功
        //关闭资源
        statement.close();
        connection.close();

    }
}
