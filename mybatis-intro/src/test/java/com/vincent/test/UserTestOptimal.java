package com.vincent.test;

import com.vincent.domain.User;
import com.vincent.mapper.UserMapper;
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

public class UserTestOptimal {
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() throws Exception {
        System.out.println("Before==================================>");
        //1.读取配置文件--在resources文件夹下可以直接读到
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.使用工厂生产SqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() throws Exception {
        //6.释放资源
        System.out.println("After==================================>");
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void testSelectAll() throws IOException {
        //5.使用代理对象执行方法
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }

    @Test
    public void testSelectById() throws IOException {
        //5.使用代理对象执行方法
        System.out.println( userMapper.selectById(46));
    }

    @Test
    public void testSelectByCondition() throws IOException {
        //5.使用代理对象执行方法
        List<User> users = userMapper.selectByIdCondition(45);
        for(User user : users) {
            System.out.println(user);
        }
    }

}
