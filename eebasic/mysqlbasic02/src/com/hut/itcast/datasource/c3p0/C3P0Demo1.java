package com.hut.itcast.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0演示
 */
public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
        //获取连接池对象
        DataSource ds = new ComboPooledDataSource();
        //获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);

    }
}
