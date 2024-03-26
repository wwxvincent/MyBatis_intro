package com.vincent.test;

import com.vincent.domain.Role;
import com.vincent.mapper.BrandMapper;
import com.vincent.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private RoleMapper roleMapper;

    @Before
    public void init() throws Exception {
        System.out.println("Before==================================>");
        //1.读取配置文件--在resources文件夹下可以直接读到
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.使用工厂生产SqlSession对象
        //事物 方式2 默认值false;开启事务 true 做增删改的时候 需要把事务打开
//        sqlSession = sqlSessionFactory.openSession();
        sqlSession = sqlSessionFactory.openSession(true);

        //4.使用SqlSession创建Dao接口的代理对象
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void destroy() throws Exception {
        //6.释放资源
        System.out.println("After==================================>");
        //事物 方式1
//        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testSelectAll() throws IOException {
        List<Role> roles = roleMapper.selectALL();
        System.out.println(roles);
    }
    @Test
    public void testInsert() throws IOException {
        Role role = new Role();
        role.setId(3);
        role.setRolename("President");
        role.setRoledesc("running the whole company");
        roleMapper.insert(role);
    }

    @Test
    public void testUpdateById() throws IOException {
        Role role = new Role();
        role.setId(3);
        role.setRolename("President's wife");
        roleMapper.updateById(role);
    }

}
