package com.hut.jdbc;

import com.hut.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作:
 * 执行事务张三给李四转账500元
 */
public class JdbcDemo10 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstat1 = null;
        PreparedStatement pstat2 = null;
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //用连接对象开启事务
            conn.setAutoCommit(false);//设置为false手动提交事务
            //定义sql
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";
            //获取执行sql对象
            pstat1 = conn.prepareStatement(sql1);
            pstat2 = conn.prepareStatement(sql2);
            //设置值
            pstat1.setDouble(1, 500);
            pstat1.setDouble(2, 1);

            pstat2.setDouble(1, 500);
            pstat2.setDouble(2, 2);
            //执行sql
            pstat1.executeUpdate();
                                      //手动制造int j = 3/0测试事务回滚
            pstat2.executeUpdate();

            //如果执行到最后没有什么问题就提交事务
            conn.commit();
        } catch (SQLException e) {
           //如果try里面的代码执行出现任何异常 则回滚
            try {
                if (conn != null)
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstat1, conn);
            JDBCUtils.close(pstat2, null);
        }
    }
}
