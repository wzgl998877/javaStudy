package com.zt.javastudy.mybatis;


import com.zt.javastudy.mybatis.entity.TUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * mybatis学习
 *
 * @author zhengtao on 2021/12/14
 */
public class MybatisApiTest {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            try {
                TUser user = session.selectOne("com.zt.javastudy.mybatis.mapper.TUserMapper.selectByPrimaryKey", "1027604989");
                System.out.println(user);
            } finally {
                session.close();
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
