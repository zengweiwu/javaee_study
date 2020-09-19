package cn.itcast.service;

import cn.itcast.domain.Province;

import java.util.List;

/**
 * province服务层接口
 */
public interface ProvinceService {
    /**
     * 查询所有省份返回list集合
     * @return
     */
    List<Province> findAllProvince();

    /**
     * 查询所有省份返回json格式的字符串
     * @return
     */
    String findAll_json();
}
