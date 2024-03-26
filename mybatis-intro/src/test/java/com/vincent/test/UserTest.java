package com.vincent.test;

import com.vincent.domain.User;
import com.vincent.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    @Test
    public void testSelectAll() throws IOException {
        //1.读取配置文件--在resources文件夹下可以直接读到
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.使用SqlSession创建Dao接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //5.使用代理对象执行方法
        List<User> users = userMapper.selectAll();
        System.out.println(users);

        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testSelectById() throws IOException {
        //1.读取配置文件--在resources文件夹下可以直接读到
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.使用SqlSession创建Dao接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //5.使用代理对象执行方法
//        List<User> users = userMapper.selectById(46);
        System.out.println( userMapper.selectById(46));

        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }
}
