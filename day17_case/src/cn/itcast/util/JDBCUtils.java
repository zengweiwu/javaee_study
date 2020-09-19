package cn.itcast.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 */
public class JDBCUtils {
    private static DataSource ds;

    /*
     * 静态代码块初始化ds
     */
    static {
        try {
            //创建properties集合用来存储配置文件
            Properties pro = new Properties();
            //利用类加载器加载配置文件进内存
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //创建连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取连接方法
     */
    public static Connection getConnection() throws SQLException {
            return ds.getConnection();
    }


    public static DataSource getDataSource() {
        return ds;

    }
}

