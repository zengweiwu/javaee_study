package com.hut.jdbc;

import java.sql.*;

/**
 * 测试executeQuery() 结果集的正确使用
 */
public class JdbcDemo6 {
    public static void main(String[] args) {
        Connection conn = null;//提升对象作用域
        Statement stat = null;
        ResultSet res = null;
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
            String sql = "select * from account";
            //获取执行sql对象
            stat = conn.createStatement();
            //执行sql 返回结果集
            res = stat.executeQuery(sql);
            //处理结果
            while (res.next()){//游标最开始处于表头 游标向下移动一行 有数据则返回true
                //取出数据
                int id = res.getInt(1);//传入列的编号获取第一列的游标所指行一个数据
                String name = res.getString("name");//传入列名
                double balance = res.getDouble(3);
                //输出结果
                System.out.println("id---"+id+"  name---"+name+"  balance---"+balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
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
}
