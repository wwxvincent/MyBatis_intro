package com.vincent.mapper;

import com.vincent.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role;")
    List<Role> selectALL();

    @Insert("insert into role (id, rolename, roledesc) values (#{id}, #{rolename}, #{roledesc});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Role role);

    void updateById(Role role);
}
