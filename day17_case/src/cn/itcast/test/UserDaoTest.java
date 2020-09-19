package cn.itcast.test;

import cn.itcast.dao.imp.UserDaoImp;
import cn.itcast.domain.User;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    /*
    测试findAll方法
     */
    @Test
    public void testfindAll(){
        UserDaoImp userDaoImp = new UserDaoImp();
        List<User> users = userDaoImp.findAll();
        System.out.println(users);
    }
}
