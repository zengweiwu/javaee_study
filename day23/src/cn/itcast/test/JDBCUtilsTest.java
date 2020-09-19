package cn.itcast.test;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.imp.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * jdbc工具类测试
 */
public class JDBCUtilsTest {
    @Test
    public void  test1(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from province";
        List<Map<String, Object>> maps = template.queryForList(sql);
        System.out.println(maps);
    }
    /**
     * 测试findAllProvince()
     */
    @Test
    public void testfindAllProvince(){
        ProvinceDao provinceDao = new ProvinceDaoImpl();
        List<Province> allProvince = provinceDao.findAllProvince();
        System.out.println(allProvince);
    }
}

