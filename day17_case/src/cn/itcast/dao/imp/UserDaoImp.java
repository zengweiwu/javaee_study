package cn.itcast.dao.imp;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImp implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //用jdbctemplate执行sql封装数据
        //定义sql
        String sql = "select * from user";
        //java.lang.NoClassDefFoundError: org/springframework/jdbc/core/RowMapper
        // 这个是因为实体类字段跟数据库类型不一致造成的,把实体类类型改成跟数据库类型一样就好了 基本类型统一用包装类就可以了
        List<User> users = jt.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUserByusernameAndpassword(String username,String password) {
        try {
            //定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            //查询返回完整user对象
            User user = jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            //查询出现异常返回null
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
            //定义sql
            String sql = "insert into user values (null,?,?,?,?,?,?,null,null)";
            //执行sql
            jt.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delUser(int id) {
        //定义sql
        String sql = "delete from user where id = ?";
        //执行sql
        jt.update(sql,id);
    }

    @Override
    public User findUserByid(int id) {
        try {
            //定义sql
            String sql = "select * from user where id = ?";
            //执行sql 返回对象
            User user = jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void updateUser(User user) {
        //定义sql
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        //执行sql
        jt.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalcount(Map<String, String[]> condition) {
        //定义初始sql
        String sql = "select count(*) from user where 1=1";
        //用stringbuilder来拼接sql
        StringBuilder sb = new StringBuilder(sql);
        //定义储存参数的集合
        List<Object> param = new ArrayList<Object>();
        if (condition != null){
            //获取condition的键集合
            Set<String> keys = condition.keySet();
            for (String key:keys) {
                if ("currentpage".equals(key) || "rows".equals(key)){
                    continue;
                }
                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)){
                    sb.append(" and "+key+" like ?");
                    //将参数放入param中
                    param.add("%"+value+"%");//?的值
                }
            }
        }
        //执行sql 返回结果
        return jt.queryForObject(sb.toString(),Integer.class,param.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //定义初始sql
        String sql = "select * from user where 1=1";
        //用stringbuilder来拼接sql
        StringBuilder sb = new StringBuilder(sql);
        //定义储存参数的集合
        List<Object> param = new ArrayList<Object>();
        if (condition != null){
            //获取condition的键集合
            Set<String> keys = condition.keySet();
            for (String key:keys) {
                if ("currentpage".equals(key) || "rows".equals(key)){
                    continue;
                }
                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)){
                    sb.append(" and "+key+" like ?");
                    //将参数放入param中
                    param.add("%"+value+"%");//?的值
                }
            }
        }
        //添加分页查询
        sb.append(" limit ?,?");
        param.add(start);
        param.add(rows);
        Object[] sqlob = param.toArray();
        //执行sql 返回结果(注意执行的sql不是原来的sql)
        List<User> users = jt.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class),sqlob);
        return users;
    }
}
