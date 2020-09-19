package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {
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
