package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询用户信息的方法
     * @return
     */
    public List<User> findAll();

    /**
     * 根据用户名和密码查询用户返回一个完整的user
     * @return
     */
    public User login(User user);

    /**
     * 保存user
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据id删除指定记录
     * @param id
     */
    void delUser(String id);

    /**
     * 根据id查找出记录 封装成对象返回
     */
    User findUserByid(String id);

    /**
     * 修改指定id的记录
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除选中条目
     * @param ids
     */
    void delselectedUserByids(String[] ids);

    /**
     * 分页查询
     * @param _currentpage
     * @param _rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String _currentpage, String _rows, Map<String, String[]> condition);
}
