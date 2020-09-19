package com.hut.itcast.datasource.druid;

import com.hut.itcast.datasource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试druid工具类:
 * 像accout表中增加一条记录
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            //获取连接
            conn = JDBCUtils.getconnection();
            //定义sql语句
            String sql = "insert into account values (null,?,?)";
            //获取执行sql对象
            pstat = conn.prepareStatement(sql);
            //设置值
            pstat.setString(1,"王五");
            pstat.setDouble(2,3000);
            //执行sql
            int count = pstat.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JDBCUtils.close(pstat,conn);
        }

    }
}
