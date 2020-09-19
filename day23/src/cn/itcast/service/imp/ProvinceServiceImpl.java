package cn.itcast.service.imp;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.imp.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import cn.itcast.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
private ProvinceDao provinceDao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAllProvince() {
        return provinceDao.findAllProvince();
    }

    @Override
    public String findAll_json() {
        //先查询redis缓存
        Jedis jedis = JedisPoolUtils.getJedis();
        String provinces_json = jedis.get("provinces");
        //如果缓存中没有则查询数据库
        if (provinces_json == null || provinces_json.length() == 0){
            List<Province> allProvince = provinceDao.findAllProvince();
            //将查询出来的对象集合封装成json格式
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                provinces_json = objectMapper.writeValueAsString(allProvince);
                //将查询出来的值存入redis
                jedis.set("provinces",provinces_json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return provinces_json;
    }
}
