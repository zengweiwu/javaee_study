package com.hut.jdbc;

import com.hut.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 从键盘输入用户名密码判断是否登录成功
 */
public class JdbcDemo9Login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        boolean islogin = login(username, password);
        if (islogin)
            System.out.println("登录成功");
        else
            System.out.println("登录失败");
    }


    public static boolean login(String username, String password) {
        //如果用户名和密码任意一个为空则登录失败
        if (username == null || password == null) {
            return false;
        }
        //如果不为空则到数据库中寻找匹配的记录
        if (username != null && password != null) {
            Connection conn = null;//获取连接
            PreparedStatement pstat = null;
            ResultSet res = null;

            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from user where name = ? and password = ?";//定义sql 使用占位符防止sql注入
                pstat = conn.prepareStatement(sql);//获取prepareStatement对象 这里是要传入参数
                //填充占位符
                pstat.setString(1, username);
                pstat.setString(2, password);
                //执行返回结果集
                res = pstat.executeQuery();
                //返回结果
                return res.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(res, pstat, conn);
            }
        }
        return false;
    }
}
