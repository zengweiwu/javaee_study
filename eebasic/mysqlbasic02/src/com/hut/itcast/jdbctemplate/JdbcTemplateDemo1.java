package com.hut.itcast.jdbctemplate;

import com.hut.itcast.datasource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Template演示
 *     JdbcTemplate自动封装对象自动释放资源
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //传入DataSource 创建template对象
        JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
        //定义sql语句
        String sql = "update account set balance = 5000 where id = ?";
        //执行sql
        int count = template.update(sql, 3);
        System.out.println(count);
    }
}
