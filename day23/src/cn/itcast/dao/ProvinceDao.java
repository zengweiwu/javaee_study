package cn.itcast.dao;

import cn.itcast.domain.Province;

import java.util.List;

/**
 * Province的数据库操作接口
 */
public interface ProvinceDao {
    /**
     * 从数据库查找所有省份 返回list集合
     */
    List<Province> findAllProvince();
}
