package cn.itcast.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidDataSourceFactory.*;

/**
 * 数据库操作工具类
 */
public class JDBCUtils {
    private static DataSource ds;
    //静态代码块加载资源
    static {
        try {
            //创建集
            Properties pro = new Properties();
            //用类加载器获取流
            //加载配置文件到集合中
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //利用durid工厂类创建连接池
            ds = createDataSource(pro);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接池方法
     */
    public static DataSource getDataSource(){
        return ds;
    }
    /**
     * 获取连接方法
     */
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
