package com.zt.javastudy.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.zt.javastudy.mybatis.entity.TUser;
import com.zt.javastudy.mybatis.mapper.TUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


/**
 * mybatis测试
 *
 * @author zhengtao on 2021/9/16
 */
@Slf4j
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        // 1. 加载 Mybatis 配置文件，创建 SqlSessionFactory
        // 注：在实际的应用中，SqlSessionFactory 应该是单例
        InputStream inputStream = Resources.getResourceAsStream("/src/main/resources/mybatis/mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        // 2. 创建一个 SqlSession 实例，进行数据库操作
        SqlSession sqlSession = factory.openSession();

        // 3. Mapper 映射并执行
        Long params = 1L;
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser user = mapper.selectByPrimaryKey("1027604989");
        log.info("mybatis 查询结果:{}", JSONObject.toJSONString(user));
    }
}
