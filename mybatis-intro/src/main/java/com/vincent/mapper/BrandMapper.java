package com.vincent.mapper;

import com.vincent.domain.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> selectAll();
    List<Brand> selectByCompanyName(String companyName);
    List<Brand> selectByCompanyNameAndStatus(@Param("companyName") String companyName,
                                             @Param("status") Integer status);
// 封装类对象 查询
    List<Brand>selectByBrand(Brand brand);
    // 对于complicate request, then use map as para input to search
    List<Brand> selectByCondition(Map map);
    List<Brand> selectByCondition2(Map map);
    //C
//    void insert(Brand brand);
    int insert(Brand brand);
    void updatedById(Brand brand);
    void deleteById(Integer id);
    int deleteByIds(Integer[] ids);
    int insertBatch(@Param("brands") List<Brand> brands);
}
