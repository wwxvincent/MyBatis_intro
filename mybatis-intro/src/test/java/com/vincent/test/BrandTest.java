package com.vincent.test;

import com.vincent.domain.Brand;
import com.vincent.domain.User;
import com.vincent.mapper.BrandMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandTest {
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private BrandMapper brandMapper;

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
        brandMapper = sqlSession.getMapper(BrandMapper.class);
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
        //5.使用代理对象执行方法
        List<Brand> brands = brandMapper.selectAll();
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }

    @Test
    public void testSelectByCompanyName() throws IOException {
        //5.使用代理对象执行方法
//        List<Brand> brands = brandMapper.selectByCompanyName("%科技有限%");
        List<Brand> brands = brandMapper.selectByCompanyName("科技有限");
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }

    // multiple element request
    @Test
    public void testSelectByCompanyNameAndStatus() throws IOException {
        //5.使用代理对象执行方法
//        List<Brand> brands = brandMapper.selectByCompanyName("%科技有限%");
        List<Brand> brands = brandMapper.selectByCompanyNameAndStatus("科技有限",0);
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
    @Test
    public void testSelectByBrand() throws IOException {
        Brand brand = new Brand();
        brand.setCompanyName("%科技有限%");
        brand.setStatus(0);
//        brand.setBrandName("XXX");
        List<Brand> brands = brandMapper.selectByBrand(brand);
        System.out.println(brands);
    }
    @Test
    public void testSelectByCondition() throws IOException {
        Map map = new HashMap<>();
//        map.put("companyName", "%科技有限%");
//        map.put("status",0);
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);
    }
    //动态sql chosen when ；when the first condition fit, then pop out
    @Test
    public void testSelectByCondition2() throws IOException {
        Map map = new HashMap<>();
//        map.put("companyName", "%科技有限%");
        map.put("status",0);
        map.put("brand_name","格力");
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);
    }
    @Test
    public void testInsert() throws IOException {
        Brand brand = new Brand();
        brand.setBrandName("小米3");
        brand.setCompanyName("小米科技有限公司3");
        brand.setOrdered(7);
        brand.setDescription("AreYouOkay2");
        brand.setStatus(1);
//        brandMapper.insert(brand);
        int i = brandMapper.insert(brand);
        System.out.println(i);
        System.out.println(brand.getId());
    }
    @Test
    public void testUpdatedById() throws IOException {
        Brand brand = new Brand();
        brand.setId(6);
        brand.setCompanyName("小米科技有限公司4");
        brand.setBrandName("小米4");
        brand.setOrdered(5);
        brand.setStatus(0);
        brandMapper.updatedById(brand);
    }
    @Test
    public void testDeletedById() throws IOException {
        brandMapper.deleteById(8);
    }
    @Test
    public void testDeletedByIds() throws IOException {
        Integer[] ids = {6,7};
        int i = brandMapper.deleteByIds(ids);
        System.out.println(i);
    }
    @Test
    public void testInsertBatch() throws IOException {
        List<Brand> brands = new ArrayList<>();
        // brand 1
        Brand brand1 = new Brand();
        brand1.setBrandName("ROG");
        brand1.setCompanyName("华硕科技有限公司");
        brand1.setOrdered(7);
        brand1.setDescription("fancy");
        brand1.setStatus(1);
        //brand2
        Brand brand2 = new Brand();
        brand2.setBrandName("DELL");
        brand2.setCompanyName("DELL科技有限公司");
        brand2.setOrdered(8);
        brand2.setDescription("Not fancy");
        brand2.setStatus(0);

        brands.add(brand1);
        brands.add(brand2);

        int i = brandMapper.insertBatch(brands);
        System.out.println(i);
    }
}
