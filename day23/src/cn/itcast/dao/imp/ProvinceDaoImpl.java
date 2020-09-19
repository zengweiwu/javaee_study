package cn.itcast.dao.imp;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.domain.Province;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class ProvinceDaoImpl implements ProvinceDao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAllProvince() {
        //定义sql
        String sql = "select * from province";.
        //执行sql query方法可以封装成指定对象返回
        List<Province> provinces = jt.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
