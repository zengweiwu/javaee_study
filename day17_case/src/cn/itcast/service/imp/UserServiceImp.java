package cn.itcast.service.imp;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.imp.UserDaoImp;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * UserService的实现类
 */
public class UserServiceImp implements UserService {
    private UserDao userDao= new UserDaoImp();
    @Override
    public List<User> findAll() {
        //调用findAll（）方法返回List<User>
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.findUserByusernameAndpassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void delUser(String id) {
        userDao.delUser(Integer.parseInt(id));
    }

    @Override
    public User findUserByid(String id) {
        return userDao.findUserByid(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void delselectedUserByids(String[] ids) {
        for (String id:ids) {
            userDao.delUser(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentpage, String _rows, Map<String, String[]> condition) {
        int currentpage = Integer.parseInt(_currentpage);
        int rows = Integer.parseInt(_rows);
        if (currentpage < 1){
            currentpage = 1;
        }
        //1 创建分页对象
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrentpage(currentpage);
        pageBean.setRows(rows);
        //封装pagebean对象
        //2 获取总记录
        int totalcount = userDao.findTotalcount(condition);
        pageBean.setTotalcount(totalcount);
        //3 获取user的list集合
        int start = (currentpage -1) * rows;
        pageBean.setStart(start);
        List<User> users = userDao.findByPage(start,rows,condition);
        pageBean.setObjectInPage(users);
        //4 计算总页数
        int totalpage = (totalcount % rows) == 0 ? (totalcount / rows) : totalcount / rows + 1;
        pageBean.setTotalpage(totalpage);
        //5 返回结果
        return pageBean;
    }

}

