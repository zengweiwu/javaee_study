package com.hut.itcast.jdbctemplate;

import com.hut.itcast.datasource.utils.JDBCUtils;
import com.hut.itcast.domain.Emp;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 测试JdbcTemplate update方法 对数据增删改
 */
public class JdbcTempateDemo2 {
    //创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 将emp中唐僧的薪水改为10000
     */
    @Test
    public void test1(){
        //定义sql
        String sql = "update emp set salary = 10000 where id = 3";
        //执行sql
        template.update(sql);
    }

    /**
     * 向emp表中添加一条记录
     */
    @Test
    public void test2(){
        String sql = "insert into emp values (null,'杨过','男',1111,'2011-03-14',1)";
        template.update(sql);
    }

    /**
     * 删除刚刚添加的记录
     */
    @Test
    public void test3(){
        String sql = "delete from emp where id = 7";
        template.update(sql);
    }

    /**
     * 将查询的一条记录（注意是一条记录）封装成map集合：以列名为key，值为value
     */
    @Test
    public void test4(){
        String sql = "select * from emp where id = 1";
        Map<String, Object> emp1 = template.queryForMap(sql);
        System.out.println(emp1);
    }

    /**
     * 将查询的所有记录每一个封装成map集合后，再将map集合封装成list集合
     */
    @Test
    public void test5(){
        String sql = "select * from emp";
        List<Map<String, Object>> maps = template.queryForList(sql);
        for (Map<String,Object> emp:maps) {
            System.out.println(emp);
        }
    }

    /**
     * 将查询的所有记录封装成一个个emp对象，放进list集合 返回一个list集合
     */
    @Test
    public void test6(){
        String sql = "select * from emp";
        //传入RowMapper接口的实现类对象BeanPropertyRowMapper（也可以自己实现这个接口）指定泛型 自动封装成emp对象并返回list集合
        //注意 要封装的对象成员变量的类型不能是基本数据类型，不然不能转换
        List<Emp> emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp:emps) {
            System.out.println(emp);
        }
    }

    /**
     * 查询总记录
     */
    @Test
    public void test7(){
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, Long.class);//传入需要返回的类型
        System.out.println(total);
    }
}
