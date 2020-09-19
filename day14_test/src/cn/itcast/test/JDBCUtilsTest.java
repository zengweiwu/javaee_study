package cn.itcast.test;

import cn.itcast.util.JDBCUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilsTest {
    @Test
    public void testgetDataSource(){
        DataSource dataSource = JDBCUtils.getDataSource();
        System.out.println(dataSource);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
    }
}
