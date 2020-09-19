package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * User持久层接口
 */
public interface UserDao {
    /**
     * 查询user接口
     * @return
     */
    public List<User> findAll();

    /**
     * 根据用户名和密码查询用户 返回完整的用户信息
     * @return
     */
    public User findUserByusernameAndpassword(String username,String password);

    /**
     * 保存用户
     * @param user
     */
    public void add(User user);

    /**
     * 根据id删除指定记录
     * @param id
     */
    void delUser(int id);

    /**
     * 根据id查找记录 返回封装对象
     * @param parseInt
     * @return
     */
    User findUserByid(int id);

    /**
     * 修改指定id的记录
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalcount(Map<String, String[]> condition);

    /**
     * 查询每一页的user对象 返回该页user的list集合
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
