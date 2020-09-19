package com.hut.itcast.datasource.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * druid工具类
 */
public class JDBCUtils {
    private static DataSource ds;
    /**
     * 静态代码块加载配置文件完成连接池的初始化
     */
    static {
        try {
            //加载配置文件到集合
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池静态方法
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接静态方法
     */
    public static Connection getconnection() throws SQLException {
            return ds.getConnection();
    }

    /**
     * 归还连接静态方法
     * @param st
     * @param conn
     */
    public static void close(Statement st,Connection conn){
         if (st != null){
             try {
                 st.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
        if (conn != null){
            try {
                conn.close();//将连接归还给连接池 注意不是关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 归还连接池静态方法重载
     * @param st
     * @param conn
     * @param  rs
     */
    public static void close(ResultSet rs, Statement st, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();//将连接归还给连接池 注意不是关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
