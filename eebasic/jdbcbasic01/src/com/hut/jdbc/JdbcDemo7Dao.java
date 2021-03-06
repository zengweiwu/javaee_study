package com.hut.jdbc;

import com.hut.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *访问数据库将数据库中的记录封装成对象存储到集合中去
 */
public class JdbcDemo7Dao {
    public static void main(String[] args) {
        List<Emp> emps = findAll();//调用方法
        System.out.println(emps);//输出
        System.out.println(emps.size());
    }

    /*
    将emp表中的数据封装成对象放到一个list集合
    返回值：list<emp>
     */
    public static List<Emp> findAll(){
        Connection conn = null;//提升对象作用域
        Statement stat = null;
        ResultSet res = null;
        List<Emp> emps = new ArrayList<>();
        Emp emp = null;
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
            String sql = "select * from emp";
            //获取执行sql对象
            stat = conn.createStatement();
            //执行sql 返回结果集
            res = stat.executeQuery(sql);
            //处理结果
            while (res.next()){//游标最开始处于表头 游标向下移动一行 有数据则返回true
                //取出数据
                int id = res.getInt("id");//传入列的编号获取第一列的游标所指行一个数据
                String name = res.getString("name");//传入列名
                String gender = res.getString("gender");
                double salary = res.getDouble("salary");
                Date join_date = res.getDate("join_date");
                int dept_id = res.getInt("dept_id");
                //创建对象
                emp = new Emp();
                //赋值
                emp.setId(id);
                emp.setName(name);
                emp.setGender(gender);
                emp.setSalary(salary);
                emp.setJoin_date(join_date);
                emp.setDept_id(dept_id);
                //将对象添加到List集合
                emps.add(emp);
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
        return emps;//返回集合
    }
}
