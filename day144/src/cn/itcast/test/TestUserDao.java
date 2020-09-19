package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

/**
 * UserDao测试类
 *  测试login方法
 */
public class TestUserDao {
    @Test
    public void testlogin(){
        User loginuser = new User();
        loginuser.setUsername("zengweiwu");
        loginuser.setPassword("12345");

        UserDao userDao = new UserDao();
        User user = userDao.login(loginuser);
        System.out.println(user);
    }
}
