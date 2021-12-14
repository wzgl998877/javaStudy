//package com.zt.javastudy.mybatis.like;
//
//import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
//import org.springframework.context.annotation.Configuration;
//
//import javax.lang.model.element.Element;
//import java.io.Reader;
//
///**
// * session构建类
// *
// * @author zhengtao on 2021/12/14
// */
//public class SqlSessionFactoryBuilder {
//    public DefaultSqlSessionFactory build(Reader reader) {
//        SAXReader
//    }
//    private Configuration parseConfiguration(Element root) {
//        Configuration configuration = new Configuration();
//        configuration.setDataSource(dataSource(root.selectNodes("//dataSource")));
//        configuration.setConnection(connection(configuration.dataSource));
//        configuration.setMapperElement(mapperElement(root.selectNodes("mappers")));
//        return configuration;
//    }
//
//}
