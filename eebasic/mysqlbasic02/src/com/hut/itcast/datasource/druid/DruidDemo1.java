package com.hut.itcast.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * druid演示
 */
public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
        //导入jar包
        //创建properties集合
        Properties pro = new Properties();
        //加载配置文件
        ClassLoader classLoader = DruidDemo1.class.getClassLoader();//获取类加载器
        //通过类加载器获取资源字节流
        InputStream is = classLoader.getResourceAsStream("druid.properties");
        //加载到集合
        pro.load(is);
        //通过DruidDataSourceFactory中的方法创建连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);

    }
}
