package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * user的数据库操作类
 */
public class UserDao {
    /**
     * 登录方法
     * @return 一个user对象
     */
    public User login(User loginUser){
        try {
            //定义sql语句
            String sql = "select * from user where username =? and password =?";
            //获取连接
            Connection conn = JDBCUtils.getConnection();
            //获取sql执行器
            PreparedStatement pstat = conn.prepareStatement(sql);
            //设置参数
            pstat.setString(1,loginUser.getUsername());
            pstat.setString(2,loginUser.getPassword());
            //执行sql返回结果集
            ResultSet res = pstat.executeQuery();
            if (res.next()){
                int id = res.getInt("id");
                String username = res.getString("username");
                String password = res.getString("password");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                return user;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
